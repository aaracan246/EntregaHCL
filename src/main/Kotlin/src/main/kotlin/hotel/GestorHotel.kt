package org.example.hotel

class GestorHotel {

    private val bookings: MutableList<Bookings> = mutableListOf()

    fun bookRoom(client: Client, room: Room){
        val existingBooking = bookings.find { it.room == room && it.isActive }

        if (existingBooking != null) {
            println("Room ${room.number} is already booked.")
        } else {

            val reservation = Bookings(client, room)
            bookings.add(reservation)
            room.status = Booked.Yes
            println("Room ${room.number} successfully booked by ${client.name}.")
        }
    }

    fun cancelBooking(client: Client, room: Room){
        val reservation = bookings.find { it.client == client && it.room == room && it.isActive }

        if (reservation != null) {
            reservation.isActive = false
            room.status = Booked.Nope
            println("Reservation for room ${room.number} has been cancelled for ${client.name}.")
        } else {
            println("No active reservation found for room ${room.number} for ${client.name}.")
        }
    }

    fun showAllBookings(){
        val activeBooking = bookings.filter{ it.isActive }

        if (activeBooking.isEmpty()){ println("No active bookings found.") }
        else{
            println("\n-- Active bookings --")
            activeBooking.forEach{ println("Client: ${it.client.name}, Room: ${it.room}") }
        }
    }
}