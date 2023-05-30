package com.nhnacademy.board.login.controller;

import com.nhnacademy.board.entity.User;
import com.nhnacademy.board.login.dto.LoginRequest;
import com.nhnacademy.board.login.service.LoginService;
import com.nhnacademy.board.user.domain.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("loginRequest", new LoginRequest());

        return "login/loginForm";
    }

    @PostMapping("/login")
    public String loginAction(@Valid @ModelAttribute LoginRequest loginRequest, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request){

        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        User user = loginService.doLogin(loginRequest.getId(), loginRequest.getPassword());
        if(Objects.nonNull(user)){
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);

            if(user.getRole().equals(Role.ADMIN)) {
                return "redirect:/admin/users/";
            }
            return "redirect:/board/list/";
        }

        redirectAttributes.addFlashAttribute("message", "아이디 또는 비밀번호를 확인해 주세요!" );
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String logoutAction(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        if(Objects.nonNull(session)){
            session.invalidate();
            Cookie cookie = new Cookie("JSESSIONID","");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        return "redirect:/login";
    }
}
