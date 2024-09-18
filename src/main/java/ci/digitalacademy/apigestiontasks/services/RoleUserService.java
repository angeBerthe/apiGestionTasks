package ci.digitalacademy.apigestiontasks.services;

import ci.digitalacademy.apigestiontasks.services.dto.RoleUserDTO;

public interface RoleUserService {

    RoleUserDTO save(RoleUserDTO roleUserDTO);

    RoleUserDTO update(RoleUserDTO roleUserDTO);
}
