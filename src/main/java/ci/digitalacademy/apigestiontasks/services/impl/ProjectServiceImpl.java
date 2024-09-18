package ci.digitalacademy.apigestiontasks.services.impl;

import ci.digitalacademy.apigestiontasks.models.Project;
import ci.digitalacademy.apigestiontasks.models.Team;
import ci.digitalacademy.apigestiontasks.repositories.ProjectRepository;
import ci.digitalacademy.apigestiontasks.services.ProjectService;
import ci.digitalacademy.apigestiontasks.services.dto.MemberDTO;
import ci.digitalacademy.apigestiontasks.services.dto.ProjectDTO;
import ci.digitalacademy.apigestiontasks.services.dto.TeamDTO;
import ci.digitalacademy.apigestiontasks.services.mapper.ProjectMapper;
import ci.digitalacademy.apigestiontasks.services.mapper.TeamMapper;
import ci.digitalacademy.apigestiontasks.services.mapping.ProjectMapping;
import ci.digitalacademy.apigestiontasks.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;
    private final TeamMapper teamMapper;

    @Override
    public ProjectDTO save(ProjectDTO projectDTO) {
        log.debug("save to project {}", projectDTO);
        Project project = projectMapper.toEntity(projectDTO);
        Project saveProject = projectRepository.save(project);
        return projectMapper.fromEntity(saveProject);
    }

    @Override
    public ProjectDTO saveProject(ProjectDTO projectDTO) {
        log.debug("Request to save project and slug {}", projectDTO);
        final String slug = SlugifyUtils.generate(projectDTO.getNameProject());
        projectDTO.setSlug(slug);
        return save(projectDTO);
    }


    @Override
    public Optional<ProjectDTO> findOne(Long id) {
        log.debug("Resquest to get project by id: {}", id);
        return projectRepository.findById(id).map(project -> {
            return projectMapper.fromEntity(project);
        });
    }

    @Override
    public List<ProjectDTO> findAll() {
        log.debug("Resquest to get all project");
        return projectRepository.findAll().stream().map(project -> {
            return projectMapper.fromEntity(project);
        }).toList();
    }

    @Override
    public void delete(Long id) {
        log.debug("Resquest to delete project");
        projectRepository.deleteById(id);

    }

    @Override
    public ProjectDTO update(ProjectDTO projectDTO, Long id) {
        log.debug("Resquest to update project with two parameters :{} {}",projectDTO, id);
        return findOne(projectDTO.getId()).map(existingProject -> {
            existingProject.setNameProject(projectDTO.getNameProject());
            existingProject.setDescription(projectDTO.getDescription());
            existingProject.setStatus(projectDTO.getStatus());
            existingProject.setStartDate(projectDTO.getStartDate());
            existingProject.setEndDate(projectDTO.getEndDate());
            return save(existingProject);
        }).orElseThrow(()->new IllegalArgumentException());
    }

    @Override
    public ProjectDTO partialupdate(ProjectDTO projectDTO, Long id) {
        log.debug("Resquest to update partial project with two parameters :{} {}",projectDTO, id);
        return projectRepository.findById(id).map(project -> {
            ProjectMapping.partialUpdate(project, projectDTO);
            return project;
        }).map(projectRepository::save).map(projectMapper::fromEntity).orElse(null);
    }

    @Override
    public Optional<ProjectDTO> findOneBySlug(String slug) {
        log.debug("Request to get teacher by slug");
        return projectRepository.findBySlug(slug).map(teacher -> {
            return projectMapper.fromEntity(teacher);
        });
    }

    @Override
    public List<TeamDTO> getTeamsByProjectId(Long id) {
        log.debug("Request to get team of project with id: {}", id);
        Optional<Project> teamOptional = projectRepository.findById(id);
        if (teamOptional.isPresent()) {
            Project project = teamOptional.get();
            return project.getTeams().stream()
                    .map(teamMapper::fromEntity)
                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("Team not found with id: " + id);
        }
    }

}
