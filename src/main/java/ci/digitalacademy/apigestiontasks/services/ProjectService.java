package ci.digitalacademy.apigestiontasks.services;

import ci.digitalacademy.apigestiontasks.services.dto.ProjectDTO;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    ProjectDTO save(ProjectDTO projectDTO);

    ProjectDTO update(ProjectDTO projectDTO);

    Optional<ProjectDTO> findOne(Long id);

    List<ProjectDTO> findAll();

    void delete(Long id);

    ProjectDTO update(ProjectDTO projectDTO, Long id);

    ProjectDTO saveProject(ProjectDTO projectDTO);

    ProjectDTO partialupdate(ProjectDTO projectDTO, Long id);

    Optional<ProjectDTO> findOneBySlug(String slug);
}
