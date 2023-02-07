package exceptions;

public class UnknownCommandException extends Exception {
    public UnknownCommandException(String message)
    {
        super(message);
    }
}
