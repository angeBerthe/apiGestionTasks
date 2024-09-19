package ci.digitalacademy.apigestiontasks.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseUserDTO {

    private Long id;

    private String username;

    @JsonIgnoreProperties({"role"})
    private List<RoleUserDTO> roleUsers;
}
