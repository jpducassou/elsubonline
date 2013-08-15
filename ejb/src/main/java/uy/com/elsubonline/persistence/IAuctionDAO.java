package uy.com.elsubonline.persistence;

import uy.com.elsubonline.domain.Auction;

public interface IAuctionDAO {
    public void create(Auction entity) throws PersistenceException;
}
