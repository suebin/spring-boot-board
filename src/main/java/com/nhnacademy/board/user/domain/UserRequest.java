package com.nhnacademy.board.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NotBlank
    private String id;
    @NotBlank
    private String password;
    @NotBlank
    private String name;

    private String profileFileName;
    private Role role;

    private MultipartFile profileFile;

    public UserRequest(String id, String password, String name, String profileFileName, Role role) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.profileFileName = profileFileName;
        this.role = role;
    }


}
