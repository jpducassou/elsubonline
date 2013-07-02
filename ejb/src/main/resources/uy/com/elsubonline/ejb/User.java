package uy.com.elsubonline.ejb;

import javax.ejb.Stateless;
import uy.com.elsubonline.api.IUser;
import org.apache.log4j.Logger;

public @Stateless class User implements IUser {

	private final static Logger logger = Logger.getLogger(User.class);

	@Override
	public void add(String email, String alias, String first_name, String last_name, String password, String phone, boolean subscribed) {
		logger.error("User.add called");
	}
}
