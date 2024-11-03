package org.example.hotel

data class Bookings(val client: Client, val room: Room, var isActive: Boolean = true)
