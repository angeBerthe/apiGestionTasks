package ci.digitalacademy.apigestiontasks.services.mapper.impl;

import ci.digitalacademy.apigestiontasks.models.Team;
import ci.digitalacademy.apigestiontasks.services.dto.TeamDTO;
import ci.digitalacademy.apigestiontasks.services.mapper.TeamMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class TeamMapperImpl implements TeamMapper {
    private final ModelMapper modelMapper;

    @Override
    public TeamDTO fromEntity(Team entity) {
        log.debug("Mapping Team to TeamDto");
        return modelMapper.map(entity, TeamDTO.class );
    }

    @Override
    public Team toEntity(TeamDTO dto) {
        log.debug("Mapping TeamDTO to Team");
        return modelMapper.map(dto, Team.class);
    }
}
