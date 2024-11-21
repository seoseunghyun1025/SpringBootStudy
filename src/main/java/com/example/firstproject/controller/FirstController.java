package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FirstController {
    @GetMapping("/hi")
    public String niceToMeet(Model mo){
        mo.addAttribute("userName","비회원");
        System.out.println("niceToMeetYou Run!!!");
        return "greetings";
    }
    @GetMapping("/hi/{name}")
    public String niceToMeetYou(@PathVariable("name") String name, Model mo){
        mo.addAttribute("userName", name);
        //System.out.println("niceToMeetYou Run!!!");
        return "greetings";
    }
    @GetMapping("/bye")
    public String seeYouNext(Model mo){
        mo.addAttribute("userName","서승현");
        return "goodbye";
    }
}
