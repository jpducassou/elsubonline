package uy.com.elsubonline.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import uy.com.elsubonline.api.ICategoryService;
import uy.com.elsubonline.domain.Category;

public @Stateless class CategoryService implements ICategoryService {

    private final static Logger logger = Logger.getLogger(CategoryService.class);

    @javax.persistence.PersistenceContext(unitName="uy.com.elsubonline.persistence")
    private javax.persistence.EntityManager em;

    @Override
    public List<String> list() {
        logger.info("CategoryService.list");
        Query query = em.createQuery("select c.name from Category c");
        return (List<String>)query.getResultList();
    }

    @Override
    public void create(String category_name) {
        logger.info("CategoryService.create");
        Category new_category = new Category();
        new_category.setName(category_name);
        em.persist(new_category);
    }

    @Override
    public void delete(String category_name) {
        logger.info("CategoryService.delete");
        Category category = em.find(Category.class, category_name);
        if (category == null) {
            logger.error("Cannot delete");
            return;
        }
        em.remove(category);
    }

    @Override
    public void update(String old_category_name, String new_category_name) {
        logger.info("CategoryService.update");
/*
        Category category = em.find(Category.class, old_category_name);
        category.setName(new_category_name);
        em.merge(category);
        em.flush();
*/
        Query query = em.createQuery("update Category c set c.name = ?1 where c.name = ?2");
        query.setParameter(1, new_category_name);
        query.setParameter(2, old_category_name);
        query.executeUpdate();

    }

}
