package ci.digitalacademy.apigestiontasks.services.mapper.impl;

import ci.digitalacademy.apigestiontasks.models.RoleUser;
import ci.digitalacademy.apigestiontasks.services.dto.RoleUserDTO;
import ci.digitalacademy.apigestiontasks.services.mapper.RoleUserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RoleUserMapperImpl implements RoleUserMapper {

    private final ModelMapper modelMapper;

    @Override
    public RoleUserDTO fromEntity(RoleUser entity) {
        return modelMapper.map(entity, RoleUserDTO.class);
    }

    @Override
    public RoleUser toEntity(RoleUserDTO dto) {
        return modelMapper.map(dto, RoleUser.class);
    }
}
