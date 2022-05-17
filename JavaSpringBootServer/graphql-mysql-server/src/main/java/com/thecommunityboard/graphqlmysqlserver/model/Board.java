package com.thecommunityboard.graphqlmysqlserver.model;

import javax.persistence.*;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * class that represents a board
 */
@Entity
public class Board {
   // member variables
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   @Column(name = "boardId", nullable = false, updatable = false, unique = true)
   private String boardId;
   @Column(name = "population", nullable = false, updatable = true)
   private int population;

   // constructors
   public Board() {} // default
   /**
    * construct a new board.
    * @param bid the board's what3words.
    */
   public Board(@NonNull String bid) {
      this.boardId = bid;
      this.population = 0;
   }
   /**
    * reconstruct a board from its elements
    * @param bid the id of the board
    * @param pop the population of the board
    * @param pNotes the list of notes on the board
    * @param dNotes the list of notes discarded from the board
    */
   public Board(@NonNull String bid, @NonNull int pop) {
      this.boardId = bid;
      this.population = pop;
   }
   
   // getters
   public Long getId() {
      return id;
   }
   public String getBoardId() {
      return boardId;
   }
   public int getPopulation() {
      return population;
   }

   // setters
   public void setPopulation(int np) {
      population = np;
   }
}