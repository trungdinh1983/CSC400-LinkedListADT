// Class to test our CustomLinkedList
public class Main {
  public static void main(String[] args) {
    // Create a new empty list
    CustomLinkedList list = new CustomLinkedList();

    // Test 1: Adding numbers
    System.out.println("Test 1: Adding numbers to the list");
    list.insert(1);
    list.insert(2);
    list.insert(3);
    System.out.println("List after adding 1, 2, 3: " + list);

    // Test 2: Using iterator to go through list
    System.out.println("\nTest 2: Going through the list with iterator");
    Iterator<Integer> iterator = list.iterator();
    System.out.print("List items: ");
    while (iterator.hasNext()) {
      System.out.print(iterator.next() + " ");
    }
    System.out.println();

    // Test 3: Removing a number
    System.out.println("\nTest 3: Removing number 2 from list");
    list.delete(2);
    System.out.println("List after removing 2: " + list);

    // Test 4: Reading from file
    System.out.println("\nTest 4: Reading numbers from file");
    try {
      // Create a new list for file numbers
      CustomLinkedList fileList = new CustomLinkedList();

      // Try to read numbers from our file
      fileList.loadFromFile("numbers.txt");
      System.out.println("Numbers loaded from file: " + fileList);
    } catch (IOException e) {
      // If there's an error with the file, print it
      System.err.println("Error reading file: " + e.getMessage());
    }
  }
}