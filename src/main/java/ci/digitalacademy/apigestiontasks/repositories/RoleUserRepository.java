package ci.digitalacademy.apigestiontasks.repositories;

import ci.digitalacademy.apigestiontasks.models.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleUserRepository extends JpaRepository<RoleUser, Long> {
}
