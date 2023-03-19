package ru.oorzhak.filestorage.exception;

/**
 * Исключение, выбрасываемое при добавлении уже существующего пользователя
 */
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String msg) {
        super(msg);
    }
}
