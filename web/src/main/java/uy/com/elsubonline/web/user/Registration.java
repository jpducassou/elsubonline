package uy.com.elsubonline.web.user;

import java.io.Serializable;
import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.apache.log4j.Logger;
import uy.com.elsubonline.api.IUserService;

/**
 *
 * @author jean
 */
@ManagedBean
@RequestScoped
public class Registration implements Serializable {

    private static final long   serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(Registration.class);

    @EJB
    private IUserService user;

    private String first_name;
    private String last_name;
    private String email;
    private String alias;
    private String password;
    private String phone;
    private boolean subscribed;

    /**
     * Creates a new instance of Registration
     */
    public Registration() {
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    /**
     * This method process the registration process
     * @return outcome
     */
    public String register() {
        logger.info("Trying to register: " + email);
        user.add(email, alias, first_name, last_name, password, phone, subscribed);
        return "home";
    }

}
