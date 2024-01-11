package com.crud.repo;

import com.crud.dto.BoardDto;
import com.crud.mapper.BoardMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDao {
    private final SqlSessionFactory sessionFactory;
    public BoardDao(SqlSessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    // 데이터베이스에서 데이터 전부 불러오기
    public List<BoardDto> readBoardAll(){
        // SqlSession 은 Mybatis, Database 가 연결되었다는 것을 상징하는 객체
        try(SqlSession session = sessionFactory.openSession()){
            // Mapper 인터페이스를 가져옴
            BoardMapper mapper = session.getMapper(BoardMapper.class);
            return mapper.selectBoardAll();

        }

    }
    // BoardDto -> 게시물 추가기능
    public void createBoard(BoardDto dto){
        try(SqlSession session = sessionFactory.openSession()){
            BoardMapper mapper = session.getMapper(BoardMapper.class);
            mapper.insertBoard(dto);
        }
    }

    // SELECT
    // id 를 Long 으로 받아 데이터베이스에서 id가 같은 줄을 반환하는 메서드
    public BoardDto readBoard(Long id){
        try(SqlSession session = sessionFactory.openSession()){
            BoardMapper mapper = session.getMapper(BoardMapper.class);
            return mapper.selectBoard(id);
        }
    }



    // BoardDto -> 게시물 삭제기능
    public void deleteBoard(Long id){
        try(SqlSession session = sessionFactory.openSession()){
            BoardMapper mapper = session.getMapper(BoardMapper.class);
            mapper.deleteBoard(id);
        }
    }
}
