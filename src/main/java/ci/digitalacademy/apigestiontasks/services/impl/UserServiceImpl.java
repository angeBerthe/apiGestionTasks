package ci.digitalacademy.apigestiontasks.services.impl;

import ci.digitalacademy.apigestiontasks.models.User;
import ci.digitalacademy.apigestiontasks.repositories.UserRepository;
import ci.digitalacademy.apigestiontasks.security.AuthorityConstants;
import ci.digitalacademy.apigestiontasks.services.RoleUserService;
import ci.digitalacademy.apigestiontasks.services.UserService;
import ci.digitalacademy.apigestiontasks.services.dto.RoleUserDTO;
import ci.digitalacademy.apigestiontasks.services.dto.UserDTO;
import ci.digitalacademy.apigestiontasks.services.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleUserService roleUserService;

    @Override
    public UserDTO save(UserDTO userDTO) {
        log.debug("Request to save user {}", userDTO);
        String password = userDTO.getPassword();
        userDTO.setPassword(bCryptPasswordEncoder.encode(password));
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.fromEntity(user);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        log.debug("Request to update user {}", userDTO);
        return findOne(userDTO.getId()).map(user -> {
            user.setUsername(userDTO.getUsername());
            return save(user);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public Optional<UserDTO> findOne(Long id) {
        log.debug("Request to get User : {}", id);
        return userRepository.findById(id).map(user -> {
            return userMapper.fromEntity(user);
        });
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete User : {}", id);
        userRepository.deleteById(id);
    }
}
