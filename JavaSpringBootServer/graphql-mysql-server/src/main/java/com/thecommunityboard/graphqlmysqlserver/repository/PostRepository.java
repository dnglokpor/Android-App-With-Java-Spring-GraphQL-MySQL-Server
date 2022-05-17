package com.thecommunityboard.graphqlmysqlserver.repository;

import com.thecommunityboard.graphqlmysqlserver.model.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
   // special queries
   @Query("select p from Post p where p.postSite = ?1")
   List<Post> findAllPostsOnBoard(Long boardId);
   @Query("select p from Post p where p.postSite = ?1 and p.author = ?2 and p.title = ?3")
   Post findPostByAttributes(String boardId, String authorId, String title);
}