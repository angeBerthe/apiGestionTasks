package ci.digitalacademy.apigestiontasks.services.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TasksDTO {

    private Long id;

    private String wording;

    private LocalDate startDate;

    private LocalDate endDate;

    private String status;

    private String slug;

    private ProjectDTO project;

    private TeamDTO team;
}
