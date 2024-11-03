package org.example.tasks

class Task(val title: String) {
    var isCompleted: Boolean = false


    fun complete() {
        isCompleted = true
        println("Task '$title' has been marked as completed.")
    }
}

class Project(val name: String) {
    private val tasks = mutableListOf<Task>()

    fun addTask(task: Task) {
        tasks.add(task)
        println("Task '${task.title}' has been added to project '$name'.")
    }

    fun listTasks() {
        println("Tasks in project '$name':")
        if (tasks.isEmpty()) {
            println("No tasks found.")
        } else {
            for (task in tasks) {
                val status = if (task.isCompleted) "Completed" else "Pending"
                println("- ${task.title}: $status")
            }
        }
    }

    fun completeTask(taskTitle: String) {
        val task = tasks.find { it.title == taskTitle }
        task?.complete() ?: println("Task '$taskTitle' not found.")
    }
}


class User(private val username: String) {
    private val projects = mutableListOf<Project>()

    fun createProject(name: String): Project {
        val project = Project(name)
        projects.add(project)
        println("$username created a new project: '$name'.")
        return project
    }


    fun listProjects() {
        println("Projects for user '$username':")
        if (projects.isEmpty()) {
            println("No projects found.")
        } else {
            for (project in projects) {
                println("- ${project.name}")
            }
        }
    }


    fun getProject(name: String): Project? {
        return projects.find { it.name == name }
    }
}


fun main() {
    val user = User("Morri")


    var running = true
    while (running) {
        println("""
            |Manager Menu
            |1. Create Project
            |2. Add Task to Project
            |3. Complete Task
            |4. List Projects
            |5. List Tasks in Project
            |6. Exit
        """.trimMargin())

        print("Choose an option: ")
        val choice = readln()

        when (choice) {
            "1" -> {
                print("Enter project name: ")
                val projectName = readln()
                user.createProject(projectName)
            }
            "2" -> {
                print("Enter project name: ")
                val projectName = readln()
                val project = user.getProject(projectName)
                if (project != null) {
                    print("Enter task title: ")
                    val taskTitle = readln()
                    val task = Task(taskTitle)
                    project.addTask(task)
                } else {
                    println("Project '$projectName' not found.")
                }
            }
            "3" -> {
                print("Enter project name: ")
                val projectName = readln()
                val project = user.getProject(projectName)
                if (project != null) {
                    print("Enter task title to complete: ")
                    val taskTitle = readln()
                    project.completeTask(taskTitle)
                } else {
                    println("Project '$projectName' not found.")
                }
            }
            "4" -> {
                user.listProjects()
            }
            "5" -> {
                print("Enter project name: ")
                val projectName = readln()
                val project = user.getProject(projectName)
                if (project != null) {
                    project.listTasks()
                } else {
                    println("Project '$projectName' not found.")
                }
            }
            "6" -> {
                println("Exiting the task manager. . . Goodbye!!!")
                running = false
            }
            else -> {
                println("Invalid option. Please try again.")
            }
        }
    }
}