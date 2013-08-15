package uy.com.elsubonline.api;

import java.util.List;
import javax.ejb.Local;
import uy.com.elsubonline.api.exceptions.ServiceException;

@Local
public interface ICategoryService {
    public List<String> list();
    public void create(String category_name) throws ServiceException;
    public void update(String old_category_name, String new_category_name) throws ServiceException;
}
