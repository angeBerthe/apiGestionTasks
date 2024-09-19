package ci.digitalacademy.apigestiontasks.services.impl;


import ci.digitalacademy.apigestiontasks.models.NotificationHistory;
import ci.digitalacademy.apigestiontasks.repositories.NotificationHistoryRepository;
import ci.digitalacademy.apigestiontasks.services.NotificationMailService;
import ci.digitalacademy.apigestiontasks.services.dto.MemberDTO;
import ci.digitalacademy.apigestiontasks.services.dto.TasksDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;




@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationMailServiceImpl implements NotificationMailService {

    private final JavaMailSender javaMailSender;
    private final NotificationHistoryRepository notificationHistoryRepository;

    public void sendNoficationMailAddMemberByTeam(MemberDTO memberDTO) {
        NotificationHistory history = new NotificationHistory();
        history.setNotification_type("ADD_MEMBER_TO_TEAM");
        history.setRecipient_email(memberDTO.getEmail());
        history.setSubject("Notification d'ajout à une équipe");

        try {
            if (memberDTO.getEmail() == null || memberDTO.getEmail().isEmpty()) {
                throw new IllegalArgumentException("L'email du membre est null ou vide");
            }

            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);

            helper.setFrom("angeseugban2000@gmail.com");
            helper.setTo(memberDTO.getEmail());
            helper.setSubject(history.getSubject());


            String content = "<html>" +
                    "<body>" +
                    "    <div style=\"font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #edf2f7; padding: 20px; text-align: center;\">" +
                    "        <div style=\"background-color: #ffffff; width: 100%; max-width: 480px; margin: auto; box-shadow: 0 8px 16px rgba(0,0,0,0.1); border-radius: 10px; overflow: hidden; border-left: 5px solid #4a90e2;\">" +
                    "            <div style=\"background-color: #FFA500; color: white; padding: 20px; font-size: 18px; text-align: center;\">Notification d'ajout à une équipe</div>" +
                    "            <div style=\"padding: 20px; color: #333333; line-height: 1.6; text-align: center;\">" +
                    "                Bonjour <strong>" + memberDTO.getFirstName() + " " + memberDTO.getLastName() + "</strong>,<br><br>" +
                    "                Vous avez été ajouté à l'équipe:<br>" +
                    "                <div style=\"font-size: 16px; margin: 20px 0;\">" +
                    "                    <strong>Nom équipe : </strong>" + memberDTO.getTeam().getNameTeam() + "<br>" +
                    "                    <strong>Description : </strong>" + memberDTO.getTeam().getDescription() + "<br>" +
                    "                </div>" +
                    "                <br>" +
                    "            <div style=\"background-color: #9bbbcc; color: #666666; text-align: center; padding: 12px 20px; font-size: 14px;\">© 2024 Equipe Atos, Tous droits réservés.</div>" +
                    "        </div>" +
                    "    </div>" +
                    "</body>" +
                    "</html>";
            helper.setText(content, true);
            history.setStatus("SUCCESS");
            history.setRelated_entity_type("TEAM");

            javaMailSender.send(mail);
            log.info("Email envoyé avec succès à {}", memberDTO.getEmail());
        } catch (MessagingException | IllegalArgumentException e) {
            history.setStatus("FAILED");
            history.setError_message(e.getMessage());
            log.error("Échec de l'envoi de l'email à {}: {}", memberDTO.getEmail(), e.getMessage());
        } finally {
            notificationHistoryRepository.save(history);
        }
    }


    @Override
    public void sendNoficationMailAttributeTaskByMember(TasksDTO tasksDTO) {

        NotificationHistory history = new NotificationHistory();
        history.setNotification_type("ATTRIBUTE_TASK_TO_MEMBER");
        history.setRecipient_email(tasksDTO.getMember().getEmail());
        history.setSubject("Attribution d'une tâche à un membre");

        try {
            if (tasksDTO.getMember().getEmail() == null || tasksDTO.getMember().getEmail().isEmpty()) {
                throw new IllegalArgumentException("L'email du membre est null ou vide");
            }

            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);

            helper.setFrom("angeseugban2000@gmail.com");
            helper.setTo(tasksDTO.getMember().getEmail());
            helper.setSubject(history.getSubject());

            String content = "<html>" +
                    "<body>" +
                    "    <div style=\"font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #edf2f7; padding: 20px; text-align: center;\">" +
                    "        <div style=\"background-color: #ffffff; width: 100%; max-width: 480px; margin: auto; box-shadow: 0 8px 16px rgba(0,0,0,0.1); border-radius: 10px; overflow: hidden; border-left: 5px solid #4a90e2;\">" +
                    "            <div style=\"background-color: #FFA500; color: white; padding: 20px; font-size: 18px; text-align: center;\">Attribution d'une tâche à un membre</div>" +
                    "            <div style=\"padding: 20px; color: #e0d5d5; line-height: 1.6; text-align: center;\">" +
                    "                Bonjour <strong>" + tasksDTO.getMember().getFirstName() + " " + tasksDTO.getMember().getLastName() + "</strong>,<br><br>" +
                    "                Vous avez la charge de la tâche<br>" +
                    "                <div style=\"font-size: 16px; margin: 20px 0;\">" +
                    "                    <strong>Libellé : </strong>" + tasksDTO.getWording() + "<br>" +
                    "                </div>" +
                    "                <br>" +
                    "            <div style=\"background-color: #9bbbcc; color: #666666; text-align: center; padding: 12px 20px; font-size: 14px;\">© 2024 Equipe Atos, Tous droits réservés.</div>" +
                    "        </div>" +
                    "    </div>" +
                    "</body>" +
                    "</html>";

            helper.setText(content, true);
            history.setStatus("SUCCESS");
            history.setRelated_entity_type("TASK");

            javaMailSender.send(mail);
            log.info("Email envoyé avec succès à {}", tasksDTO.getMember().getEmail());
        } catch (MessagingException | IllegalArgumentException e) {
            history.setStatus("FAILED");
            history.setError_message(e.getMessage());
            log.error("Échec de l'envoi de l'email à {}: {}", tasksDTO.getMember().getEmail(), e.getMessage());
        } finally {
            notificationHistoryRepository.save(history);
        }
    }
}
