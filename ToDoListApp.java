import java.io.*;
import java.util.*;

public class ToDoListApp {
    private static final String FILE_NAME = "tasks.txt";  
    private static ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasksFromFile();  

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n To-Do List Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Delete Task");
            System.out.println("3. View Tasks");
            System.out.println("4. Mark Task as Completed");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addTask(scanner);
                    break;
                case 2:
                    deleteTask(scanner);
                    break;
                case 3:
                    viewTasks();
                    break;
                case 4:
                    markTaskCompleted(scanner);
                    break;
                case 5:
                    saveTasksToFile();
                    System.out.println(" Exiting... Tasks saved.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }

    
    private static void loadTasksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String task;
            while ((task = reader.readLine()) != null) {
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("No previous tasks found. Starting fresh.");
        }
    }

   
    private static void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String task : tasks) {
                writer.write(task);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(" Error saving tasks.");
        }
    }

    
    private static void addTask(Scanner scanner) {
        System.out.print("Enter task: ");
        String task = scanner.nextLine();
        tasks.add("[ ] " + task); 
        System.out.println(" Task added.");
    }

    
    private static void deleteTask(Scanner scanner) {
        viewTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to delete: ");
        int index = scanner.nextInt();
        if (index > 0 && index <= tasks.size()) {
            tasks.remove(index - 1);
            System.out.println(" Task deleted.");
        } else {
            System.out.println(" Invalid task number.");
        }
    }

    
    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println(" No tasks available.");
        } else {
            System.out.println("\n To-Do List:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    
    private static void markTaskCompleted(Scanner scanner) {
        viewTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to mark as completed: ");
        int index = scanner.nextInt();
        if (index > 0 && index <= tasks.size()) {
            String task = tasks.get(index - 1);
            if (task.startsWith("[ ]")) {
                tasks.set(index - 1, "[✔] " + task.substring(4)); 
                System.out.println(" Task marked as completed.");
            } else {
                System.out.println(" Task is already completed.");
            }
        } else {
            System.out.println(" Invalid task number.");
        }
    }
}
