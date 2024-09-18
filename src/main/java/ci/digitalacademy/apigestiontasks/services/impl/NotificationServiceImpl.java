package ci.digitalacademy.apigestiontasks.services.impl;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;




@Slf4j
@Service
public class NotificationServiceImpl  {

    private final JavaMailSender javaMailSender;

    public NotificationServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendNotificationMail(Code validation) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);

            helper.setFrom("angeseugban2000@gmail.com");
            helper.setSubject("Votre code d'activation");

            String content = "<html>" +
                    "<body>" +
                    "    <div style=\"font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #edf2f7; padding: 20px; text-align: center;\">" +
                    "        <div style=\"background-color: #ffffff; width: 100%; max-width: 480px; margin: auto; box-shadow: 0 8px 16px rgba(0,0,0,0.1); border-radius: 10px; overflow: hidden; border-left: 5px solid #4a90e2;\">" +
                    "            <div style=\"background-color: #4a90e2; color: white; padding: 20px; font-size: 18px; text-align: center;\">Authentification à double facteur</div>" +
                    "            <div style=\"padding: 20px; color: #333333; line-height: 1.6; text-align: center;\">" +
                    "                Merci d'utiliser l'authentification à double facteur pour sécuriser votre connexion. Voici votre code de vérification :<br>" +
                    "                <div style=\"font-size: 24px; font-weight: bold; background-color: #E8F0FE; color: #4a90e2; padding: 10px 20px; border-radius: 8px; display: inline-block; margin: 20px 0;\">" +
                    "                    " + validation.getCode() +
                    "                </div>" +
                    "                Ce code est valable pour une seule utilisation. Si vous ne l'avez pas demandé, veuillez ignorer cet e-mail." +
                    "            </div>" +
                    "            <div style=\"background-color: #f7f7f7; color: #666666; text-align: center; padding: 12px 20px; font-size: 14px;\">© 2024 Votre Entreprise. Tous droits réservés.</div>" +
                    "        </div>" +
                    "    </div>" +
                    "</body>" +
                    "</html>";


            helper.setText(content, true);
            javaMailSender.send(mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
