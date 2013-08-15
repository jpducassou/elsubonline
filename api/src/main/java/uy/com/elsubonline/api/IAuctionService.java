package uy.com.elsubonline.api;

import java.util.Date;
import javax.ejb.Local;
import uy.com.elsubonline.api.exceptions.ServiceException;

@Local
public interface IAuctionService {
    public void create(String title, String short_description, String long_description, double base_price, Date closing_time, String category_name) throws ServiceException;
}
