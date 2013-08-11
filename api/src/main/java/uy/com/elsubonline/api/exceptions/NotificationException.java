package uy.com.elsubonline.api.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class NotificationException extends ServiceException {
    public NotificationException(String message) {
        super(message);
    }
}
