package uy.com.elsubonline.persistence;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import uy.com.elsubonline.domain.Category;

@Stateless
public class CategoryDAO implements ICategoryDAO {

    @javax.persistence.PersistenceContext(unitName="uy.com.elsubonline.persistence")
    private javax.persistence.EntityManager em;

    @Override
    public List<String> list() {
        Query query = em.createQuery("select c.name from Category c");
        return (List<String>)query.getResultList();
    }

    @Override
    public void create(Category category) throws PersistenceException {
        try {
            em.persist(category);
            em.flush();
        } catch (Throwable ex) {
            throw(new PersistenceException(ex.getMessage()));
        }
    }

    @Override
    public Category retrieve(String id) throws PersistenceException {
        try {
            return em.find(Category.class, id);
        } catch (Throwable ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public void update(String old_category_name, String new_category_name) throws PersistenceException {

        /*
        Category category = em.find(Category.class, old_category_name);
        category.setName(new_category_name);
        em.merge(category);
        em.flush();
        */

        try {
            Query query = em.createQuery("update Category c set c.name = ?1 where c.name = ?2");
            query.setParameter(1, new_category_name);
            query.setParameter(2, old_category_name);
            query.executeUpdate();
            em.flush();
        } catch (Throwable ex) {
            throw(new PersistenceException(ex.getMessage()));
        }
    }

}
