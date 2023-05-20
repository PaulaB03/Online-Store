package exceptions;

import static constants.ExceptionConstants.EMAIL_INVALID;

public class EmailException extends RuntimeException {
    public EmailException() {
        super(EMAIL_INVALID);
    }
}