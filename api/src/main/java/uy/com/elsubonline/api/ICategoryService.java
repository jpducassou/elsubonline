package uy.com.elsubonline.api;

import java.util.List;
import javax.ejb.Local;

@Local
public interface ICategoryService {
    public List<String> list();
    public void create(String category_name);
    public void delete(String category_name);
    public void update(String old_category_name, String new_category_name);
}
