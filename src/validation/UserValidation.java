package validation;

import exceptions.EmailException;
import exceptions.PasswordException;
import exceptions.PhoneNumberException;

import static constants.ExceptionConstants.*;

public class UserValidation {
    public static boolean validateEmail(String email) {
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))
            throw new EmailException();

        return true;
    }

    public static boolean validatePassword(String password) {
        if (!password.matches("^(?=.*[A-Z]).+$"))
            throw new PasswordException(PASSWORD_UPPER);
        if (!password.matches("^(?=.*[a-z]).+$"))
            throw new PasswordException(PASSWORD_LOWER);
        if (!password.matches("^(?=.*\\d).+$"))
            throw new PasswordException(PASSWORD_DIGIT);
        if (!password.matches("^.{10,}$"))
            throw new PasswordException(PASSWORD_LENGTH);
        if (!password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{10,}$"))
            throw new PasswordException(PASSWORD_WRONG);

        return true;
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 10 || !phoneNumber.matches("[0-9]+"))
            throw new PhoneNumberException();

        return true;
    }
}