package ng.hotsystems.contentManager.exceptions;

public class ArticleExistsException extends ApplicationException {
    public ArticleExistsException() {
    }

    public ArticleExistsException(String message) {
        super(message);
    }

    public ArticleExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArticleExistsException(Throwable cause) {
        super(cause);
    }
}
