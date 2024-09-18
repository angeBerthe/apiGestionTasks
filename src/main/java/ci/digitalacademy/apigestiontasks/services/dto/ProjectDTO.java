package ci.digitalacademy.apigestiontasks.services.dto;

import ci.digitalacademy.apigestiontasks.models.Team;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class ProjectDTO {

    private Long id;

    private String nameProject;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean status;

    private String slug;

    private Set<TeamDTO> teams;
}
