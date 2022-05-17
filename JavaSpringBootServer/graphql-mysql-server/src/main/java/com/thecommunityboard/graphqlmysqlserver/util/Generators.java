package com.thecommunityboard.graphqlmysqlserver.util;
import java.util.Random;

public class Generators {
	private static Random gen = new Random(); // random generator
   private Generators(){} // can't be constructed
   
   // static generators
   /**
    * generate a user id string from the passed username
    * @param uname the username of the user
    * @return a string of the form "uname#XXXX" where "XXXX" is a sequence of digits
    */
   public static String genUid(String uname) {
      int digitSeq = Math.round(gen.nextFloat() * (Values.UID_MAX - Values.UID_MIN) + Values.UID_MIN);
      return uname + "#" + digitSeq;
   }
   
   /**
    * generate a postId string from the provided information
    * @param boardId the id of the board being posted on
    * @param authorId the id of the user posting
    * @param noteTitle the title of the post
    * @return a string of the shape "boardId:authorId:noteTitle"
    */
   public static String genPostId(String boardId, String authorId, String noteTitle) {
      return boardId + ':' + authorId + ':' + noteTitle.replace(' ', '-');
   }
}