package ci.digitalacademy.apigestiontasks.services.dto;

import ci.digitalacademy.apigestiontasks.models.Project;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamDTO {

    private Long id;

    private String nameTeam;

    private String description;

    private String slug;

    private ProjectDTO project;

}
