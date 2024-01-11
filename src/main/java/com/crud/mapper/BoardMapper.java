package com.crud.mapper;

import com.crud.dto.BoardDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {
    @Select("SELECT * FROM board;")
    List<BoardDto> selectBoardAll();

    // SELECT, INSERT, UPDATE, DELETE

    // INSERT
    @Insert("INSERT INTO board(name, text, password) " +
            "VALUES (#{name}, #{text}, #{password});")
    void insertBoard(BoardDto dto);

    // SELECT ONE
    @Select("SELECT * FROM board WHERE id = #{id};")
    BoardDto selectBoard(Long id);

    // UPDATE
    @Update("UPDATE board SET " +
            "name = #{name}, " +
            "text = #{text}, " +
            "password = #{password}, " +
            "WHERE id = #{id})" )
    void updateBoard (BoardDto dto);

    // DELETE
    @Delete("DELETE FROM board " +
            "WHERE id = #{id}")
    void deleteBoard(Long id);
}
