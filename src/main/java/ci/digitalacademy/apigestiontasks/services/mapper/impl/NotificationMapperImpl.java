package ci.digitalacademy.apigestiontasks.services.mapper.impl;

import ci.digitalacademy.apigestiontasks.models.Notification;
import ci.digitalacademy.apigestiontasks.models.Project;
import ci.digitalacademy.apigestiontasks.services.dto.NotificationDTO;
import ci.digitalacademy.apigestiontasks.services.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class NotificationMapperImpl implements NotificationMapper {

    private final ModelMapper modelMapper;
    @Override
    public NotificationDTO fromEntity(Notification entity) {
        log.debug("Mapping to Notification to NotificationDTO");
        return modelMapper.map(entity, NotificationDTO.class);
    }

    @Override
    public Notification toEntity(NotificationDTO dto) {
        log.debug("Mapping NotficationDTO  to Notification");
        return modelMapper.map(dto, Notification.class);
    }
}
