package ng.hotsystems.contentManager.exceptions;

public class ArticleDoesNotExistException extends ApplicationException {
    public ArticleDoesNotExistException() {
    }

    public ArticleDoesNotExistException(String message) {
        super(message);
    }

    public ArticleDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArticleDoesNotExistException(Throwable cause) {
        super(cause);
    }
}
