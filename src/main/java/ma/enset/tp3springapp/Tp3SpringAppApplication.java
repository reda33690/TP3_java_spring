package ma.enset.tp3springapp;

import com.github.javafaker.Faker;

import ma.enset.tp3springapp.entities.Patient;
import ma.enset.tp3springapp.repository.PatientRepository;
import ma.enset.tp3springapp.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;


import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
public class Tp3SpringAppApplication implements CommandLineRunner {

    @Autowired
    PatientRepository patientRepository;


    private final Faker faker = new Faker();

    public static void main(String[] args) {
        SpringApplication.run(Tp3SpringAppApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {


        Random random = new Random();

        String[] firstNames = {"Mohammed", "Fatima", "Ali", "Noor", "Sara", "Amine", "Karima", "Youssef"};
        String[] lastNames = {"Alami", "Zahra", "Benkiran", "Chakir", "Amrani", "El Aoufi", "El Mahroug", "Moutawakil"};

        for (int i = 0; i < 50; i++) {
            String firstName = firstNames[random.nextInt(firstNames.length)];
            String lastName = lastNames[random.nextInt(lastNames.length)];
            String fullName = firstName + " " + lastName;

            Date birthDate = generateRandomBirthDate(random);
            boolean isIll = random.nextBoolean();
            int minScore = 101; // Minimum score
            int maxScore = 200; // Maximum score
            int score = random.nextInt(maxScore - minScore + 1) + minScore; // Generates a score between 101 and 200

            Patient patient = new Patient(null, fullName, birthDate, isIll, score);
            patientRepository.save(patient);
        }
    }


    private Date generateRandomBirthDate(Random random) {
        int year = random.nextInt(56) + 1950; // Random year between 1950 and 2005
        int month = random.nextInt(12); // Random month (0-11)
        int day = random.nextInt(28) + 1; // Random day (1-28)
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }


    @Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager) {
        PasswordEncoder passwordEncoder = passwordEncoder();
        return args -> {
            UserDetails u1 = jdbcUserDetailsManager.loadUserByUsername("user3");
            UserDetails u2 = jdbcUserDetailsManager.loadUserByUsername("user4");
            UserDetails u3 = jdbcUserDetailsManager.loadUserByUsername("admin1");


            if (u1 == null) {
                jdbcUserDetailsManager.createUser(
                        User.withUsername("user3").password(passwordEncoder.encode("1234")).roles("USER").build()
                );
            }

            if (u2 == null) {
                jdbcUserDetailsManager.createUser(
                        User.withUsername("user4").password(passwordEncoder.encode("1234")).roles("USER").build()
                );
            }

            if (u3 == null) {
                jdbcUserDetailsManager.createUser(
                        User.withUsername("admin1").password(passwordEncoder.encode("1234")).roles("USER").build()
                );
            }

        };
    }



    @Bean
    CommandLineRunner commandLineRunnerUserDetails(AccountService accountService) {
        return args -> {
            accountService.addNewRole ("USER");
            accountService.addNewRole("ADMIN");
            accountService.addNewUser ("user1", "1234", "user1@gmail.com", "1234");
            accountService.addNewUser ("user2", "1234", "user2@gmail.com", "1234");
            accountService.addNewUser ("admin", "1234", "admin@gmail.com", "1234");
            accountService.addRoleToUser ("user1", "USER");
            accountService.addRoleToUser ("user2" , "USER");
            accountService.addRoleToUser ("admin", "USER");
            accountService.addRoleToUser ("admin", "ADMIN");

        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

