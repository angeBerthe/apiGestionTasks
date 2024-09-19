package ci.digitalacademy.apigestiontasks.services.dto;

import ci.digitalacademy.apigestiontasks.models.Member;
import ci.digitalacademy.apigestiontasks.models.Project;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class TeamDTO {

    private Long id;

    private String nameTeam;

    private String description;

    private String slug;


    private ProjectDTO project;

}
