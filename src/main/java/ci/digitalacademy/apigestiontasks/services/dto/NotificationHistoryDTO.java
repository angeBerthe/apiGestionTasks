package ci.digitalacademy.apigestiontasks.services.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class NotificationHistoryDTO {


    private Long id;


    private String notification_type;
    private String recipient_email;
    private String subject ;
    private String status;
    private String related_entity_type;
    private String error_message;
}
