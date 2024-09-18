package ci.digitalacademy.apigestiontasks.services;

import ci.digitalacademy.apigestiontasks.services.dto.UserDTO;

import java.util.Optional;

public interface UserService {

    UserDTO save(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    Optional<UserDTO> findOne(Long id);

    void delete(Long id);
}
