package com.cafe24.demo.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cafe24.demo.Utils.JsoupUtils;
import com.cafe24.demo.VO.Music;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
class HomeController {

    JsoupUtils utils = new JsoupUtils();

    @RequestMapping(value = {"/","/melon"})
    public String Home(Model model){

        List<Music> melon = new ArrayList<>();
        
        melon = utils.getMelonChart();

        model.addAttribute("list", melon);
        model.addAttribute("name", "melon");
        
        return "index";
    }

    @RequestMapping(value = "/genie")
    public String Genie(Model model){

        List<Music> genie = new ArrayList<>();

        genie = utils.getGeineChart();

        model.addAttribute("list", genie);
        model.addAttribute("name", "genie");

        return "index";
    }

    @RequestMapping(value = "/bugs")
    public String Bugs(Model model){

        List<Music> bugs = new ArrayList<>();

        bugs = utils.getBugsChart();

        model.addAttribute("list", bugs);
        model.addAttribute("name", "bugs");

        return "index";
    }
    
}
