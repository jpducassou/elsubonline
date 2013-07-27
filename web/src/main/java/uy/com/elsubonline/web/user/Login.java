package uy.com.elsubonline.web.user;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.apache.log4j.Logger;

@ManagedBean
@RequestScoped
public class Login {
    
    private static final Logger logger = Logger.getLogger(Registration.class);

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
    
    void login() {
        logger.info("Trying to login: " + username);
    }

}
