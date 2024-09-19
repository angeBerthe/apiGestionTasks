package ci.digitalacademy.apigestiontasks.services.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    private String userName;

    private String password;

    private boolean rememberMe;
}
