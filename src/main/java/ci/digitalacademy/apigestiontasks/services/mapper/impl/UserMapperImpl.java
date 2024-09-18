package ci.digitalacademy.apigestiontasks.services.mapper.impl;

import ci.digitalacademy.apigestiontasks.models.User;
import ci.digitalacademy.apigestiontasks.services.dto.UserDTO;
import ci.digitalacademy.apigestiontasks.services.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final ModelMapper modelMapper;

    @Override
    public UserDTO fromEntity(User entity) {
        return modelMapper.map(entity, UserDTO.class);
    }

    @Override
    public User toEntity(UserDTO dto) {
        return modelMapper.map(dto, User.class);
    }
}
