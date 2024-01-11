package com.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class GalleryController {
    // Gallery, boards

    @GetMapping("/home/1")
    public String board1Entire(Model model){
        System.out.println("/home/1 working");
        return "boards/board1Free";
    }

    @GetMapping("/home/2")
    public String board2Entire(Model model){
        System.out.println("/home/2 working");
        return "boards/board2Develop";
    }

    @GetMapping("/home/3")
    public String board3Entire(Model model){
        System.out.println("/home/3 working");
        return "boards/board3Daily";
    }

    @GetMapping("/home/4")
    public String board4Entire(Model model){
        System.out.println("/home/4 working");
        return "boards/board4Trouble";
    }

}
