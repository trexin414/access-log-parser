public class LengthExceptions extends Exception{
    public LengthExceptions() {
    }

    public LengthExceptions(String message) {
        super(message);
    }

    public LengthExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public LengthExceptions(Throwable cause) {
        super(cause);
    }
}
