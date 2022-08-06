package ng.hotsystems.contentManager.exceptions;

public class BlogDoesNotExistException extends ApplicationException {
    public BlogDoesNotExistException() {
    }

    public BlogDoesNotExistException(String message) {
        super(message);
    }

    public BlogDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlogDoesNotExistException(Throwable cause) {
        super(cause);
    }
}
