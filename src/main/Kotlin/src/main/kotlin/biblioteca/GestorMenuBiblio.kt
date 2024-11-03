package org.example.biblioteca

class GestorMenuBiblio(private val library: Library) {

    private fun menuPrint() {
        println("\n--- Library Menu ---")
        println("1. Add a new user")
        println("2. Add a new book")
        println("3. Borrow a book")
        println("4. Show catalog")
        println("5. Exit")
        print("Please select an option: ")
    }

    fun menuOptions() {
        var exit = false
        while (!exit) {
            menuPrint()
            when (readln().toIntOrNull()) {
                1 -> library.addUser()
                2 -> library.addBook()
                3 -> {
                    println("Please enter the user name: ")
                    val userName = readln()
                    val user = library.getUsers().find { it.name == userName }

                    if (user != null) {
                        println("Please enter the name of the book to borrow: ")
                        val bookName = readln()
                        library.borrowBook(user, bookName)

                    } else {
                        println("org.example.tasks.org.example.tasks.User not found!")
                    }
                }
                4 -> {
                    println("\n--- Catalog ---")
                    library.getCatalog().forEach { println("${it.name} - ${it.status}") }
                }
                5 -> {
                    println("Exiting...")
                    exit = true
                }
                else -> println("Invalid option. Please try again.")
            }
        }
    }
}