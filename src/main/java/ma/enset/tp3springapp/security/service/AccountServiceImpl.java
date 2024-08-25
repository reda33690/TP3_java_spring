package ma.enset.tp3springapp.security.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.enset.tp3springapp.security.entities.AppRole;
import ma.enset.tp3springapp.security.entities.AppUser;
import ma.enset.tp3springapp.security.repository.AppRoleRipository;
import ma.enset.tp3springapp.security.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AppRoleRipository appRoleRipository;
    private AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser addNewUser(String username, String password, String email, String confirmPassword) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser != null) throw new RuntimeException("This user already exist");
        if (!password.equals(confirmPassword)) throw new RuntimeException("Password not match");
        AppUser user = AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();

        AppUser savedUser = appUserRepository.save(user);
        return savedUser;
    }

    @Override
    public AppRole addNewRole(String role) {
        AppRole appRole = appRoleRipository.findById(role).orElse(null);
        if (appRole != null) throw new RuntimeException("This role already exist");
        appRole = AppRole.builder().role(role).build();
        return appRoleRipository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser appUser=appUserRepository.findByUsername(username) ;
        AppRole appRole=appRoleRipository.findById(role).get();
        appUser.getRoles().add(appRole);

    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppUser appUser=appUserRepository.findByUsername(username) ;
        AppRole appRole=appRoleRipository.findById(role).get();
        appUser.getRoles().remove(appRole);

    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
