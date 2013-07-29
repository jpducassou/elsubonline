package uy.com.elsubonline.service;

import javax.ejb.Stateless;
import org.apache.log4j.Logger;
import uy.com.elsubonline.api.ICategoryService;
import uy.com.elsubonline.domain.Category;

public @Stateless class CategoryService implements ICategoryService {

    private final static Logger logger = Logger.getLogger(CategoryService.class);

    @javax.persistence.PersistenceContext(unitName="uy.com.elsubonline.persistence")
    private javax.persistence.EntityManager em;

    @Override
    public void create(String category_name) {
        logger.info("CategoryService.create");
        Category new_category = new Category();
        new_category.setName(category_name);
        em.persist(new_category);
    }

    @Override
    public void delete(String category) {
        logger.info("CategoryService.delete");
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
