package uy.com.elsubonline.service;

import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import uy.com.elsubonline.api.IUserService;
import uy.com.elsubonline.domain.User;

public @Stateless class UserService implements IUserService {

    private final static Logger logger = Logger.getLogger(UserService.class);

    @javax.persistence.PersistenceContext(unitName="uy.com.elsubonline.persistence")
    private javax.persistence.EntityManager em;

    @Resource(name = "java:/ConnectionFactory")
    private static ConnectionFactory connectionFactory;

    @Resource(name = "java:/jms/elsubonline/registration")
    private static Queue registrationQueue;

    @Override
    public void create(String email, String nick_name, String first_name, String last_name, String password, String phone, boolean subscribed) {
        logger.info("UserService.create " + email);
        User user = new User();
        user.setEmail(email);
        user.setNick_name(nick_name);
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setPhone(phone);
        user.setSubscribed(subscribed);
        user.setPassword("SHA1:" + DigestUtils.shaHex(password));
        
        user.setCreation_time(new Date());

        em.persist(user);
        logger.info("UserService.created user: " + email);

        // Create the needed artifacts to connect to the queue
        Connection connection;
        try {
            connection = connectionFactory.createConnection();
        } catch (JMSException ex) {
            logger.error("Error trying to createConnection", ex);
            return;
        }
        Session session;
        try {
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException ex) {
            logger.error("Error trying to createSession", ex);
            return;
        }
        MessageProducer producer;
        try {
            producer = session.createProducer(registrationQueue);
        } catch (JMSException ex) {
            logger.error("Error trying to createProducer", ex);
            return;
        }

        // Send text message to the queue
        TextMessage message;
        try {
            message = session.createTextMessage();
        } catch (JMSException ex) {
            logger.error("Error trying to createTextMessage", ex);
            return;
        }
        try {
            message.setText("Hola mundo");
        } catch (JMSException ex) {
            logger.error("Error trying to setText", ex);
            return;
        }
        try {
            producer.send(message);
        } catch (JMSException ex) {
            logger.error("Error trying to send", ex);
            return;
        }
        try {
            connection.close();
        } catch (JMSException ex) {
            logger.error("Error trying to close", ex);
        }
        logger.info("UserService.created sent to notification queue: " + email);

    }
}
