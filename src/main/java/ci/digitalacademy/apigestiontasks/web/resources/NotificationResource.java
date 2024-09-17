package ci.digitalacademy.apigestiontasks.web.resources;

import ci.digitalacademy.apigestiontasks.services.NotificationService;
import ci.digitalacademy.apigestiontasks.services.dto.NotificationDTO;
import ci.digitalacademy.apigestiontasks.services.dto.ProjectDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationResource {

    private final NotificationService notificationService;

    @GetMapping
    public List<NotificationDTO> getAllNotification(){
        log.info("REST Request to get all notification");
        return notificationService.findAll();
    }
}
