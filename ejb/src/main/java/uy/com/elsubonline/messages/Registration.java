package uy.com.elsubonline.messages;

import java.util.Properties;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

@MessageDriven(
    activationConfig={@ActivationConfigProperty(propertyName="destination", propertyValue="java:/jms/elsubonline/registration")}
)
public class Registration implements MessageListener {

    private final static Logger logger = Logger.getLogger(Registration.class);

    @Override
    public void onMessage(Message message) {
        logger.info("Message arrived: " + message);
        // String notificationEmail = (TextMessage)message.get

        final String username = "from@gmail.com";
        final String password = "password";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                  @Override
                  protected PasswordAuthentication getPasswordAuthentication() {
                          return new PasswordAuthentication(username, password);
                  }
            }
        );
 
        try {

            MimeMessage emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress("from@gmail.com"));
            emailMessage.setRecipients(MimeMessage.RecipientType.TO,
                    InternetAddress.parse("to@gmail.com"));
            emailMessage.setSubject("Testing Subject");
            emailMessage.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            Transport.send(emailMessage);

            logger.info("Email sent to: " + message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
