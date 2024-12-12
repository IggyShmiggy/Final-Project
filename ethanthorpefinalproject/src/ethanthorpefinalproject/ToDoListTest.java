package ethanthorpefinalproject;

public class ToDoListTest {

	// Main method, used to run tests. (methods below)
    public static void main(String[] args) {
        testAddTask();
        testRemoveTask();
        testSortList();
    }
    // Test method that will perform a check of the addTask method for the ToDoList class.
    private static void testAddTask() {
    	// Creates an instance of the ToDoList class.
        ToDoList toDoList = new ToDoList();
        // Adds a generic sample task to the list.
        toDoList.addTask("Task 1");
        // Then tests to see if that was added properly.
        if (toDoList.taskList.size() != 1 || !toDoList.taskList.get(0).equals("Task 1")) {
        	// If not, then print a message to signify it worked incorrectly.
            System.out.println("testAddTask failed");
        } else {
            System.out.println("testAddTask passed");
        }
    }
    // Test method that will check to see if the removeTask method from the ToDoList class functions correctly.
    private static void testRemoveTask() {
    	// Creates another new instance of the ToDoList class as seen above. (Going to just copy + paste this line for all future same iterations of this)
        ToDoList toDoList = new ToDoList();
        // Adds a generic sample task to the instance
        toDoList.addTask("Task 1");
        // Attempts to remove the sample task from the list
        toDoList.removeTask("Task 1");
        // Checks to see if it was properly removed.
        if (toDoList.taskList.size() != 0) {
        	// If not, prints out a message that says it failed.
            System.out.println("testRemoveTask failed");
        } else {
            System.out.println("testRemoveTask passed");
        }
    }
    // Final test method that will check to see if when the list has a number of improperly ordered tasks, when told to order, it orders them correctly.
    private static void testSortList() {
    	// Creates another new instance of the ToDoList class as seen above. 
        ToDoList toDoList = new ToDoList();
        // Creates THREE tasks THAT ARE OUT OF ORDER and adds them to the ToDoList instance.
        toDoList.addTask("Task 3");
        toDoList.addTask("Task 1");
        toDoList.addTask("Task 2");
        // Prompts the ToDoList to sort itself.
        toDoList.sortList();
        // This will then check to see if the list was actually sorted correctly as we expect it to be sorted (Expected output vs actual output)
        if (!toDoList.taskList.get(0).equals("Task 1") || !toDoList.taskList.get(1).equals("Task 2") || !toDoList.taskList.get(2).equals("Task 3")) {
        	// If it is not as we expect it to be, then fail.
            System.out.println("testSortList failed");
        } else {
            System.out.println("testSortList passed");
        }
    }
}
