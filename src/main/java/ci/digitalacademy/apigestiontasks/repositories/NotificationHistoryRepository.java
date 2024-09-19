package ci.digitalacademy.apigestiontasks.repositories;

import ci.digitalacademy.apigestiontasks.models.NotificationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationHistoryRepository extends JpaRepository<NotificationHistory, Long> {
}
