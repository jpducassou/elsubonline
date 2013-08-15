package uy.com.elsubonline.persistence;

import javax.ejb.Stateless;
import uy.com.elsubonline.domain.Auction;

@Stateless
public class AuctionDAO implements IAuctionDAO {

    @javax.persistence.PersistenceContext(unitName="uy.com.elsubonline.persistence")
    private javax.persistence.EntityManager em;

    @Override
    public void create(Auction entity) throws PersistenceException {
        try {
            em.persist(entity);
            em.flush();
        } catch (Throwable ex) {
            throw(new PersistenceException(ex.getMessage()));
        }
    }

}
