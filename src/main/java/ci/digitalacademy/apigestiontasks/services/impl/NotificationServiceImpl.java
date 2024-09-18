package ci.digitalacademy.apigestiontasks.services.impl;

import ci.digitalacademy.apigestiontasks.models.Notification;
import ci.digitalacademy.apigestiontasks.repositories.NotificationRepository;
import ci.digitalacademy.apigestiontasks.services.NotificationService;
import ci.digitalacademy.apigestiontasks.services.dto.NotificationDTO;
import ci.digitalacademy.apigestiontasks.services.dto.TasksDTO;
import ci.digitalacademy.apigestiontasks.services.mapper.NotificationMapper;
import ci.digitalacademy.apigestiontasks.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @Override
    public NotificationDTO save(NotificationDTO notificationDTO) {
        log.debug("Request to save Notification : {}", notificationDTO);
        Notification notification = notificationMapper.toEntity(notificationDTO);
        notification = notificationRepository.save(notification);
        return notificationMapper.fromEntity(notification);
    }

    @Override
    public List<NotificationDTO> findAll() {
        return notificationRepository.findAll().stream().map(notification -> {
            log.debug("Request to get Tasks : {}", notification);
            return notificationMapper.fromEntity(notification);
        }).toList();
    }

    @Override
    public Optional<NotificationDTO> delete(Long id) {
        log.debug("Resquest to get id: {}", id);
        return notificationRepository.findById(id).map(notification -> {
            return notificationMapper.fromEntity(notification);
        });
    }

    @Override
    public NotificationDTO saveNotification(NotificationDTO notificationDTO) {
        log.debug("Request to save Tasks {} with slug", notificationDTO);
        final String slug = SlugifyUtils.generate(String.valueOf(notificationDTO.getWording()));
        notificationDTO.setSlug(slug);
        return save(notificationDTO);
    }

    @Override
    public NotificationDTO createNotification(String wording, TasksDTO tasks) {
        log.debug("Creating notification for task: {}", tasks);

        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setWording(wording);
        notificationDTO.setDate(Instant.now());
        notificationDTO.setTasks(tasks);

        // Générer un slug unique
        final String slug = SlugifyUtils.generate(wording);
        notificationDTO.setSlug(slug);

        return save(notificationDTO);
    }

    @Override
    public List<NotificationDTO> findByWording(String wording) {
        log.debug("Request to find Notifications by wording : {}", wording);
        List<Notification> notifications = notificationRepository.findByWording(wording);
        return notifications.stream()
                .map(notificationMapper::fromEntity)
                .toList();
    }

}
