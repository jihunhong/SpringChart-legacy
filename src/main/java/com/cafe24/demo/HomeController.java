package com.cafe24.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
class HomeController {

    @RequestMapping(value = "/")
    public String Home(){
        return "hello";
    }
}
