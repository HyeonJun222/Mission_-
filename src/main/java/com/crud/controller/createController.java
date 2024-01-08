package com.crud.controller;

import com.crud.dto.boardDto;
import com.crud.service.boardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class createController {
    private static final Logger log = LoggerFactory.getLogger(createController.class);

    // service
    private boardService service;

    public createController(boardService boardService){
        this.service = boardService;
    }
    // 생성 사이트
    @GetMapping("/create-view")
    public String createView(){
        return "create";
    }

    // /create로 데이터 보내기
    // 내용 데이터를 보내는 요청받는 메서드
    @PostMapping("/create")
    public String create(
            @RequestParam("name")
            String name,
            @RequestParam("text")
            String text,
            @RequestParam("password")
            String password
    ){
        boardDto board = service.createBoard(name, text, password);
//        log.info(board.toString());

        // double post problem 을 해결하기 위해 (새로고침 시 같은 데이터 무한입력)
        // post redirect get pattern
        // redirect:/create-view
        return "redirect:/home";
    }

    // home
    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("boardList", service.readBoardAll());
        return "home";
    }


}
