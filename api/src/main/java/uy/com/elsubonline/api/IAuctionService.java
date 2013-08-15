package uy.com.elsubonline.api;

import javax.ejb.Local;
import uy.com.elsubonline.api.exceptions.ServiceException;

@Local
public interface IAuctionService {
    public void create(String title, String short_description, String long_description, double base_price) throws ServiceException;
}
