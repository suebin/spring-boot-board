package com.nhnacademy.board.login.dto;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String id;
    @NotBlank
    private String password;
    public LoginRequest(){}
    public LoginRequest(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

}
