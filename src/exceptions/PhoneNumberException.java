package exceptions;

import static constants.ExceptionConstants.PHONE_NUMBER_INVALID;

public class PhoneNumberException extends RuntimeException {
    public PhoneNumberException() {
        super(PHONE_NUMBER_INVALID);
    }
}