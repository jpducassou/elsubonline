package uy.com.elsubonline.persistence;

import java.util.List;
import uy.com.elsubonline.domain.Category;

public interface ICategoryDAO {
    public List<String> list();
    public void create(Category category) throws PersistenceException;
    public Category retrieve(String id) throws PersistenceException;
    public void update(String old_category_name, String new_category_name) throws PersistenceException;
}
