package ci.digitalacademy.apigestiontasks.services.mapper.impl;

import ci.digitalacademy.apigestiontasks.models.Tasks;
import ci.digitalacademy.apigestiontasks.services.dto.TasksDTO;
import ci.digitalacademy.apigestiontasks.services.mapper.TasksMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
@Slf4j
public class TasksMapperImpl implements TasksMapper {

    private final ModelMapper modelMapper;

    @Override
    public TasksDTO fromEntity(Tasks entity) {
        log.debug("Mapping Tasks to TasksDTO");
        return modelMapper.map(entity, TasksDTO.class);
    }

    @Override
    public Tasks toEntity(TasksDTO dto) {
        log.debug("Mapping TasksDTO to Tasks");
        return modelMapper.map(dto, Tasks.class);
    }
}
