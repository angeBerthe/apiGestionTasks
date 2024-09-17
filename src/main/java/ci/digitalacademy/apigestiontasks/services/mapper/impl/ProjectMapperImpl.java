package ci.digitalacademy.apigestiontasks.services.mapper.impl;

import ci.digitalacademy.apigestiontasks.models.Project;
import ci.digitalacademy.apigestiontasks.services.dto.ProjectDTO;
import ci.digitalacademy.apigestiontasks.services.mapper.ProjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class ProjectMapperImpl implements ProjectMapper {

    private final ModelMapper modelMapper;

    @Override
    public ProjectDTO fromEntity(Project entity) {
        log.debug("Mapping to Project to ProjectDTO");
        return modelMapper.map(entity, ProjectDTO.class);
    }

    @Override
    public Project toEntity(ProjectDTO dto) {
        log.debug("Mapping ProjectDTO to Project");
        return modelMapper.map(dto, Project.class);
    }
}
