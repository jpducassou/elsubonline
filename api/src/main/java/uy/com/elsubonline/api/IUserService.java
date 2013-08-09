package uy.com.elsubonline.api;

import javax.ejb.Local;

@Local
public interface IUserService {
	public void create(String email, String alias, String first_name, String last_name, String password, String phone, boolean subscribed);
        public boolean validate_credentials(String username, String password);
}
