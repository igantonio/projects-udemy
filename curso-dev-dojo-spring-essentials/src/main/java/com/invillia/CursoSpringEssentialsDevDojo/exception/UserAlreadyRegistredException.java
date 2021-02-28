package com.invillia.CursoSpringEssentialsDevDojo.exception;

public class UserAlreadyRegistredException extends RuntimeException {
    public UserAlreadyRegistredException(String message) {
        super(message);
    }
}
