package uy.com.elsubonline.api;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ICategoryService {
    public List<String> list();
    public void create(String category_name);
    public void delete(String category_name);
    public void update(String old_category_name, String new_category_name);
}
