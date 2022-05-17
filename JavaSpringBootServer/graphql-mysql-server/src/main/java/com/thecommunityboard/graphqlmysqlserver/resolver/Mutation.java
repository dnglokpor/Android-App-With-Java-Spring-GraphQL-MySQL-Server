package com.thecommunityboard.graphqlmysqlserver.resolver;

import java.util.Optional;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.thecommunityboard.graphqlmysqlserver.model.*;
import com.thecommunityboard.graphqlmysqlserver.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {
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
   public Mutation(UserRepository ur, PostRepository pr, BoardRepository br) {
      this.userRepos = ur;
      this.postRepos = pr;
      this.boardRepos = br;  
   }

   // user.graphqls mutations
   public User addUser(String username, String birthDate, String password) {
      User newUser = null;
      Optional<User> existing = Optional
         .ofNullable(userRepos.findExistingUser(username, birthDate, password)); 
      if (!existing.isPresent()) {
         newUser = new User(username, birthDate, password);
         newUser = userRepos.save(newUser);
      }
      return newUser;
   }
   public Boolean deleteUser(Long id) {
      userRepos.deleteById(id);
      return true;
   }
   
   // boards.graphqls mutations
   public Board makeBoard(String boardId) {
      Optional<Board> existing = Optional.ofNullable(boardRepos.findByBoardId(boardId));
      if (!existing.isPresent()){
         Board board = new Board(boardId);
         board = boardRepos.save(board);
         return board;
      }
      return null;
   }
   public Post postOn(String boardId, String userId, String title, String message) { 
      // post if board exists
      Optional<Board> board = Optional.ofNullable(boardRepos.findByBoardId(boardId));
      if (board.isPresent()){
         Optional<User> user = Optional.ofNullable(userRepos.findByUserId(userId));
         if (user.isPresent()) {
            Post post = new Post(board.get(), user.get(), title, message);
            post = postRepos.save(post);
            return post;
         }
      }
      return null; // failed action
   }

}