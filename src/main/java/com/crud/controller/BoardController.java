package com.crud.controller;

import com.crud.dto.BoardDto;
import com.crud.service.boardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BoardController {
    private static final Logger log = LoggerFactory.getLogger(BoardController.class);

    // service
    private boardService service;

    public BoardController(boardService boardService){
        this.service = boardService;
    }

    // 생성 사이트
    @GetMapping("/create-view")
    public String createView(){
        System.out.println("/create-view working");
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
        System.out.println("/create working");
        service.createBoard(name, text, password);
        log.info(name);
        log.info(text);
        log.info(password);
        // double post problem 을 해결하기 위해 (새로고침 시 같은 데이터 무한입력)
        // post redirect get pattern
//        return "redirect:/create-view";
        return "redirect:/home";
    }

    // home
    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("boardList", service.readBoardAll());
        System.out.println("/home working");
        return "home";
    }

    // read로 요청을 받으면
    // read.html에 게시판 정보포함해서 반환하는 메서드
    // Mapping 에 {}를 넣으면 그 안에 들어있는 데이터를 @PathVariable("id")
    // 매개변수에 할당해 줄 수 있음

//    @GetMapping("/read/{id}")
//    public String readOne(
//            @PathVariable("id")
//            Long id,
//            Model model
//    ){
//        System.out.println("/read/{id} working (readOnd)");
//        BoardDto dto = service.readBoard(id);
//        System.out.println(dto);
//        model.addAttribute("board", dto);
//        return "read";
//    }

    // read 오류를 잡기 위한 시도
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @GetMapping("/read/{id}")
    public String readOne(@PathVariable("id") Long id, Model model) {
        logger.info("/read/{id} working (readOnd)");

        try {
            BoardDto dto = service.readBoard(id);
            logger.info("BoardDto: {}", dto);
            model.addAttribute("board", dto);
            return "read";
        } catch (Exception e) {
            logger.error("Error during readBoard", e);
            return "error"; // 에러 페이지로 리다이렉트 또는 에러 메시지를 표시하는 뷰를 반환
        }
    }

    // update-view/{id}
    @GetMapping("/update-view/{id}")
    public String updateView(
            @PathVariable("id")
            Long id,
            Model model
    ){
        System.out.println("/update-view/{id} working");
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
        System.out.println("/update/{id} working");
        BoardDto dto = service.updateBoard(id, name, text, password);
        return String.format("redirect:read/%d", dto.getId());
    }

    @PostMapping("/delete/{id}")
    public String delete( @PathVariable("id") Long id){
        System.out.println("/delete/{id} working");
        service.deleteBoard(id);
        return "redirect:/home";
    }
}
