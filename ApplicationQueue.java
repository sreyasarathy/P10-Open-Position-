//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Application Queue Class
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
 * Array-based heap implementation of a priority queue containing Applications. Guarantees the
 * min-heap invariant, so that the Application at the root should have the lowest score, and
 * children always have a higher or equal score as their parent. The root of a non-empty queue is
 * always at index 0 of this array-heap.
 */

public class ApplicationQueue implements PriorityQueueADT<Application>, Iterable<Application> {
  private Application[] queue; // array min-heap of applications representing this priority queue
  private int size; // size of this priority queue

  /**
   * Creates a new empty ApplicationQueue with the given capacity
   * 
   * @param capacity Capacity of this ApplicationQueue
   * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
   *                                  positive integer
   */
  
  public ApplicationQueue(int capacity) {
	  
	  if (capacity <= 0) {
      throw new IllegalArgumentException("Error: The capacity is not a positive integer!");
    }
    queue = new Application[capacity];
    size = 0;
  }

  /**
   * Checks whether this ApplicationQueue is empty
   * 
   * @return {@code true} if this ApplicationQueue is empty
   */
  
  @Override
  public boolean isEmpty() {
    
	  if (size == 0) {
      return true;
    }
    return false;
  }

  /**
   * Returns the size of this ApplicationQueue
   * 
   * @return the size of this ApplicationQueue
   */
  
  @Override
  public int size() {
    return this.size; 
  }

  /**
   * Adds the given Application to this ApplicationQueue and use the percolateUp() method to
   * maintain min-heap invariant of ApplicationQueue. Application should be compared using the
   * Application.compareTo() method.
   * 
   * 
   * @param o Application to add to this ApplicationQueue
   * @throws NullPointerException  if the given Application is null
   * @throws IllegalStateException with a descriptive error message if this ApplicationQueue is full
   */
  
  @Override
  public void enqueue(Application o) {
    
	  if (o == null) {
      throw new NullPointerException("Error: The application is null!");
    }
	  if (size == queue.length) {
      throw new IllegalStateException("Error: The queue is full!");
    }
    queue[size] = o;
    percolateUp(size);
    size++;
  }

  /**
   * Removes and returns the Application at the root of this ApplicationQueue, i.e. the Application
   * with the lowest score.
   * 
   * @return the Application in this ApplicationQueue with the smallest score
   * @throws NoSuchElementException with a descriptive error message if this ApplicationQueue is
   *                                empty
   */
  
  @Override
  public Application dequeue() {
    
	  if (size == 0) {
      throw new NoSuchElementException("Error: The application queue is empty!");
    }
    
	Application lowScore = queue[0];
    queue[0] = queue[size - 1];
    queue[size - 1] = null;
    percolateDown(0);
    size--;
    
    return lowScore;
  }

  /**
   * An implementation of percolateDown() method. Restores the min-heap invariant of a given subtree
   * by percolating its root down the tree. If the element at the given index does not violate the
   * min-heap invariant (it is due before its children), then this method does not modify the heap.
   * Otherwise, if there is a heap violation, then swap the element with the correct child and
   * continue percolating the element down the heap.
   * 
   * This method may be implemented recursively OR iteratively.
   * 
   * @param i index of the element in the heap to percolate downwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  
  private void percolateDown(int i) throws IndexOutOfBoundsException {
    if (i >= size) {
      throw new IndexOutOfBoundsException("Error: The index is out of bounds.");
    }
    if ((2 * i) + 2 > queue.length) {
      return;
    }
    Application initialApplication;
    try {
      initialApplication = queue[(2 * i) + 1];
    } 
    catch (Exception err) {
      initialApplication = null;
    }
    Application applicationTwo;
    try {
      applicationTwo = queue[(2 * i) + 2];
    } 
    catch (Exception err) {
      applicationTwo = null;
    }
    Application tempApplication;
    int latIndex;
    
    if (initialApplication == null && applicationTwo != null) {
      tempApplication = applicationTwo;
      latIndex = (2 * i) + 2;
    } 
    else if (initialApplication != null && applicationTwo == null) {
      tempApplication = initialApplication;
      latIndex = (2 * i) + 1;
    } 
    else if (initialApplication == null && applicationTwo == null) {
      return;
    } 
    else {
      if (applicationTwo.compareTo(initialApplication) < 0) {
        tempApplication = applicationTwo;
        latIndex = (2 * i) + 2;
      } 
      else if (initialApplication.compareTo(applicationTwo) < 0) {
        tempApplication = initialApplication;
        latIndex = (2 * i) + 1;
      } 
      else {
        tempApplication = initialApplication;
        latIndex = (2 * i) + 1;
      }
    }
    if (queue[i] == null) {
      return;
    }
    if (queue[i].compareTo(tempApplication) > 0) {
      tempApplication = queue[i];
      queue[i] = queue[latIndex];
      queue[latIndex] = tempApplication;
      percolateDown(latIndex);
    }
  }

  /**
   * An implementation of percolateUp() method. Restores the min-heap invariant of the tree by
   * percolating a leaf up the tree. If the element at the given index does not violate the min-heap
   * invariant (it occurs after its parent), then this method does not modify the heap. Otherwise,
   * if there is a heap violation, swap the element with its parent and continue percolating the
   * element up the heap.
   * 
   * This method may be implemented recursively OR iteratively.
   * 
   * Feel free to add private helper methods if you need them.
   * 
   * @param i index of the element in the heap to percolate upwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  
  private void percolateUp(int i) {
    
	int parentIndex = (i - 2) / 2;
    if (i == 0) {
      return;
    }
    if (queue[i].compareTo(queue[parentIndex]) < 0) {
      Application parent_Application = queue[parentIndex];
      queue[parentIndex] = queue[i];
      queue[i] = parent_Application;
      percolateUp(parentIndex);
    } 
    else {
      return;
    }
  }

  /**
   * Returns the Application at the root of this ApplicationQueue, i.e. the Application with the
   * lowest score.
   * 
   * @return the Application in this ApplicationQueue with the smallest score
   * @throws NoSuchElementException if this ApplicationQueue is empty
   */
  
  @Override
  public Application peek() {
    if (size == 0) {
      throw new NoSuchElementException("Error: The application queue is empty!");
    }
    return queue[0];
  }

  /**
   * Returns a deep copy of this ApplicationQueue containing all of its elements in the same order.
   * This method does not return the deepest copy, meaning that you do not need to duplicate
   * applications. Only the instance of the heap (including the array and its size) will be
   * duplicated.
   * 
   * @return a deep copy of this ApplicationQueue. The returned new application queue has the same
   *         length and size as this queue.
   */
  
  public ApplicationQueue deepCopy() {
    ApplicationQueue newestQueue = new ApplicationQueue(queue.length);
    
    for (int a = 0; a < queue.length; a++) {
      if (queue[a] == null) {
        return newestQueue;
      }
      newestQueue.enqueue(queue[a]);
    }
    return newestQueue;
  }

  /**
   * Returns a String representing this ApplicationQueue, where each element (application) of the
   * queue is listed on a separate line, in order from the lowest score to the highest score.
   * 
   * This implementation is provided.
   * 
   * @see Application#toString()
   * @see ApplicationIterator
   * @return a String representing this ApplicationQueue
   */
  
  @Override
  public String toString() {
    StringBuilder val = new StringBuilder();
    for (Application a : this) {
      val.append(a).append("\n");
    }
    return val.toString();
  }

  /**
   * Returns an Iterator for this ApplicationQueue which proceeds from the lowest-scored to the
   * highest-scored Application in the queue.
   * 
   * This implementation is provided.
   * 
   * @see ApplicationIterator
   * @return an Iterator for this ApplicationQueue
   */
  
  @Override
  public Iterator<Application> iterator() {
    return new ApplicationIterator(this);
  }
}