package org.example.hotel

class GestorMenuHotel(private val gestorHotel: GestorHotel) {

    private fun menuPrint() {
        println("\n--- Hotel Reservation System Menu ---")
        println("1. Add a new client")
        println("2. Book a room")
        println("3. Cancel a booking")
        println("4. Show all active bookings")
        println("5. Exit")
        print("Please select an option: ")
    }


    fun menuOptions() {
        val rooms = mutableListOf(
            Room("101"), Room("102"), Room("103"), Room("104"), Room("105")
        )

        val clients = mutableListOf<Client>()

        var exit = false
        while (!exit) {
            menuPrint()
            when (readln().toIntOrNull()) {
                1 -> {
                    println("Enter client name:")
                    val clientName = readln()
                    val client = Client(clientName)
                    clients.add(client)
                    println("Client $clientName added successfully!")
                }
                2 -> {
                    if (clients.isEmpty()) {
                        println("No clients registered. Please add a client first.")
                        continue
                    }
                    println("Enter client name:")
                    val clientName = readln()
                    val client = clients.find { it.name == clientName }

                    if (client != null) {
                        println("Enter room number:")
                        val number = readln()
                        val room = rooms.find { it.number == number }

                        if (room != null) {
                            gestorHotel.bookRoom(client, room)
                        } else {
                            println("Room $number does not exist.")
                        }
                    } else {
                        println("Client $clientName not found.")
                    }
                }
                3 -> {
                    if (clients.isEmpty()) {
                        println("No clients registered. Please add a client first.")
                        continue
                    }
                    println("Enter client name:")
                    val clientName = readln()
                    val client = clients.find { it.name == clientName }

                    if (client != null) {
                        println("Enter room number to cancel:")
                        val number = readln()
                        val room = rooms.find { it.number == number }

                        if (room != null) {
                            gestorHotel.cancelBooking(client, room)
                        } else {
                            println("Room $number does not exist.")
                        }
                    } else {
                        println("Client $clientName not found.")
                    }
                }
                4 -> gestorHotel.showAllBookings()
                5 -> {
                    println("Exiting...")
                    exit = true
                }
                else -> println("Invalid option. Please try again.")
            }
        }
    }
}