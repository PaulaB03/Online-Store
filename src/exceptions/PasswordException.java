package exceptions;

import static constants.ExceptionConstants.PASSWORD_INVALID;

public class PasswordException extends RuntimeException {
    public PasswordException(String message) {
        super(PASSWORD_INVALID + message);
    }
}