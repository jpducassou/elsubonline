package uy.com.elsubonline.messages;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import org.apache.log4j.Logger;

@MessageDriven(
    activationConfig={@ActivationConfigProperty(propertyName="destination", propertyValue="java:/jms/elsubonline/registration")}
)
public class Registration implements MessageListener {

    private final static Logger logger = Logger.getLogger(Registration.class);

    @Override
    public void onMessage(Message message) {
        logger.info("Message arrived: " + message);
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
