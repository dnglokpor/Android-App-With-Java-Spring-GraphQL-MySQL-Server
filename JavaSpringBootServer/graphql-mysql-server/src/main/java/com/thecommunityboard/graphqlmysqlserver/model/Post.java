package com.thecommunityboard.graphqlmysqlserver.model;

import javax.persistence.*;
import java.time.ZonedDateTime;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * class that represents a post on a board.
 * it tracks not only the note itself but also stats associated with it. 
 */
@Entity
public class Post {
   /** inner status values for posts on the board */   
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   @ManyToOne
   @JoinColumn(name = "board_id", nullable = false, updatable = false)
   private Board postSite;
   @ManyToOne
   @JoinColumn(name = "author_id", nullable = false, updatable = false)
   private User author;
   @Column(name = "title", nullable = false, updatable = false)
   private String title;
   @Column(name = "body", nullable = false, updatable = false)
   private String body;
   @Column(name = "datePosted", nullable = false, updatable = false)
   private String datePosted;
   @Column(name = "dateRemoved", nullable = true, updatable = true)
   private String dateRemoved;
   @Column(name = "views", nullable = false, updatable = true)
   private int views;
   @Column(name = "rips", nullable = false, updatable = true)
   private int rips;
   @Column(name = "isPosted", nullable = false, updatable = true)
   private Boolean isPosted;

   // constructors
   public Post() {} // default
   /**
    * construct a new Post. This assumes the author and the board to post it on exist.
    * @param postSite the board to post on. can be null if this post isn't yet posted
    * @param author the user that made this post
    * @param title the title of the post
    * @param contents the body of the post
    */
   public Post(Board postSite, @NonNull User author, @NonNull String title, @NonNull String body) {
      // e.g.: "myLewysG#4456:Heroes-of-oblivion"
      this.postSite = postSite;
      this.author = author;
      this.title = title;
      this.body = body;
      this.datePosted = ZonedDateTime.now().toString();
      this.dateRemoved = null;
      this.views = 0;
      this.rips = 0;
      this.isPosted = true; // by default
   }   
   // getters
   public Long getId() {
      return this.id;
   }
   public Board getPostSite() {
      return postSite;
   }
   public String getTitle() {
      return title;
   }
   public String getBody() {
      return body;
   }
   public String getDatePosted() {
      return datePosted;
   }
   public String getDateRemoved() {
      return dateRemoved;
   }
   public int getViews() {
      return views;
   }
   public int getRips() {
      return rips;
   }
   public Boolean getStatus() {
      return isPosted;
   }

   // setters
   public void setDateRemoved(String dateTime) {
      dateRemoved = dateTime;
   }
   public void setViews(int v) {
      views = v;
   }
   public void setRips(int r) {
      rips = r;
   }
}