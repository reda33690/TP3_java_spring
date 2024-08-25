package ma.enset.tp3springapp.security.repository;

import ma.enset.tp3springapp.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser , String> {

    AppUser findByUsername(String username) ;
}
