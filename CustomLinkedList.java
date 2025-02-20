
// Import necessary Java utilities
import java.io.BufferedReader; // For reading files efficiently
import java.io.FileReader; // For reading files
import java.io.IOException; // For handling file errors
import java.util.Iterator; // For implementing iterator
import java.util.NoSuchElementException; // For iterator error handling

// Main class that implements our custom linked list
public class CustomLinkedList {
  // The first node in our list (head of the list)
  private Node head;

  // Keep track of how many items are in our list
  private int size;

  // Constructor - creates an empty list
  public CustomLinkedList() {
    this.head = null; // No nodes yet
    this.size = 0; // List starts empty
  }

  // Method to add a new number to our list
  public void insert(int data) {
    // Create a new node to hold our number
    Node newNode = new Node(data);

    // Increase the size of our list by 1
    size = size + 1;

    // If the list is empty, make the new node the first node
    if (head == null) {
      head = newNode;
      return;
    }

    // If list isn't empty, go to the end and add the new node there
    Node currentNode = head;

    // Keep moving to next node until we reach the end
    while (currentNode.next != null) {
      currentNode = currentNode.next;
    }

    // Add our new node at the end
    currentNode.next = newNode;
  }

  // Method to remove a number from our list
  public boolean delete(int data) {
    // If list is empty, we can't delete anything
    if (head == null) {
      return false;
    }

    // If the first node has our number, remove it
    if (head.data == data) {
      head = head.next; // Make the second node our new first node
      size = size - 1; // Decrease size by 1
      return true; // Tell user we succeeded
    }

    // If number isn't in first node, search through the list
    Node currentNode = head;
    Node previousNode = null;

    // Keep looking until we find our number or reach the end
    while (currentNode != null && currentNode.data != data) {
      previousNode = currentNode;
      currentNode = currentNode.next;
    }

    // If we found the number, remove it
    if (currentNode != null) {
      // Connect previous node to next node, skipping over our node
      previousNode.next = currentNode.next;
      size = size - 1; // Decrease size by 1
      return true; // Tell user we succeeded
    }

    // If we didn't find the number, tell user we failed
    return false;
  }

  // Method to get current size of list
  public int size() {
    return size;
  }

  // Method to check if list is empty
  public boolean isEmpty() {
    return size == 0;
  }

  // Method to get an iterator (lets us go through the list)
  public Iterator<Integer> iterator() {
    return new LinkedListIterator();
  }

  // Method to read numbers from a file and add them to our list
  public void loadFromFile(String filename) throws IOException {
    // Create a file reader
    BufferedReader reader = new BufferedReader(new FileReader(filename));

    // Read the file line by line
    String line;
    while ((line = reader.readLine()) != null) {
      try {
        // Convert each line to a number and add it to our list
        int number = Integer.parseInt(line.trim());
        insert(number);
      } catch (NumberFormatException e) {
        // If a line isn't a valid number, print an error
        System.err.println("Skipping invalid number: " + line);
      }
    }

    // Close the file when we're done
    reader.close();
  }

  // Inner class to represent each node in our list
  private class Node {
    int data; // The number stored in this node
    Node next; // Link to the next node

    // Constructor to create a new node
    Node(int data) {
      this.data = data;
      this.next = null;
    }
  }

  // Inner class that lets us iterate through our list
  private class LinkedListIterator implements Iterator<Integer> {
    // Keep track of where we are in the list
    private Node current = head;

    // Check if there's another number after this one
    @Override
    public boolean hasNext() {
      return current != null;
    }

    // Get the next number in the list
    @Override
    public Integer next() {
      // If there are no more numbers, throw an error
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      // Get the current number
      int data = current.data;

      // Move to the next node
      current = current.next;

      // Return the number we got
      return data;
    }
  }

  // Convert our list to a string so we can print it
  @Override
  public String toString() {
    // If list is empty, return empty brackets
    if (isEmpty()) {
      return "[]";
    }

    // Create a string builder to build our result
    StringBuilder result = new StringBuilder("[");

    // Go through each node
    Node current = head;
    while (current != null) {
      // Add the current number to our result
      result.append(current.data);

      // If there's another number coming, add a comma
      if (current.next != null) {
        result.append(", ");
      }

      // Move to next node
      current = current.next;
    }

    // Close the brackets and return the result
    result.append("]");
    return result.toString();
  }
}