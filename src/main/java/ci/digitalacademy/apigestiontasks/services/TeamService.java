package ci.digitalacademy.apigestiontasks.services;

import ci.digitalacademy.apigestiontasks.services.dto.MemberDTO;
import ci.digitalacademy.apigestiontasks.services.dto.TeamDTO;

import java.util.List;
import java.util.Optional;

public interface TeamService {

    TeamDTO save(TeamDTO teamDTO);

    List<TeamDTO> findAll();

    Optional<TeamDTO> findOne(Long id);

    Optional<TeamDTO> findBySlug(String slug);

    TeamDTO update(TeamDTO teamDTO, Long id);

    void delete(Long id);

    List<MemberDTO> getMembersByTeamId(Long id);
}
