package uy.com.elsubonline.api.exceptions;

public class UserUnconfirmedException extends ServiceException {
    public UserUnconfirmedException(String message) {
        super(message);
    }
}
