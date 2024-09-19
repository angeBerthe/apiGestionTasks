package ci.digitalacademy.apigestiontasks.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "notification_history")
public class NotificationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String notification_type;
    private String recipient_email;
    private String subject ;
    private String status;
    private String related_entity_type;
    private String error_message;


}
