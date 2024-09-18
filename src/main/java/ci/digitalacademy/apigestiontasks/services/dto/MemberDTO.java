package ci.digitalacademy.apigestiontasks.services.dto;

import ci.digitalacademy.apigestiontasks.enumeration.Gender;
import ci.digitalacademy.apigestiontasks.enumeration.Role;
import ci.digitalacademy.apigestiontasks.models.Notification;
import ci.digitalacademy.apigestiontasks.models.Tasks;
import ci.digitalacademy.apigestiontasks.models.Team;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class MemberDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Gender gender;

    private Role role;

    private String slug;

    private TeamDTO team;

    private Set<TasksDTO> tasks;

    private Set<NotificationDTO> notifications;
}
