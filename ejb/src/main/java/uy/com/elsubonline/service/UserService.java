package uy.com.elsubonline.service;

import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Queue;
import org.apache.log4j.Logger;
import uy.com.elsubonline.api.IUserService;
import uy.com.elsubonline.domain.User;

public @Stateless class UserService implements IUserService {

	private final static Logger logger = Logger.getLogger(UserService.class);

	@javax.persistence.PersistenceContext(unitName="uy.com.elsubonline.persistence")
	private javax.persistence.EntityManager em;
        
        @Resource(name = "jms/elsubonline/registration")
        private static Queue registrationQueue;

	@Override
	public void create(String email, String nick_name, String first_name, String last_name, String password, String phone, boolean subscribed) {
		logger.info("UserService.create " + email);
		User user = new User();
		user.setEmail(email);
		user.setNick_name(nick_name);
		user.setFirst_name(first_name);
		user.setLast_name(last_name);
		user.setPassword(password);
		user.setPhone(phone);
		user.setSubscribed(subscribed);
		user.setCreation_date(new Date());
		em.persist(user);
                logger.info("UserService.created user: " + email);
	}
}
