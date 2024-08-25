package ma.enset.tp3springapp.security.repository;

import ma.enset.tp3springapp.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRipository extends JpaRepository<AppRole , String> {

}
