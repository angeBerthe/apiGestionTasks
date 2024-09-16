package ci.digitalacademy.apigestiontasks.services.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;

public class ProjectDTO {

    private Long id;

    private String nameProject;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private String status;

    private String slug;
}
