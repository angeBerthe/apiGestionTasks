package ci.digitalacademy.apigestiontasks.services.mapper.impl;

import ci.digitalacademy.apigestiontasks.models.NotificationHistory;
import ci.digitalacademy.apigestiontasks.services.dto.NotificationHistoryDTO;
import ci.digitalacademy.apigestiontasks.services.mapper.NotificationHistoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class NotificationHistoryMapperImpl implements NotificationHistoryMapper {


    private final ModelMapper modelMapper;
    @Override
    public NotificationHistoryDTO fromEntity(NotificationHistory entity) {
        return modelMapper.map(entity, NotificationHistoryDTO.class);
    }

    @Override
    public NotificationHistory toEntity(NotificationHistoryDTO dto) {
        return modelMapper.map(dto, NotificationHistory.class);
    }
}
