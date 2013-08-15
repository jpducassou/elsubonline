package uy.com.elsubonline.service;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.log4j.Logger;
import uy.com.elsubonline.api.ICategoryService;
import uy.com.elsubonline.api.exceptions.ServiceException;
import uy.com.elsubonline.domain.Category;
import uy.com.elsubonline.persistence.ICategoryDAO;
import uy.com.elsubonline.persistence.PersistenceException;

public @Stateless class CategoryService implements ICategoryService {

    private final static Logger logger = Logger.getLogger(CategoryService.class);

    @EJB
    private ICategoryDAO categoryDAO;

    @Override
    public List<String> list() {
        logger.info("CategoryService.list");
        return categoryDAO.list();
    }

    @Override
    public void create(String category_name) throws ServiceException {
        logger.info("CategoryService.create");
        Category new_category = new Category();
        new_category.setName(category_name);
        try {
            categoryDAO.create(new_category);
        } catch (PersistenceException ex) {
            logger.error("CategoryService.create failed!");
            throw(new ServiceException("Category update failed!"));
        }
    }

    @Override
    public void update(String old_category_name, String new_category_name) throws ServiceException {
        logger.info("CategoryService.update");
        try {
            categoryDAO.update(old_category_name, new_category_name);
        } catch (PersistenceException ex) {
            logger.error("CategoryService.update failed!");
            throw(new ServiceException("Category update failed!"));
        }

    }

}
