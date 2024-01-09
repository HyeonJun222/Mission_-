package com.crud;

import com.crud.dto.BoardDto;
import com.crud.mapper.boardMapper;
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
            boardMapper mapper = session.getMapper(boardMapper.class);
            return mapper.selectBoardAll();

        }

    }
}
