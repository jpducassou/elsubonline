package uy.com.elsubonline.web.user;

import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import uy.com.elsubonline.api.IUserService;
import uy.com.elsubonline.api.exceptions.ServiceException;

@ManagedBean
@RequestScoped
public class Confirmation {

    private static final Logger logger = Logger.getLogger(Confirmation.class);

    @EJB
    private IUserService user;

    private String username;
    private String confirmation_code;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getConfirmation_code() {
        return confirmation_code;
    }

    public void setConfirmation_code(String confirmation_code) {
        this.confirmation_code = confirmation_code;
    }

    public String confirm() {

        logger.info("Trying to confirm: " + username);

        FacesMessage msg;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msg");

        try {
            user.confirm(username, confirmation_code);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("msg_user_confirmed"), username);
        } catch (ServiceException ex) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, bundle.getString("err_confirm"), username);
        }

        facesContext.addMessage(null, msg);
        return "/index.xhtml";
    }

}
