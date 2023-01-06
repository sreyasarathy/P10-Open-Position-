//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Open Position Tester Class
// Course:   CS 300 Spring 2022 
//
// Author:   Sreya Sarathy 
// Email:    sarathy2@wisc.edu
// Lecturer: Prof Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Naman Parekh 
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

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * This class implements unit test methods to check the correctness of Application, 
 * ApplicationIterator, ApplicationQueue and OpenPosition classes in the assignment.
 *
 */
public class OpenPositionTester {
    
	/**
     * This method tests and makes use of the Application constructor, getter methods,
     * toString() and compareTo() methods.
     * 
     * @return true when this test verifies the functionality, and false otherwise
     */
    public static boolean testApplication() {
      
      // create an Application with valid input    
      try {
        Application userApplication = new Application("Sreya", "sarathy2@wisc.edu", 0);
      } 
      catch (Exception err){
        return false;
      }
      
      try {
        Application userApplication = new Application("Sreya", "sarathy2@wisc.edu", 100);
      } 
      catch (Exception err){
        return false;
      }
      
      try {
        Application userApplication = new Application("Sreya", "sarathy2@wisc.edu", 65);
      } 
      catch (Exception err){
        return false;
      }

      // create an Application with invalid input:
      try {
        // blank name
        Application userApplication = new Application("", "sarathy2@wisc.edu", 75);
        return false;
      } 
      catch (IllegalArgumentException err) {
          
      } 
      catch(Exception err) {
          return false;
      }
      
      // null email
      try {
        Application userApplication = new Application("Sreya", null, 75);
        return false;
      } 
      catch (IllegalArgumentException err) {
        
      } 
      catch(Exception err) {
          return false;
      }
      
      // no @ email
      try {
        Application userApplication = new Application("Sreya", "sarathy2wisc.edu", 75);
        return false;
      } 
      catch (IllegalArgumentException err) {
        
      } 
      catch(Exception err) {
          return false;
      }
        
      // too many @ email
      try {
        Application userApplication = new Application("Sreya", "s@a@r@a@t@h@y2@wisc.edu", 75);
        return false;
      } 
      catch (IllegalArgumentException err) {
        
      } 
      catch(Exception err) {
          return false;
      }
       
      // invalid score
      try {
        Application userApplication = new Application("Sreya", "sarathy2@wisc.edu", -75);
        return false;
      } 
      catch (IllegalArgumentException err) {
        
      }
      catch(Exception err) {
          return false;
      }
      
      try {
        Application userApplication = new Application("Sreya", "sarathy2@wisc.edu", 150);
        return false;
      } 
      catch (IllegalArgumentException err) {
        
      } 
      catch(Exception err) {
          return false;
      }
      
      // verify getters
        Application userApplication = new Application("Sreya", "sarathy2@wisc.edu", 75);
        
        if(!(userApplication.getName().equals("Sreya"))) {
        	return false;
        }
        
        if(!(userApplication.getEmail().equals("sarathy2@wisc.edu"))) {
        	return false;
        }
        
        if(userApplication.getScore() != 75) {
        	return false;
        }
      
      // verify compareTo
      try { 
        Application applicationTwo = new Application("Sreya", "sarathy2@wisc.edu", 60);
        Application applicationThree = new Application("Sreya", "sarathy2@wisc.edu", 75);
        Application applicationFour = new Application("Sreya", "sarathy2@wisc.edu", 80);   
        
        if(!(userApplication.compareTo(applicationFour) < 0)) {
        	return false;
        }
        
        if(!(userApplication.compareTo(applicationTwo) > 0)) {
        	return false;
        }

        if(!(userApplication.compareTo(applicationThree) == 0)) {
        	return false;
        }
              
      } catch (Exception err) {
        return false;
      }
      
      // verify toString
      String stringApplication = "Sreya:sarathy2@wisc.edu:75";  
      if (!(userApplication.toString().equals(stringApplication))) {
    	  return false;          
      }
        return true;
    }
    
    /**
     * This method tests and makes use of the ApplicationIterator class.
     * 
     * @return true when this test verifies the functionality, and false otherwise
     */
    
    public static boolean testApplicationIterator() {
    	// create an ApplicationQueue with capacity at least 3
    	// and at least 3 Applications with different scores
    	
    	ApplicationQueue queue = new ApplicationQueue(6);
        
    	Application applicationOne = new Application("Sreya", "sarathy2@wisc.edu", 50);
        Application applicationTwo = new Application("Naman", "ncparekh@wisc.edu", 45);
        Application applicationThree = new Application("John", "john2@wisc.edu", 70);
        Application applicationFour = new Application("Sai", "saikaran@wisc.edu", 80);
        Application applicationFive = new Application("Carly", "carly@wisc.edu", 90);
        Application applicationSix = new Application("Sheesh", "sheesh@wisc.edu", 60);
        
    	
    	// add those Applications to the queue
        
        queue.enqueue(applicationOne);
        queue.enqueue(applicationTwo);
        queue.enqueue(applicationThree);
        queue.enqueue(applicationFour);
        queue.enqueue(applicationFive);
        queue.enqueue(applicationSix);
    	
    	// verify that iterating through the queue gives you the applications in order of
    	// INCREASING score
        ApplicationIterator itTst = new ApplicationIterator(queue);
        
        if (!(itTst.next() == applicationTwo && itTst.next() == applicationOne && itTst.next() == applicationSix &&
        		itTst.next() == applicationThree
        		&& itTst.next() == applicationFour && itTst.next() == applicationFive)) {
        	return false;
        }
        
        return true;
    }
    
    /**
     * This method tests and makes use of the enqueue() and dequeue() methods
     * in the ApplicationQueue class.
     * 
     * @return true when this test verifies the functionality, and false otherwise
     */
    public static boolean testEnqueueDequeue() {
    	
    	// create an ApplicationQueue with capacity 3
    	// and at least 4 Applications with different scores
    	ApplicationQueue queue = new ApplicationQueue(3);
        
    	Application applicationOne = new Application("Sreya", "sarathy2@wisc.edu", 65);
        Application applicationTwo = new Application("Naman", "ncparekh@wisc.edu", 60);
        Application applicationThree = new Application("John", "john2@wisc.edu", 50); 
        Application applicationFour = new Application("Sai", "saikaran@wisc.edu", 45);
        

        // enqueue an invalid value (null)
        try {
            queue.enqueue(null);
            return false;
          } 
        catch(NullPointerException err) {
        }
        catch (Exception err) {
            return false;
          }
        
        // enqueue one valid application
        try {
            queue.enqueue(applicationOne);
          } 
        catch(NullPointerException err) {
            return false;
          } 
        catch (Exception err) {
            return false;
          }
        
        // enqueue two more valid applications
        try {
            queue.enqueue(applicationTwo);
            queue.enqueue(applicationThree);
          } 
        catch(NullPointerException err) {
            return false;
          } 
        catch (Exception err) {
            return false;
          }
        
        // enqueue one more application (exceeds capacity)
        try {
            queue.enqueue(applicationFour);
          } 
        catch(IllegalStateException err) {
            
          } 
        catch (Exception err) {
            return false;
          }
    	
    	// dequeue one application (should be lowest score)
        try {
            if(queue.dequeue().compareTo(applicationThree) != 0) {
            	return false;       
            }
          } 
        catch (Exception err) {
            return false;
          }

        // dequeue all applications
        try {
            if(queue.dequeue().compareTo(applicationTwo) != 0) {
            	return false;
            }
            if(queue.dequeue().compareTo(applicationOne) != 0) {
            	return false;    
            }
          } 
        catch (Exception err) {
            return false;
          }
            
        
        // dequeue from an empty queue
        try {
            queue.dequeue();
            return false;
          } 
        catch (NoSuchElementException err) {
            
          } 
        catch (Exception err) {
            return false;
          } 
         
            return true;
        }
        
    /**
     * This method tests and makes use of the common methods (isEmpty(), size(), peek())
     * in the ApplicationQueue class.
     * 
     * @return true when this test verifies the functionality, and false otherwise
     */
    
    public static boolean testCommonMethods() {
    	
    	// create an ApplicationQueue with 0 capacity (should fail)
    	try {
            ApplicationQueue failQueue = new ApplicationQueue(0);
            return false;
           } 
    	catch (IllegalArgumentException err) {

           } 
    	catch (Exception err) {
             return false;
           }

    	// create an ApplicationQueue with capacity 3
    	// and at least 3 Applications with different scores
    	ApplicationQueue queue = new ApplicationQueue(3);
        
    	Application applicationOne = new Application("Sreya", "sarathy2@wisc.edu", 65);
        Application applicationTwo = new Application("Naman", "ncparekh@wisc.edu", 60);
        Application applicationThree = new Application("John", "john2@wisc.edu", 50); 
        
        // verify the methods' behaviors on an empty queue
        if(!(queue.isEmpty())) {
        	return false;
        }
        if(queue.size() != 0) {
        	return false;
        }
        try {
          queue.peek();
          return false;
        } 
        catch (NoSuchElementException err) {
          
        } 
        catch (Exception err) {
          return false;
        }    

        // add one Application and verify the methods' behaviors
        queue.enqueue(applicationOne);
        if(queue.isEmpty()) {
        	return false;
        }
        if(queue.size() != 1) {
        	return false;
        }
        if(queue.peek().compareTo(applicationOne) != 0) {
        	return false;
        }
        
        // add the rest of the Applications and verify the methods' behaviors
        queue.enqueue(applicationTwo);
        queue.enqueue(applicationThree);
        
        if(queue.isEmpty()) {
        	return false;
        }
        if(queue.size() != 3) {
        	return false;   
        }
        if(queue.peek().compareTo(applicationThree) != 0) {
        	return false;
        }

        return true; 
      }

     /**
     * This method tests and makes use of OpenPosition class.
     * 
     * @return true when this test verifies the functionality, and false otherwise
     */
    
    public static boolean testOpenPosition() {
        
    	// create an OpenPosition with 0 capacity (should fail)
    	try {
            OpenPosition failOpenPosition = new OpenPosition("Amber", 0);
            return false;
          } 
    	catch (IllegalArgumentException err) {
            
          } 
    	catch (Exception err) {
            return false;
          }
    	

    	// create an OpenPosition with capacity 3
    	// and at least 5 Applications with different scores
    	OpenPosition openPosition = new OpenPosition("Amber", 3);
        
    	Application applicationOne = new Application("Sreya", "sarathy2@wisc.edu", 65);
        Application applicationTwo = new Application("Naman", "ncparekh@wisc.edu", 60);
        Application applicationThree = new Application("John", "john2@wisc.edu", 50); 
        Application applicationFour = new Application("Sai", "saikaran@wisc.edu", 45);
        Application applicationFive = new Application("Carly", "carly@wisc.edu", 40);    
    	
        // verify that the 3 MIDDLE-scoring Applications can be added
    	// don't use the highest and lowest scoring applications YET
        if(!(openPosition.add(applicationTwo))) {
        	return false;
        }
        if(!(openPosition.add(applicationThree))) {
        	return false;
        }
        if(!(openPosition.add(applicationFour))) {
        	return false;
        }
        
        // verify that getApplications returns the correct value for your input
        String correctApplications = "Sai:saikaran@wisc.edu:45\n" 
        + "John:john2@wisc.edu:50\n"
        + "Naman:ncparekh@wisc.edu:60\n";
            
        if(!(openPosition.getApplications().equals(correctApplications))) {
            	return false;
            }
        
        // verify that the result of getTotalScore is the sum of all 3 Application scores
        if (openPosition.getTotalScore() != 155) {
        	return false; 
        }

        // verify that the lowest-scoring application is NOT added to the OpenPosition
        if (openPosition.add(applicationFive)) {
        	return false;
        }
        if (!(openPosition.getApplications().equals(correctApplications))) {
        	return false;
        }
        
        // verify that the highest-scoring application IS added to the OpenPosition
        if (!(openPosition.add(applicationOne))) {
        	return false;
        }
        
        // verify that getApplications has changed correctly
        correctApplications = "John:john2@wisc.edu:50\n" + "Naman:ncparekh@wisc.edu:60\n"
                + "Sreya:sarathy2@wisc.edu:65\n";
            if (!(openPosition.getApplications().equals(correctApplications))) {
            	return false;
            }
        
        // verify that the result of getTotalScore has changed correctly
        if (openPosition.getTotalScore() != 175) {
        	return false;
        }
            
        return true; 
    }
    
    /**
     * This method calls all the test methods defined and implemented in your OpenPositionTester class.
     * 
     * @return true if all the test methods defined in this class pass, and false otherwise.
     */
    
    public static boolean runAllTests() {
        return testApplication() && testApplicationIterator()
                && testEnqueueDequeue() && testCommonMethods()
                && testOpenPosition();
    }

    /**
     * Driver method defined in this OpenPositionTester class
     * 
     * @param args input arguments if any.
     */
    
    public static void main(String[] args) {
        System.out.print(runAllTests());
    }
}

    