package com.crud.repo;

import com.crud.dto.BoardDto;
import com.crud.mapper.BoardXMLMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor    // final 애들 생성자 자동으로 만들어줌
public class BoardXMLDao {
    private final SqlSessionFactory sessionFactory;

    public List<BoardDto> readBoardAll(){
        try (SqlSession session = sessionFactory.openSession()){
            BoardXMLMapper mapper = session.getMapper(BoardXMLMapper.class);
            return mapper.selectBoardAll();
        }
    }
    public BoardDto readBoard(Long id){
        try (SqlSession session = sessionFactory.openSession()){
            BoardXMLMapper mapper = session.getMapper(BoardXMLMapper.class);
            return mapper.selectBoard(id);
        }
    }
}

