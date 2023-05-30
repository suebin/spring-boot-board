package com.nhnacademy.board.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException() {
        super("해당 포스트는 존재하지 않습니다.");
    }
}
