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

    @RequestMapping(value = "/")
    public String Home(Model model){

        List<Music> melon = new ArrayList<>();
        List<Music> genie = new ArrayList<>();
        List<Music> bugs = new ArrayList<>(); 
        
        melon = utils.getMelonChart();
        genie = utils.getGeineChart();
        bugs  = utils.getBugsChart();

        model.addAttribute("melon", melon);
        model.addAttribute("genie", genie);
        model.addAttribute("bugs", bugs);

        return "index";
    }
    
}
