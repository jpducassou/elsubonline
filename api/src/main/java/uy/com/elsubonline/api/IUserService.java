package uy.com.elsubonline.api;

import javax.ejb.Local;
import uy.com.elsubonline.api.exceptions.AddressAlreadyInUseException;
import uy.com.elsubonline.api.exceptions.NotificationException;

@Local
public interface IUserService {
	public void create(String email, String alias, String first_name, String last_name, String password, String phone, boolean subscribed) throws AddressAlreadyInUseException, NotificationException;
        public boolean validate_credentials(String username, String password);
}
