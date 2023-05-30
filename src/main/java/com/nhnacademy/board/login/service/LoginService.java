package com.nhnacademy.board.login.service;

import com.nhnacademy.board.entity.User;
import com.nhnacademy.board.user.servlce.UserService;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserService userService;

    public LoginService(UserService userService) {
        this.userService = userService;
    }

    public User doLogin(String id, String password){
        User user = userService.getUser(id);
        if(user.getId().equals(id) && user.getPassword().equals(password)){
            return user;
        }
        return null;
    }
}
