package com.thecommunityboard.graphqlmysqlserver.repository;

import com.thecommunityboard.graphqlmysqlserver.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
   // special queries
   @Query("select u from User u where u.userId = ?1")
   User findByUserId(String userId);
   @Query("select u from User u where u.username = ?1 and u.password = ?2")
   User findByUsernameAndPassword(String username, String password);
   @Query("select u from User u where u.username = ?1 and u.birthDate = ?2 and u.password = ?3")
   User findExistingUser(String username, String birthDate, String password);
}
