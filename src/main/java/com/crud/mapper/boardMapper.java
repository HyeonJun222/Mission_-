package com.crud.mapper;

import com.crud.dto.BoardDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface boardMapper {
    @Select("SELECT * FROM board;")
    List<BoardDto> selectBoardAll();

    // SELECT, INSERT, UPDATE, DELETE

    // INSERT
    @Insert("INSERT INTO board(name, text, password) " +
            "VALUES (#{name}, #{text}, #{password});")
    void insertBoard(BoardDto dto);

    // SELECT ONE
    @Select("SELECT * FROM board;")
    void selectBoard(Long id);
}
