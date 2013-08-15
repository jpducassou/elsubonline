package uy.com.elsubonline.api;

import javax.ejb.Local;
import uy.com.elsubonline.api.dtos.UserDto;
import uy.com.elsubonline.api.exceptions.AlreadyRegisteredException;
import uy.com.elsubonline.api.exceptions.InvalidCredentialsException;
import uy.com.elsubonline.api.exceptions.NotificationException;
import uy.com.elsubonline.api.exceptions.ServiceException;
import uy.com.elsubonline.api.exceptions.UserBannedException;
import uy.com.elsubonline.api.exceptions.UserUnconfirmedException;

@Local
public interface IUserService {
    public void create(String email, String alias, String first_name, String last_name, String password, String phone, boolean subscribed) throws AlreadyRegisteredException, NotificationException;
    public UserDto validate_credentials(String username, String password) throws InvalidCredentialsException, UserUnconfirmedException, UserBannedException;
    public void confirm(String username, String confirmation_code) throws ServiceException;
}
