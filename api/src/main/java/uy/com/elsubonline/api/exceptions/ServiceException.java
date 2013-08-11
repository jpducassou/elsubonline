package uy.com.elsubonline.api.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class ServiceException extends Exception {
    public ServiceException(String message) {
        super(message);
    }
}
