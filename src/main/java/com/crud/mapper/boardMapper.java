package com.crud.mapper;

import com.crud.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface boardMapper {
    @Select("SELECT * FROM board;")
    List<BoardDto> selectBoardAll();
}
