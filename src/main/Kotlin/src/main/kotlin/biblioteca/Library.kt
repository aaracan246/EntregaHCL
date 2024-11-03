package org.example.biblioteca

import java.util.UUID

class Library {

    private val catalog: MutableSet<Book> = mutableSetOf()
    private val users: MutableSet<User> = mutableSetOf()


    fun addUser(){
        println("Please, insert your name: ")
        val name = readln()

        println("org.example.tasks.org.example.tasks.User registered!")
        val newUser = User(name, null)

        users.add(newUser)
    }

    fun addBook(){
        println("Please, insert the name of the book: ")
        val name = readln()

        val isbn = UUID.randomUUID()

        val newBook = Book(name, isbn, status = Status.AVAILABLE)



        if (catalog.contains(newBook)){ println("That book already exists!") }
        else{
            catalog.add(newBook)
            println("Book added successfully!")
        }
    }

    fun borrowBook(user: User, bookName: String){

        val book = catalog.find { it.name == bookName &&  it.status == Status.AVAILABLE}
        if (user.booksBorrowed != null){

            if (!user.booksBorrowed.contains(book) && book?.status == Status.AVAILABLE){
                user.booksBorrowed.add(book)
            }
            else{
                println("That book is either borrowed already or the user already has it!")
            }

        }
    }

    fun getCatalog(): MutableSet<Book>{
        return catalog
    }
    fun getUsers(): MutableSet<User>{
        return users
    }
}