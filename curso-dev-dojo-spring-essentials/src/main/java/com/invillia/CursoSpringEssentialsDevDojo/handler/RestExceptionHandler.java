package com.invillia.CursoSpringEssentialsDevDojo.handler;

import com.invillia.CursoSpringEssentialsDevDojo.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResoucerNotFoundException(ResourceNotFoundException rfnException){
        return new ResponseEntity<>(getResourceNotFoundDetails(HttpStatus.NOT_FOUND.value(),
                "Resource not found !!!",
                rfnException), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentsAlreadyRegistredException.class)
    public ResponseEntity<?> handleStudentAlreadyRegistredException(StudentsAlreadyRegistredException sarException){
        return new ResponseEntity<>(getResourceNotFoundDetails(HttpStatus.BAD_REQUEST.value(),
                "Student already registred",
                sarException), HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manvException,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        List<FieldError> fieldErrorList = manvException.getBindingResult().getFieldErrors();
        String fields = fieldErrorList.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String fieldMessages = fieldErrorList.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining( ","));

        ValidationErrorDetails veDetails = ValidationErrorDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Field validation error")
                .detail("Field validation error !!!")
                .developerMessage(manvException.getClass().getName())
                .field(fields)
                .fieldMessage(fieldMessages)
                .build();

        return new ResponseEntity<>(veDetails, HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {

        ErrorDetails errorDetails = ResouceNotFoundDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(status.value())
                .title("Internal Exception")
                .detail(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();

        return new ResponseEntity<>(errorDetails, headers, status);
    }

    //    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<?> handleMethodArgumentoNotValidException(MethodArgumentNotValidException manvException){
//
//
//        List<FieldError> fieldErrorList = manvException.getBindingResult().getFieldErrors();
//        String fields = fieldErrorList.stream().map(FieldError::getField).collect(Collectors.joining(","));
//        String fieldMessages = fieldErrorList.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining( ","));
//
//        ValidationErrorDetails veDetails = ValidationErrorDetails.Builder
//                .newBuilder()
//                .timestamp(new Date().getTime())
//                .status(HttpStatus.BAD_REQUEST.value())
//                .title("Field validation error")
//                .detail("Field validation error !!!")
//                .developerMessage(manvException.getClass().getName())
//                .field(fields)
//                .fieldMessage(fieldMessages)
//                .build();
//
//        return new ResponseEntity<>(veDetails, HttpStatus.BAD_REQUEST);
//
//    }

    private ResouceNotFoundDetails getResourceNotFoundDetails(int status, String title, RuntimeException exceptionError){
        ResouceNotFoundDetails rfnDetails = ResouceNotFoundDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(status)
                .title(title)
                .detail(exceptionError.getMessage())
                .developerMessage(exceptionError.getClass().getName())
                .build();

        return rfnDetails;
    }

}
