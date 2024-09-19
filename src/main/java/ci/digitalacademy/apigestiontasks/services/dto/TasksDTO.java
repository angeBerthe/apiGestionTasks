package ci.digitalacademy.apigestiontasks.services.dto;

import ci.digitalacademy.apigestiontasks.models.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TasksDTO {

    private Long id;

    private String wording;

    private String delais;

    private String slug;

    private MemberDTO member;
}
