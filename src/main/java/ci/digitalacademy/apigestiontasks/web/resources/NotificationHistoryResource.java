package ci.digitalacademy.apigestiontasks.web.resources;

import ci.digitalacademy.apigestiontasks.services.NotificationHistoryService;
import ci.digitalacademy.apigestiontasks.services.dto.MemberDTO;
import ci.digitalacademy.apigestiontasks.services.dto.NotificationHistoryDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/historys")
public class NotificationHistoryResource {

    private final NotificationHistoryService notificationHistoryService;

    @GetMapping
    @Operation(summary = "get all member", description = "this endpoint allow to get all member")
    public List<NotificationHistoryDTO> getAllNotificationHistory(){
        log.debug("REST Request to all member ");
        return notificationHistoryService.findAll();
    }
}
