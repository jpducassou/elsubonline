package uy.com.elsubonline.api.exceptions;

public class InvalidCredentialsException extends ServiceException {
    public InvalidCredentialsException() {
    }
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
