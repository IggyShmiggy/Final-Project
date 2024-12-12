package ethanthorpefinalproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.*;

// ToDo class represents a single task in the to-do list
// This is a linked list data structure fyi, 1st structure. NON-GUI INTERFACING (directly)
class ToDo {
    String task;
    ToDo next;

    // Constructor to initialize a new task
    public ToDo(String task) {
        this.task = task;
        next = null;
    }
}

// ToDoList class represents the to-do list application. This is the beginning of the GUI Framework
// 2nd data structure. Stores tasks in separate data structure. Interfaces with GUI directly.
class ToDoList extends JFrame implements Serializable {
    private static final long serialVersionUID = 1L;

    private JTextField taskField;
    private JButton addButton;
    private JButton removeButton;
    private JButton sortButton;
    private JTextArea taskListArea;
    //ToDoList GUI adding an ArrayList variable.
    ToDo head;
    ArrayList<String> taskList;

    // Constructor to initialize the GUI components and event listeners, such as Adding buttons and text fields on those buttons for the main UI Interface
    // Such as 'Add task' 'Remove Task' etc. The three main buttons.
    public ToDoList() {
        taskField = new JTextField(20);
        addButton = new JButton("Add Task");
        removeButton = new JButton("Remove Task");
        sortButton = new JButton("Sort List");
        taskListArea = new JTextArea(10, 20);
        taskList = new ArrayList<String>();

        // Add action listener to the add button. This basically waits to see if there is input from the User on the button, and if there is, it performs
        // The 'addTask' method.
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ToDoList.this.addTask(taskField.getText());
                updateTaskListArea();
            }
        });

        // Add action listener to the remove button. (All of these buttons do what is described above, going to repeat beginning text so I know which ones do.)
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ToDoList.this.removeTask(taskField.getText());
                updateTaskListArea();
            }
        });

        // Add action listener to the sort button
        sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ToDoList.this.sortList();
                updateTaskListArea();
            }
        });

        // Create input panel and add components. Very basic, this is just putting these elements on screen. (That's what the next three area's are doing.)
        JPanel inputPanel = new JPanel();
        inputPanel.add(taskField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);
        inputPanel.add(sortButton);

        // Create list panel and add components
        JPanel listPanel = new JPanel();
        listPanel.add(new JScrollPane(taskListArea));

        // Set layout and add panels to the frame
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);

        // Set default close operation and make the frame visible
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    // Method to update the task list area with the current tasks. This means when the User adds new tasks it will properly show them on the screen.
    private void updateTaskListArea() {
        taskListArea.setText("");
        for (String task : taskList) {
            taskListArea.append(task + "\n");
        }
    }

    // Method to add a new task to the to-do list
    public void addTask(String task) {
        // Check if the task is empty or null. INPUT VALIDATION!!
        if (task == null || task.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Task cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Create a new ToDo object and add it to the list.
        ToDo newTask = new ToDo(task);
        if (head == null) {
            head = newTask;
        } else {
            ToDo current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newTask;
        }
        taskList.add(task);
    }

    // Method to remove a task from the to-do list
    public void removeTask(String task) {
        // Check if the task is empty or null INPUT VALIDATION!!
        if (task == null || task.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Task cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Find the task in the list and remove it
        if (head == null) {
            return;
        }
        if (head.task.equals(task)) {
            head = head.next;
            taskList.remove(task);
            return;
        }
        ToDo current = head;
        while (current.next != null) {
            if (current.next.task.equals(task)) {
                current.next = current.next.next;
                taskList.remove(task);
                return;
            }
            current = current.next;
        }
    }

    // Method to sort the to-do list using merge sort
    // Sorting method.
    public void sortList() {
        head = mergeSort(head);
        taskList.clear();
        ToDo current = head;
        while (current != null) {
            taskList.add(current.task);
            current = current.next;
        }
    }

    // Helper method to merge sort the linked list
    private ToDo mergeSort(ToDo head) {
        if (head == null || head.next == null) {
            return head;
        }
        ToDo middle = getMiddle(head);
        ToDo nextOfMiddle = middle.next;
        middle.next = null;
        return merge(mergeSort(head), mergeSort(nextOfMiddle));
    }

    // Helper method to merge two sorted linked lists
    private ToDo merge(ToDo left, ToDo right) {
        ToDo result = null;
        if (left == null)
            return right;
        if (right == null)
            return left;
        if (left.task.compareTo(right.task) <= 0) {
            result = left;
            result.next = merge(left.next, right);
        } else {
            result = right;
            result.next = merge(left, right.next);
        }
        return result;
    }

    // Helper method to find the middle of a linked list
    private ToDo getMiddle(ToDo head) {
        if (head == null)
            return head;
        ToDo slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Main method to create and run the to-do list application
    public static void main(String[] args) {
        new ToDoList();
    }
}

