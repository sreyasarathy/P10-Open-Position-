//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Application Class
// Course:   CS 300 Spring 2022 
//
// Author:   Sreya Sarathy 
// Email:    sarathy2@wisc.edu
// Lecturer: Prof Mouna Kacem 
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:   Naman Parekh 
// Partner Email:   ncparekh@wisc.edu
// Partner Lecturer's Name: Prof Hobbes LeGault
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X_ Write-up states that pair programming is allowed for this assignment.
//   _X_ We have both read and understand the course Pair Programming Policy.
//   _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models a application with a name and due date
 */

public class Application implements Comparable<Application>{
    private final String name;  // name of this applicant
    private final String email; // email of this applicant
    private final int score;    // estimated score of this applicant
    
    /**
     * Creates a new Application with the given information
     *
     * @param name       name of this applicant
     * @param email      email of this applicant
     * @param score      estimated score of this applicant (must be in the range 0 .. 100)
     * @throws IllegalArgumentException if the provided name is null or blank, or if the email is
     *                                  null or does not have a single {@literal @}, or if score
     *                                  is not in the 0 .. 100 range.
     */
    
    public Application(String name, String email, int score) throws IllegalArgumentException{
        
      //if the provided name is null or blank
      if(name.isBlank() || name == null) {
        throw new IllegalArgumentException("Error: The name is invalid!!");
      }

      //if the email is null or does not have a single {@literal@}
      if (email == null || email == "") {
          throw new IllegalArgumentException("Error: The email is null or blank!");
        }
      if (email.contains("@")) {
          int counter = 0;
          
          for (int a = 0; a < email.length(); a++) {
            if (email.charAt(a) == '@') {
              counter++;
            }
            if (counter >= 2) {
              throw new IllegalArgumentException("Error: The email has too many @s");
            }
          }
        }
      else {
          throw new IllegalArgumentException("Error: The email has no @");
        }

      //if score is not in the 0 .. 100 range
      if(score < 0 || score > 100) {
        throw new IllegalArgumentException("Error: The score is invalid!");
      }

      this.name = name;
      this.email = email;
      this.score = score;
    }

    /**
     * Returns the name of this Applicant
     * 
     * @return the name of this Applicant
     */
    
    public String getName() {
        return this.name;
    }

    /**
     * Returns the email of this Applicant
     * 
     * @return the email of this Applicant
     */
    
    public String getEmail() {
        return this.email;
    }

    /**
     * Returns the score of this Applicant
     * 
     * @return the score of this Applicant
     */
    
    public int getScore() {
        return this.score;
    }
    
    /**
     * Compares this Applicant to another applicant based on their score
     * 
     * @return a negative integer if this Applicant has an lower score, {@code 0} if the two
     *         Applicants have the same score, and a positive integer if this
     *         Applicant has a higher score.
     * @throws NullPointerException if the other assignment o is null
     */
    
    @Override
    public int compareTo(Application other) throws NullPointerException {
    	
      if(other == null) {
        throw new NullPointerException("Error: The application is null!!");
      }
      
      if(other.getScore() > this.score) {
    	  return -1;
      }
      
      if(other.getScore() == this.score) {
    	  return 0;
      }
      
      if(other.getScore() < this.score) {
    	  return 1;
      }

      return 0;
    }

    /**
     * Returns a String representing this Application containing its name, email and score.
     * (This implementation is provided for you.)
     * 
     * @return a String representing this Application
     */
    
    @Override
    public String toString() {
        return name + ":" + email + ":" + score;
    }
}