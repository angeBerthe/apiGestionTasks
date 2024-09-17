package ci.digitalacademy.apigestiontasks.services.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProjectDTO {

    private Long id;

    private String nameProject;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private String status;

    private String slug;
}
