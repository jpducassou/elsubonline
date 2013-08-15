package uy.com.elsubonline.service;

import javax.annotation.Resource;
import javax.ejb.EJB;
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
import uy.com.elsubonline.api.dtos.UserDto;
import uy.com.elsubonline.api.exceptions.AlreadyRegisteredException;
import uy.com.elsubonline.api.exceptions.InvalidCredentialsException;
import uy.com.elsubonline.api.exceptions.NotificationException;
import uy.com.elsubonline.api.exceptions.ServiceException;
import uy.com.elsubonline.api.exceptions.UserBannedException;
import uy.com.elsubonline.api.exceptions.UserUnconfirmedException;
import uy.com.elsubonline.domain.User;
import uy.com.elsubonline.domain.UserStatus;
import uy.com.elsubonline.persistence.IUserDAO;
import uy.com.elsubonline.persistence.PersistenceException;

public @Stateless class UserService implements IUserService {

    private final static Logger logger = Logger.getLogger(UserService.class);

    @EJB
    private IUserDAO userDAO;

    @Resource(name = "java:/ConnectionFactory")
    private static ConnectionFactory connectionFactory;

    @Resource(name = "java:/jms/elsubonline/registration")
    private static Queue registrationQueue;

    @Override
    public void create(String email, String nick_name, String first_name, String last_name, String password, String phone, boolean subscribed) throws AlreadyRegisteredException, NotificationException {

        logger.info("UserService.create " + email);

        User user = new User(email, nick_name, first_name, last_name, password, phone, subscribed);
        
        try {
            userDAO.create(user);
        } catch (PersistenceException ex) {
            throw(new AlreadyRegisteredException(ex.getMessage()));
        }

        logger.info("UserService.created user: " + email);

        // Create the needed artifacts to connect to the queue
        Connection connection;
        try {
            connection = connectionFactory.createConnection();
        } catch (JMSException ex) {
            logger.error("Error trying to createConnection", ex);
            throw(new NotificationException(email));
        }
        Session session;
        try {
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException ex) {
            logger.error("Error trying to createSession", ex);
            throw(new NotificationException(email));
        }
        MessageProducer producer;
        try {
            producer = session.createProducer(registrationQueue);
        } catch (JMSException ex) {
            logger.error("Error trying to createProducer", ex);
            throw(new NotificationException(email));
        }

        // Send text message to the queue
        TextMessage message;
        try {
            message = session.createTextMessage();
        } catch (JMSException ex) {
            logger.error("Error trying to createTextMessage", ex);
            throw(new NotificationException(email));
        }
        try {
            message.setText(email);
        } catch (JMSException ex) {
            logger.error("Error trying to setText", ex);
            throw(new NotificationException(email));
        }
        try {
            producer.send(message);
        } catch (JMSException ex) {
            logger.error("Error trying to send", ex);
            throw(new NotificationException(email));
        }
        try {
            connection.close();
        } catch (JMSException ex) {
            throw(new NotificationException(email));
        }
        logger.info("UserService.created sent to notification queue: " + email);

    }
    
    @Override
    public UserDto validate_credentials(String username, String password) throws InvalidCredentialsException, UserUnconfirmedException, UserBannedException {

        password = "SHA1:" + DigestUtils.shaHex(password);
        
        try {
            UserDto userDto = userDAO.validate_credentials(username, password);
            logger.info("Got user:" + userDto.getEmail());
            return userDto;
        } catch (PersistenceException ex) {
            throw(new InvalidCredentialsException());
        }

    }

    @Override
    public void confirm(String username, String confirmation_code) throws ServiceException {

        User user;

        // Find user info
        try {
            user = userDAO.retrieve(username);
        } catch (PersistenceException ex) {
            logger.error("User " + username + " not found for confirmation!");
            throw(new ServiceException());
        }

        // Validate user state
        if (user.getStatus() != UserStatus.NEW) {
            logger.error("User " + username + " is not a fresh one!");
            throw(new ServiceException("Invalid user state!"));
        }

        // Validate confirmation_code
        if (!user.getHashCode().equals(confirmation_code)) {
            logger.error("User " + username + " has invalid confirmation code!");
            throw(new ServiceException("Invalid confirmation code!"));
        }

        // Change state
        user.setStatus(UserStatus.ACTIVE);
        try {
            userDAO.update(user);
        } catch (PersistenceException ex) {
            logger.error("Cannot update status of user " + username);
        }
    }
}
