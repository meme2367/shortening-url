package com.shorteningurl.controller;

import com.shorteningurl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class UrlController {

    @Autowired
    private UrlService urlService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("testname", "안녕하세요ss");
        return "index";
    }

    @PostMapping
    public String Shortening(@RequestBody Map<String,Object> param, Model model){
        return "";
    }
}
