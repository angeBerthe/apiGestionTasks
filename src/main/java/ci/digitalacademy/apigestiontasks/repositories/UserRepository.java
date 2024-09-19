package ci.digitalacademy.apigestiontasks.repositories;

import ci.digitalacademy.apigestiontasks.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String pseudo);
}
