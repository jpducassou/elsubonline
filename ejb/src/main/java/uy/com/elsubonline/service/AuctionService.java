package uy.com.elsubonline.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.log4j.Logger;
import uy.com.elsubonline.api.IAuctionService;
import uy.com.elsubonline.api.exceptions.ServiceException;
import uy.com.elsubonline.domain.Auction;
import uy.com.elsubonline.persistence.IAuctionDAO;
import uy.com.elsubonline.persistence.PersistenceException;

public @Stateless class AuctionService implements IAuctionService {

    private final static Logger logger = Logger.getLogger(AuctionService.class);

    @EJB
    private IAuctionDAO auctionDAO;

    @Override
    public void create(String title, String short_description, String long_description, double base_price) throws ServiceException {
        logger.info("AuctionService.create " + title);
        Auction auction = new Auction(title, short_description, long_description, base_price);
        try {
            auctionDAO.create(auction);
        } catch (PersistenceException ex) {
            throw(new ServiceException("Error creating auction!"));
        }
        logger.info("AuctionService.created aution: " + title);
    }

}
