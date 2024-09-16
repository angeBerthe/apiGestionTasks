package ci.digitalacademy.apigestiontasks.services.dto;



import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class NotificationDTO {

    private Long id;

    private String wording;

    private Instant date;

    private String slug;
}
