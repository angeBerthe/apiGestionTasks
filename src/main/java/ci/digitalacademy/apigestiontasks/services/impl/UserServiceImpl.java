package ci.digitalacademy.apigestiontasks.services.impl;

import ci.digitalacademy.apigestiontasks.models.User;
import ci.digitalacademy.apigestiontasks.repositories.UserRepository;
import ci.digitalacademy.apigestiontasks.services.UserService;
import ci.digitalacademy.apigestiontasks.services.dto.UserDTO;
import ci.digitalacademy.apigestiontasks.services.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO save(UserDTO userDTO) {
        log.debug("Request to save user {}", userDTO);
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.fromEntity(user);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        return findOne(userDTO.getId()).map(user -> {
            user.setUsername(userDTO.getUsername());
            return save(user);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public Optional<UserDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
}
