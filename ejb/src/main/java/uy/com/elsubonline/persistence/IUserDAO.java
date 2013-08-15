package uy.com.elsubonline.persistence;

import uy.com.elsubonline.api.dtos.UserDto;
import uy.com.elsubonline.domain.User;

public interface IUserDAO {
    public void create(User entity) throws PersistenceException;
    public User retrieve(String id) throws PersistenceException;
    public void update(User entity) throws PersistenceException;
    public void delete(String id)   throws PersistenceException;
    public UserDto validate_credentials(String username, String password) throws PersistenceException;
}
