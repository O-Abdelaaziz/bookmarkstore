package com.bookmarkstoreserver.exception;

import com.bookmarkstoreserver.exception.custom.ResourceAlreadyExistsException;
import com.bookmarkstoreserver.exception.custom.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Created 11/02/2022 - 16:07
 * @Package com.bookmarkstoreserver.exception
 * @Project bookmarkstoreserver
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Resource Not Found",
                details,
                ((ServletWebRequest) request).getRequest().getRequestURI());

        return ResponseEntityBuilder.build(err);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<Object> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex, WebRequest request) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Resource Already Exists",
                details,
                ((ServletWebRequest) request).getRequest().getRequestURI());

        return ResponseEntityBuilder.build(err);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Malformed JSON request",
                details,
                ((ServletWebRequest) request).getRequest().getRequestURI());

        return ResponseEntityBuilder.build(err);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> details = new ArrayList<String>();
        details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getObjectName() + " : " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Validation Errors",
                details,
                ((ServletWebRequest) request).getRequest().getRequestURI());

        return ResponseEntityBuilder.build(err);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Type Mismatch",
                details,
                ((ServletWebRequest) request).getRequest().getRequestURI());

        return ResponseEntityBuilder.build(err);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(Exception ex, WebRequest request) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Constraint Violations",
                details,
                ((ServletWebRequest) request).getRequest().getRequestURI());

        return ResponseEntityBuilder.build(err);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getParameterName() + " parameter is missing");

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Missing Parameters",
                details,
                ((ServletWebRequest) request).getRequest().getRequestURI());

        return ResponseEntityBuilder.build(err);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> details = new ArrayList<String>();
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));

        details.add(builder.toString());

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                "Unsupported Media Type",
                details,
                ((ServletWebRequest) request).getRequest().getRequestURI());

        return ResponseEntityBuilder.build(err);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> details = new ArrayList<String>();
        details.add(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()));

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                "Method Not Found",
                details,
                ((ServletWebRequest) request).getRequest().getRequestURI());

        return ResponseEntityBuilder.build(err);

    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getLocalizedMessage());

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Error occurred",
                details,
                ((ServletWebRequest) request).getRequest().getRequestURI());

        return ResponseEntityBuilder.build(err);
    }
}
