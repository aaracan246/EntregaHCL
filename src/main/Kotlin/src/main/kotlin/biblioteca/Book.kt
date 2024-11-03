package org.example.biblioteca

import java.util.UUID

data class Book(val name: String, val isbn: UUID, val status: Status)
