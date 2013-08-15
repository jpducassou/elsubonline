package uy.com.elsubonline.service;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.log4j.Logger;
import uy.com.elsubonline.api.IAuctionService;
import uy.com.elsubonline.api.exceptions.ServiceException;
import uy.com.elsubonline.domain.Auction;
import uy.com.elsubonline.domain.Category;
import uy.com.elsubonline.domain.User;
import uy.com.elsubonline.persistence.IAuctionDAO;
import uy.com.elsubonline.persistence.ICategoryDAO;
import uy.com.elsubonline.persistence.IUserDAO;
import uy.com.elsubonline.persistence.PersistenceException;

public @Stateless class AuctionService implements IAuctionService {

    private final static Logger logger = Logger.getLogger(AuctionService.class);

    @EJB
    private ICategoryDAO categoryDAO;

    @EJB
    private IUserDAO userDAO;

    @EJB
    private IAuctionDAO auctionDAO;

    @Override
    public void create(String username, String title, String short_description, String long_description, double base_price, Date closing_time, String category_name) throws ServiceException {
        logger.info("AuctionService.create " + title);

        Category category;
        try {
            category = categoryDAO.retrieve(category_name);
        } catch (PersistenceException ex) {
            throw(new ServiceException("Category not found: " + category_name));
        }

        User user;
        try {
            user = userDAO.retrieve(username);
        } catch (PersistenceException ex) {
            throw(new ServiceException("User not found: " + username));
        }

        Auction auction = new Auction(title, short_description, long_description, base_price, closing_time, category, user);
        try {
            auctionDAO.create(auction);
        } catch (PersistenceException ex) {
            throw(new ServiceException("Error creating auction!"));
        }
        logger.info("AuctionService.created aution: " + title);
    }

}
