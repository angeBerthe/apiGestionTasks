package ci.digitalacademy.apigestiontasks.services;

import ci.digitalacademy.apigestiontasks.services.dto.NotificationDTO;
import ci.digitalacademy.apigestiontasks.services.dto.ProjectDTO;

import java.util.List;

public interface NotificationService {

    NotificationDTO save(NotificationDTO notificationDTO);

    List<NotificationDTO> findAll();

    void delete(Long id);

    NotificationDTO saveProject(ProjectDTO projectDTO);
}
