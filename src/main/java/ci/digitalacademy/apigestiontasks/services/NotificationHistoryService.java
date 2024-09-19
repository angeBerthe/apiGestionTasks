package ci.digitalacademy.apigestiontasks.services;

import ci.digitalacademy.apigestiontasks.services.dto.NotificationHistoryDTO;

import java.util.List;

public interface NotificationHistoryService {

    NotificationHistoryDTO save(NotificationHistoryDTO notificationHistoryDTO);

    List<NotificationHistoryDTO> findAll();


}
