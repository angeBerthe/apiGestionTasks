package ci.digitalacademy.apigestiontasks.services.impl;

import ci.digitalacademy.apigestiontasks.models.Team;
import ci.digitalacademy.apigestiontasks.repositories.TeamRepository;
import ci.digitalacademy.apigestiontasks.services.ProjectService;
import ci.digitalacademy.apigestiontasks.services.TeamService;
import ci.digitalacademy.apigestiontasks.services.dto.MemberDTO;
import ci.digitalacademy.apigestiontasks.services.dto.ProjectDTO;
import ci.digitalacademy.apigestiontasks.services.dto.TeamDTO;
import ci.digitalacademy.apigestiontasks.services.mapper.MemberMapper;
import ci.digitalacademy.apigestiontasks.services.mapper.ProjectMapper;
import ci.digitalacademy.apigestiontasks.services.mapper.TeamMapper;
import ci.digitalacademy.apigestiontasks.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final ProjectService projectService;

    @Override
    public TeamDTO save(TeamDTO teamDTO) {
        log.debug("Request to save Team : {}", teamDTO);
        Optional<ProjectDTO> projectDTO = projectService.findOne(teamDTO.getProject().getId());
        if (projectDTO.isPresent()){
            teamDTO.setProject(projectDTO.get());
        }
        Team team = teamMapper.toEntity(teamDTO);
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

    @Override
    public TeamDTO saveTeam(TeamDTO teamDTO) {
        log.debug("Request to save project and slug {}", teamDTO);
        final String slug = SlugifyUtils.generate(teamDTO.getNameTeam());
        teamDTO.setSlug(slug);
        return save(teamDTO);
    }


}
