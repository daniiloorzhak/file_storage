package ru.oorzhak.filestorage.exception;

/**
 * Исключение, выбрасываемое при несоответствии паролей
 */
public class PasswordNotConfirmedException extends RuntimeException {
    public PasswordNotConfirmedException(String msg) {
        super(msg);
    }
}
