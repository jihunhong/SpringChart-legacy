package com.cafe24.demo.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cafe24.demo.DAO.VideoDAO;
import com.cafe24.demo.Service.YoutubeService;
import com.cafe24.demo.Utils.JsoupUtils;
import com.cafe24.demo.VO.Music;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class HomeController {

    JsoupUtils utils = new JsoupUtils();

    @Autowired
    YoutubeService youtubeService;

    @Autowired
    private VideoDAO videoDAO;

    @RequestMapping(value = { "/", "/melon" })
    public String Home(Model model) throws IOException {

        List<Music> melon = new ArrayList<>();
        
        melon = utils.getMelonChart();

        ArrayList<Map<String, String>> output = new ArrayList<>();
        
        for (int i=0; i<3; i++){
            String searchQuery = melon.get(i).getTitle() + ' ' + melon.get(i).getArtist();
            output.addAll(youtubeService.SearchOnYoutube(searchQuery));
        }
        
        model.addAttribute("output", output);

        model.addAttribute("list", melon);
        model.addAttribute("name", "melon");
        
        
        return "index";
    }

    @RequestMapping(value = "/genie")
    public String Genie(Model model) throws IOException {

        List<Music> genie = new ArrayList<>();

        genie = utils.getGeineChart();

        ArrayList<Map<String, String>> output = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            String searchQuery = genie.get(i).getTitle() + genie.get(i).getArtist();
            output.addAll(youtubeService.SearchOnYoutube(searchQuery));
        }

        model.addAttribute("output", output);
        model.addAttribute("list", genie);
        model.addAttribute("name", "genie");

        return "index";
    }

    @RequestMapping(value = "/bugs")
    public String Bugs(Model model) throws IOException {

        List<Music> bugs = utils.getBugsChart();

        ArrayList<Map<String, String>> output = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            String searchQuery = bugs.get(i).getTitle() + bugs.get(i).getArtist();
            String currentTitle = bugs.get(i).getTitle();
            if( videoDAO.existsById(currentTitle) ){
                output.add(videoDAO.findById(currentTitle));
            }
            output.addAll(youtubeService.SearchOnYoutube(searchQuery));
        }
        
        model.addAttribute("output", output);

        model.addAttribute("output", output);
        model.addAttribute("list", bugs);
        model.addAttribute("name", "bugs");

        return "index";
    }
    
}
