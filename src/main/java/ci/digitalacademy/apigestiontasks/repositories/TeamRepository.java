package ci.digitalacademy.apigestiontasks.repositories;

import aj.org.objectweb.asm.commons.Remapper;
import ci.digitalacademy.apigestiontasks.models.Team;
import ci.digitalacademy.apigestiontasks.services.dto.TeamDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findBySlug(String slug);
}
