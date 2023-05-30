package com.nhnacademy.board.exception;

import javax.validation.constraints.NotBlank;

public class IdAlreadyExistException extends RuntimeException {
    public IdAlreadyExistException(@NotBlank String id) {
        super("id already exists : " + id);
    }
}
