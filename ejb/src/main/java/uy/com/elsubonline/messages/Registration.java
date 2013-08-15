package uy.com.elsubonline.messages;

import java.io.IOException;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;
import uy.com.elsubonline.domain.User;
import uy.com.elsubonline.persistence.IUserDAO;
import uy.com.elsubonline.persistence.PersistenceException;

@MessageDriven(
    activationConfig={@ActivationConfigProperty(propertyName="destination", propertyValue="java:/jms/elsubonline/registration")}
)
public class Registration implements MessageListener {

    private final static Logger logger = Logger.getLogger(Registration.class);
    private static Properties config;
    @EJB
    private IUserDAO userDAO;

    @Override
    public void onMessage(Message queueMessage) {

        logger.info("Message arrived: " + queueMessage);

        TextMessage notificationMessage = (TextMessage)queueMessage;
        String notificationEmail;
        try {
            notificationEmail = notificationMessage.getText();
        } catch (JMSException ex) {
            logger.error("Error getting message text. Discarding.");
            return;
        }

        final String username = config.getProperty("mail.username");
        final String password = config.getProperty("mail.password");

        Session session = Session.getInstance(config,
            new javax.mail.Authenticator() {
                  @Override
                  protected PasswordAuthentication getPasswordAuthentication() {
                          return new PasswordAuthentication(username, password);
                  }
            }
        );

        // Get the user
        User user;
        try {
            user = userDAO.retrieve(notificationEmail);
        } catch (PersistenceException ex) {
            logger.error("User not found " + notificationEmail);
            return;
        }

        // Get the confirmation code.
        String confirmation_code = user.getHashCode();

        try {

            MimeMessage emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(username));
            emailMessage.setRecipients(MimeMessage.RecipientType.TO,
                    InternetAddress.parse(notificationEmail));

            emailMessage.setSubject(config.getProperty("mail.subject"));

            emailMessage.setText("Dear " + user.getFirst_name() + " " + user.getLast_name() + ": "
                    + "\n\n Your activation code is: " + confirmation_code);

            Transport.send(emailMessage);

            logger.info("Email sent to: " + notificationEmail);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    private void init() {
        config = new Properties();

        try {
            //load a properties file from class path, inside static method
            config.load(Registration.class.getClassLoader().getResourceAsStream("email_config.properties"));

            //get the property value and print it out3
            logger.info("Mail through host: " + config.getProperty("mail.smtp.host"));
 
    	} catch (IOException ex) {
            logger.error("Error loading email config");
        }
    }
}
