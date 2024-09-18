package ci.digitalacademy.apigestiontasks.services.impl;

import ci.digitalacademy.apigestiontasks.models.Team;
import ci.digitalacademy.apigestiontasks.repositories.TeamRepository;
import ci.digitalacademy.apigestiontasks.services.TeamService;
import ci.digitalacademy.apigestiontasks.services.dto.MemberDTO;
import ci.digitalacademy.apigestiontasks.services.dto.TeamDTO;
import ci.digitalacademy.apigestiontasks.services.mapper.MemberMapper;
import ci.digitalacademy.apigestiontasks.services.mapper.TeamMapper;
import ci.digitalacademy.apigestiontasks.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final MemberMapper memberMapper;

    @Override
    public TeamDTO save(TeamDTO teamDTO) {
        log.debug("Request to save Team : {}", teamDTO);
        Team team = teamMapper.toEntity(teamDTO);
        team.setSlug(SlugifyUtils.generate(team.getNameTeam()));
        team = teamRepository.save(team);
        return teamMapper.fromEntity(team);
    }

    @Override
    public Optional<TeamDTO> findOne(Long id) {
        log.debug("Request to find Team with id: {}", id);
        return teamRepository.findById(id)
                .map(teamMapper::fromEntity);
    }
    @Override
    public Optional<TeamDTO> findBySlug(String slug) {
        return teamRepository.findBySlug(slug).map(teamMapper::fromEntity);
    }


    @Override
    public TeamDTO update(TeamDTO teamDTO, Long id) {
        log.debug("Request to update Team : {}", teamDTO);
        return findOne(id)
                .map(existingTeam -> {
                    existingTeam.setNameTeam(teamDTO.getNameTeam());
                    existingTeam.setDescription(teamDTO.getDescription());
                    existingTeam.setSlug(teamDTO.getSlug());
                    return save(existingTeam);
                })
                .orElseThrow(() -> new IllegalArgumentException("Team not found with id: " + id));
    }

    @Override
    public List<TeamDTO> findAll() {
        log.debug("Request to get all Teams");
        return teamRepository.findAll().stream()
                .map(teamMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Team with id: {}", id);
        teamRepository.deleteById(id);
    }

    public List<MemberDTO> getMembersByTeamId(Long id) {
        log.debug("Request to get members of Team with id: {}", id);
        Optional<Team> teamOptional = teamRepository.findById(id);
        if (teamOptional.isPresent()) {
            Team team = teamOptional.get();
            return team.getMembers().stream()
                    .map(memberMapper::fromEntity)
                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("Team not found with id: " + id);
        }
    }


}
