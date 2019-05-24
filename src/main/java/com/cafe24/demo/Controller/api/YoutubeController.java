package com.cafe24.demo.Controller.api;

import java.util.List;

import com.cafe24.demo.Service.YoutubeService;
import com.google.api.services.youtube.model.SearchResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/youtubeAPIController")
@RequestMapping("/youtube/api")
public class YoutubeController {

    @Autowired
    YoutubeService youtubeService;

    @RequestMapping("/search")
    @ResponseBody
    public List<SearchResult> SearchOnYoutube(@RequestParam(value = "q") String searchQuery) {

        return youtubeService.SearchOnYoutube(searchQuery);
    }

}