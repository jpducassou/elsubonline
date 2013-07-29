package uy.com.elsubonline.api;

import javax.ejb.Local;

@Local
public interface ICategoryService {
    public void create(String category_name);
    public void delete(String category_name);
}
