package ru.oorzhak.filestorage.advice;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.oorzhak.filestorage.exception.PasswordNotConfirmedException;
import ru.oorzhak.filestorage.exception.UserAlreadyExistsException;
import ru.oorzhak.filestorage.exception.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Обработка исключений API
 */
@RestControllerAdvice
public class ErrorControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        String message = ex.getBindingResult().getFieldErrors().stream()
//                .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                .collect(Collectors.joining(", "));
//
//        Optional<HttpStatus> httpStatus = Optional.of(HttpStatus.resolve(status.value()));
//        httpStatus.orElseThrow(RuntimeException::new);
//
//        Map<String, Object> body = body(httpStatus.get(), ex);
//        body.put("message", message);
//        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        return null;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFoundException(HttpServletRequest request, Exception ex) {
        return handleCustomException(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleUserAlreadyExistsException(HttpServletRequest request, Exception ex) {
        return handleCustomException(HttpStatus.CONFLICT, ex);
    }

    @ExceptionHandler(PasswordNotConfirmedException.class)
    public ResponseEntity<Map<String, Object>> handlePasswordNotConfirmedException(HttpServletRequest request, Exception ex) {
        return handleCustomException(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleBadCredentialsException(HttpServletRequest request, Exception ex) {
        return null;
    }

    protected Map<String, Object> body(HttpStatus status, Exception exception) {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", LocalDateTime.now());
        map.put("status", status.value());
        map.put("message", exception.getMessage());
        return map;
    }

    protected ResponseEntity<Map<String, Object>> handleCustomException(HttpStatus status, Exception exception) {
        return ResponseEntity.status(status).body(body(status, exception));
    }
}
