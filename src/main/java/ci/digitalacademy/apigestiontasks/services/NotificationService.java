package ci.digitalacademy.apigestiontasks.services;

import ci.digitalacademy.apigestiontasks.services.dto.NotificationDTO;
import ci.digitalacademy.apigestiontasks.services.dto.TasksDTO;

import java.util.List;
import java.util.Optional;

public interface NotificationService {

    NotificationDTO save(NotificationDTO notificationDTO);

    List<NotificationDTO> findAll();

    Optional<NotificationDTO> delete(Long id);

    NotificationDTO saveNotification(NotificationDTO notificationDTO);

    NotificationDTO createNotification(String wording, TasksDTO tasks);

    List<NotificationDTO> findByWording(String wording);
}
