package africa.semicolon.com.bims.exceptions;

public class EmailExistException extends BimException {
    public EmailExistException(String emailAlreadyExist) {
        super(emailAlreadyExist);
    }
}
