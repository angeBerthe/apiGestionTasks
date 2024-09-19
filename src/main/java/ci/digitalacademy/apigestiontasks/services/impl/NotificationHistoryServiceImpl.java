package ci.digitalacademy.apigestiontasks.services.impl;

import ci.digitalacademy.apigestiontasks.models.NotificationHistory;
import ci.digitalacademy.apigestiontasks.models.Project;
import ci.digitalacademy.apigestiontasks.repositories.NotificationHistoryRepository;
import ci.digitalacademy.apigestiontasks.services.NotificationHistoryService;
import ci.digitalacademy.apigestiontasks.services.dto.NotificationHistoryDTO;
import ci.digitalacademy.apigestiontasks.services.mapper.NotificationHistoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationHistoryServiceImpl implements NotificationHistoryService {

    private final NotificationHistoryRepository notificationHistoryRepository;
    private final NotificationHistoryMapper notificationHistoryMapper;
    @Override
    public NotificationHistoryDTO save(NotificationHistoryDTO notificationHistoryDTO) {
        log.debug("save to notification history {}", notificationHistoryDTO);
        NotificationHistory history = notificationHistoryMapper.toEntity(notificationHistoryDTO);
         history = notificationHistoryRepository.save(history);
        return notificationHistoryMapper.fromEntity(history);
    }

    @Override
    public List<NotificationHistoryDTO> findAll() {
        return notificationHistoryRepository.findAll().stream().map(notificationHistory -> {
            return notificationHistoryMapper.fromEntity(notificationHistory);
        }).toList();
    }

}
