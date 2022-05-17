package com.thecommunityboard.graphqlmysqlserver.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.thecommunityboard.graphqlmysqlserver.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;
import com.thecommunityboard.graphqlmysqlserver.model.*;
import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {
   // tables
   private UserRepository userRepos;
   private PostRepository postRepos;
   private BoardRepository boardRepos;

   /**
    * injects tables model objects automatically in this constructor
    * @param ur UseRepository object
    * @param nr NoteRepository object
    * @param pr PostRepository object
    * @param br BoardRepository object
    */
   @Autowired
   public Query(UserRepository ur, PostRepository pr, BoardRepository br) {
      this.userRepos = ur;
      this.postRepos = pr;
      this.boardRepos = br;  
   }
   
   // user.graphqls queries
   public User checkUser(String username, String password) {
      User found = null;
      Optional<User> user = Optional
         .ofNullable(userRepos.findByUsernameAndPassword(username, password));
      if (user.isPresent())
         found = user.get();
      return found;
   }
   public User getUser(String userId) {
      User found = null; // failed return
      Optional<User> user = Optional.ofNullable(userRepos.findByUserId(userId));
      if (user.isPresent())
         found = user.get();
      return found;
   }

   // notes.graphqls queries
   public Post getPostStats(String boardId, String authorId, String postTitle) {
      Post found = null;
      Optional<Post> post = Optional.ofNullable(postRepos.findPostByAttributes(boardId, authorId, postTitle));
      if (post.isPresent())
         found = post.get();
      return found;
   }

   // board.graphqls queries
   public Board getBoard(String boardId) {
      Board found = null;
      Optional<Board> board = Optional.ofNullable(boardRepos.findByBoardId(boardId));
      if (board.isPresent())
         found = board.get();
      return found;
   }

   public List<Post> getPostsOnBoard(String boardId) {
      Optional<Board> board = Optional.ofNullable(boardRepos.findByBoardId(boardId));
      if (board.isPresent())
         return postRepos.findAllPostsOnBoard(board.get().getId());
      return null;
   }
}