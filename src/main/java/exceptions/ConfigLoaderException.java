package exceptions;

public class ConfigLoaderException extends FunctionalException {
    public ConfigLoaderException(Throwable e, int i) {
        super("Config loader error : "+e.getMessage(), i);
    }
}
