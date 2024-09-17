package ci.digitalacademy.apigestiontasks.repositories;

import aj.org.objectweb.asm.commons.Remapper;
import ci.digitalacademy.apigestiontasks.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findBySlug(String slug);
}
