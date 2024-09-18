package ci.digitalacademy.apigestiontasks.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "role_user")
public class RoleUser implements Serializable {

    @Id
    private String role;
}
