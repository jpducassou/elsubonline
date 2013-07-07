package uy.com.elsubonline.service;

import javax.ejb.Stateless;
import org.apache.log4j.Logger;
import uy.com.elsubonline.api.IUserService;

public @Stateless class UserService implements IUserService {

	private final static Logger logger = Logger.getLogger(UserService.class);

	@Override
	public void add(String email, String alias, String first_name, String last_name, String password, String phone, boolean subscribed) {
		logger.error("User.add called");
	}
}
