package ci.digitalacademy.apigestiontasks.services.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private List<RoleUserDTO> roleUsers;
}
