package exceptions;

public class MessageManagerException extends FunctionalException {
    public MessageManagerException(Throwable e, int i) {
        super("Error while checking getZone() method. "+e.getMessage(), i);
    }
}
