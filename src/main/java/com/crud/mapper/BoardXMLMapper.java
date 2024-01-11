package com.crud.mapper;

import com.crud.dto.BoardDto;

import java.util.List;

public interface BoardXMLMapper {
    List<BoardDto> selectBoardAll();
    BoardDto selectBoard(Long id);
}
