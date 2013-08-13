package uy.com.elsubonline.web.user;

import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import uy.com.elsubonline.api.IUserService;
import uy.com.elsubonline.api.exceptions.InvalidCredentialsException;
import uy.com.elsubonline.api.exceptions.UserBannedException;
import uy.com.elsubonline.api.exceptions.UserUnconfirmedException;

@ManagedBean
@SessionScoped
public class Login {
    
    private static final Logger logger = Logger.getLogger(Login.class);

    @EJB
    private IUserService user;

    private String username;
    private String password;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String login() {
        logger.info("Trying to login: " + username);
        FacesMessage msg;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msg");

        try {
            user.validate_credentials(username, password);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("welcome"), username);
        } catch (InvalidCredentialsException ex) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, bundle.getString("err_login"), username);
        } catch (UserUnconfirmedException ex) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, bundle.getString("err_unconfirmed_user"), username);
        } catch (UserBannedException ex) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, bundle.getString("err_banned_user"), username);
        }

        facesContext.addMessage(null, msg);
        return null;
    }

    public String logout() {
        return null;
    }

}
