package com.thecommunityboard.graphqlmysqlserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.thecommunityboard.graphqlmysqlserver.model.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
   // special queries
   @Query("select b from Board b where b.boardId = ?1")
   Board findByBoardId(String boardId);
}