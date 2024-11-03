package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    private final String title;
    private boolean isCompleted;

    public Task(String title) {
        this.title = title;
        this.isCompleted = false;
    }

    public void complete() {
        isCompleted = true;
        System.out.println("Task '" + title + "' has been marked as completed.");
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getTitle() {
        return title;
    }
}

class Project {
    private final String name;
    private final List<Task> tasks;

    public Project(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Task '" + task.getTitle() + "' has been added to project '" + name + "'.");
    }

    public void listTasks() {
        System.out.println("Tasks in project '" + name + "':");
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            for (Task task : tasks) {
                String status = task.isCompleted() ? "Completed" : "Pending";
                System.out.println("- " + task.getTitle() + ": " + status);
            }
        }
    }

    public void completeTask(String taskTitle) {
        Task task = tasks.stream().filter(t -> t.getTitle().equals(taskTitle)).findFirst().orElse(null);
        if (task != null) {
            task.complete();
        } else {
            System.out.println("Task '" + taskTitle + "' not found.");
        }
    }

    public String getName() {
        return name;
    }
}

class User {
    private final String username;
    private final List<Project> projects;

    public User(String username) {
        this.username = username;
        this.projects = new ArrayList<>();
    }

    public Project createProject(String name) {
        Project project = new Project(name);
        projects.add(project);
        System.out.println(username + " created a new project: '" + name + "'.");
        return project;
    }

    public void listProjects() {
        System.out.println("Projects for user '" + username + "':");
        if (projects.isEmpty()) {
            System.out.println("No projects found.");
        } else {
            for (Project project : projects) {
                System.out.println("- " + project.getName());
            }
        }
    }

    public Project getProject(String name) {
        return projects.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
    }
}

class TaskManager {
    public static void main(String[] args) {
        User user = new User("Morri");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("""
                    |Manager Menu
                    |1. Create Project
                    |2. Add Task to Project
                    |3. Complete Task
                    |4. List Projects
                    |5. List Tasks in Project
                    |6. Exit
                    """);

            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.print("Enter project name: ");
                    String projectName = scanner.nextLine();
                    user.createProject(projectName);
                }
                case "2" -> {
                    System.out.print("Enter project name: ");
                    String projectName = scanner.nextLine();
                    Project project = user.getProject(projectName);
                    if (project != null) {
                        System.out.print("Enter task title: ");
                        String taskTitle = scanner.nextLine();
                        Task task = new Task(taskTitle);
                        project.addTask(task);
                    } else {
                        System.out.println("Project '" + projectName + "' not found.");
                    }
                }
                case "3" -> {
                    System.out.print("Enter project name: ");
                    String projectName = scanner.nextLine();
                    Project project = user.getProject(projectName);
                    if (project != null) {
                        System.out.print("Enter task title to complete: ");
                        String taskTitle = scanner.nextLine();
                        project.completeTask(taskTitle);
                    } else {
                        System.out.println("Project '" + projectName + "' not found.");
                    }
                }
                case "4" -> user.listProjects();
                case "5" -> {
                    System.out.print("Enter project name: ");
                    String projectName = scanner.nextLine();
                    Project project = user.getProject(projectName);
                    if (project != null) {
                        project.listTasks();
                    } else {
                        System.out.println("Project '" + projectName + "' not found.");
                    }
                }
                case "6" -> {
                    System.out.println("Exiting the task manager. . . Goodbye!!!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}