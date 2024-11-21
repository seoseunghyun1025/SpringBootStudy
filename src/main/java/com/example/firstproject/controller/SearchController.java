package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
    @GetMapping("/search")
    //name을 지정해서 RequestParam에 지정
    public String mainSearch(@RequestParam("mainSearchKeyword")String searchKeyword){
        System.out.println("검색어: " + searchKeyword);
        return "";
    }
}
