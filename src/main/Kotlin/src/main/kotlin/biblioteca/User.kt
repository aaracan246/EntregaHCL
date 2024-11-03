package org.example.biblioteca

data class User(val name: String, val booksBorrowed: MutableSet<Book>? = null)
