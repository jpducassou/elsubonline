package uy.com.elsubonline.persistence;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import uy.com.elsubonline.api.dtos.UserDto;
import uy.com.elsubonline.domain.User;

@Stateless
public class UserDAO implements IUserDAO {

    @javax.persistence.PersistenceContext(unitName="uy.com.elsubonline.persistence")
    private javax.persistence.EntityManager em;

    @Override
    public void create(User entity) throws PersistenceException {
        try {
            em.persist(entity);
            em.flush();
        } catch (Throwable ex) {
            throw(new PersistenceException(ex.getMessage()));
        }
    }

    @Override
    public User retrieve(String id) throws PersistenceException {
        try {
            return em.find(User.class, id);
        } catch (Throwable ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public void update(User entity) throws PersistenceException {
        try {
            em.merge(entity);
            em.flush();
        } catch (Throwable ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public void delete(String id) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserDto validate_credentials(String username, String password) throws PersistenceException {
        try {
            UserDto userDto = (UserDto)em.createNamedQuery("validate_credentials")
                    .setParameter("username", username)
                    .setParameter("password", password).getSingleResult();
            return userDto;
        } catch (NoResultException ex) {
            throw(new PersistenceException());
        }
    }

}
