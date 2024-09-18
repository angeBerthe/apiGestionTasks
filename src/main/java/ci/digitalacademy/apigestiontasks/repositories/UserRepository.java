package ci.digitalacademy.apigestiontasks.repositories;

import ci.digitalacademy.apigestiontasks.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
