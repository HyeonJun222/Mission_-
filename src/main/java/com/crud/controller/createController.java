package com.crud.controller;

import com.crud.dto.BoardDto;
import com.crud.service.boardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        service.createBoard(name, text, password);

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

    // read로 요청을 받으면
    // read.html에 게시판 정보포함해서 반환하는 메서드
    // Mapping 에 {}를 넣으면 그 안에 들어있는 데이터를 @PathVariable("id")
    // 매개변수에 할당해 줄 수 있음

    @GetMapping("/read/{id}")
    public String readOne(
            @PathVariable("id")
            Long id,
            Model model
    ){
        BoardDto dto = service.readBoard(id);
        System.out.println(dto);
        model.addAttribute("board", dto);
        return "read";
    }

    // update-view/{id}
    @GetMapping("/update-view/{id}")
    public String updateView(
            @PathVariable("id")
            Long id,
            Model model
    ){
        BoardDto dto = service.readBoard(id);
        model.addAttribute("board", dto);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String update(
            @PathVariable("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("text") String text,
            @RequestParam("password") String password
    ){
        BoardDto dto = service.updateBoard(id, name, text, password);
        return String.format("redirect:read/%d", dto.getId());
    }

    @PostMapping("/delete/{id}")
    public String delete( @PathVariable("id") Long id){
        service.deleteBoard(id);
        return "redirect:/home";
    }
}
