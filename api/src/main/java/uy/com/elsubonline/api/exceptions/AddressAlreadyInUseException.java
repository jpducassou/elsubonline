
package uy.com.elsubonline.api.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class AddressAlreadyInUseException extends ServiceException {
    public AddressAlreadyInUseException(String message) {
        super(message);
    }
}
