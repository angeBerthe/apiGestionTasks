package ci.digitalacademy.apigestiontasks.services;

import ci.digitalacademy.apigestiontasks.services.dto.RoleUserDTO;

import java.util.List;

public interface RoleUserService {

    RoleUserDTO save(RoleUserDTO roleUserDTO);

    List<RoleUserDTO> findByRole(String roleUser);
}
