package ci.digitalacademy.apigestiontasks.web.resources;

import ci.digitalacademy.apigestiontasks.services.NotificationService;
import ci.digitalacademy.apigestiontasks.services.dto.NotificationDTO;
import ci.digitalacademy.apigestiontasks.services.dto.TasksDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationResource {

    private final NotificationService notificationService;

    @GetMapping
    @Operation(summary = "get all notification", description = "this endpoint allow to get all notification")
    public List<NotificationDTO> getAllNotifications() {
        log.info("REST Request to get all notifications");
        return notificationService.findAll();
    }

    @PostMapping("/create")
    @Operation(summary = "create notification", description = "this endpoint allow to get create notification")
    public NotificationDTO createNotification(@RequestBody TasksDTO tasks, @RequestParam String operation) {
        log.debug("Creating notification for operation: {}", operation);
        NotificationDTO notification = new NotificationDTO();
        notification.setDate(Instant.now());

        if (tasks != null) {
            notification.setTasks(tasks);
        }

        switch (operation) {
            case "save":
                notification.setWording("Task has been created");
                break;
            case "update":
                notification.setWording("Task has been updated");
                break;
            case "delete":
                notification.setWording("Task has been deleted");
                break;
            default:
                notification.setWording("Task action performed");
                break;
        }

        return notificationService.save(notification);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "delete notification", description = "this endpoint allow to get delete notification")
    public ResponseEntity<?> deleteNotification(@PathVariable Long id) {
        log.debug("REST Request to delete Notification : {}", id);
        Optional<NotificationDTO> notification = notificationService.delete(id);
        if (notification.isPresent()) {
            log.info("Notification with id {} deleted successfully", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            log.warn("Notification with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "get notification by id", description = "this endpoint allow to get notification by id")
    public ResponseEntity<NotificationDTO> getNotificationById(@PathVariable Long id) {
        log.debug("REST Request to get Notification by id : {}", id);
        Optional<NotificationDTO> notification = notificationService.delete(id);
        return notification.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/wording/{wording}")
    @Operation(summary = "get notification by wording", description = "this endpoint allow to get get notification by wording")
    public ResponseEntity<List<NotificationDTO>> getNotificationsByWording(@PathVariable String wording) {
        log.debug("REST Request to get Notifications by wording : {}", wording);
        List<NotificationDTO> notifications = notificationService.findByWording(wording);
        if (!notifications.isEmpty()) {
            return new ResponseEntity<>(notifications, HttpStatus.OK);
        } else {
            log.warn("No notifications found for wording: {}", wording);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
