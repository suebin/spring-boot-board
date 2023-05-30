package com.nhnacademy.board.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id) {
        super("회원을 조회할 수 없습니다.:" + id);
    }
}
