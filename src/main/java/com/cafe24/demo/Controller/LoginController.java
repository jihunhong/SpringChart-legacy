package com.cafe24.demo.Controller;

import com.cafe24.demo.Annotation.SocialUser;
import com.cafe24.demo.VO.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController{

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping(value="/loginSuccess")
    public String loginComplete( @SocialUser User user){
        return "redirect:/";
    }

}

