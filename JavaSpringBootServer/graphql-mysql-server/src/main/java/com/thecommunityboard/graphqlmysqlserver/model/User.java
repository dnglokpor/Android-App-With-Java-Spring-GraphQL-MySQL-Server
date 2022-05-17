package com.thecommunityboard.graphqlmysqlserver.model;

import javax.persistence.*;
import org.jetbrains.annotations.NotNull;
import com.thecommunityboard.graphqlmysqlserver.util.Generators;

/**
 * class that represent the app's users.
 */
@Entity
public class User {
	// member data / DB table structure
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   @Column(name = "userId", nullable = false, unique = true)
   private String userId;
   @Column(name = "username", nullable = false)
   private String username;
   @Column(name = "birthDate", nullable = false)
   private String birthDate;
   @Column(name = "password", nullable = false)
   private String password;

   // constructors
   public User() {} // default
   public User(@NotNull String uname, @NotNull String bDate, @NotNull String pass) {
      this.username = uname;
      this.birthDate = bDate;
      this.password = pass;
      // generate userID
      this.userId = Generators.genUid(this.username);
   }

   // getters
   public Long getId() {
      return id;
   }
   public String getUserId() {
      return userId;
   }
   public String getUsername() {
      return username;
   }
   public String getBirthDate() {
      return birthDate;
   }
   public String getPassword() {
      return password;
   }
   @Override
   public String toString() {
      return (
         "{id: " + id + "userId: " + userId + "username: " + username + "}"
      );
   }
}