package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

enum Booked {
    Yes,
    Nope
}

class Client {
    private final String name;

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Room {
    private final String number;
    private Booked status;

    public Room(String number) {
        this.number = number;
        this.status = Booked.Nope;
    }

    public String getNumber() {
        return number;
    }

    public Booked getStatus() {
        return status;
    }

    public void setStatus(Booked status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return number;
    }
}

class Bookings {
    private final Client client;
    private final Room room;
    private boolean isActive;

    public Bookings(Client client, Room room) {
        this.client = client;
        this.room = room;
        this.isActive = true;
    }

    public Client getClient() {
        return client;
    }

    public Room getRoom() {
        return room;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

class GestorHotel {
    private final List<Bookings> bookings;

    public GestorHotel() {
        this.bookings = new ArrayList<>();
    }

    public void bookRoom(Client client, Room room) {
        Optional<Bookings> existingBooking = bookings.stream()
                .filter(b -> b.getRoom().equals(room) && b.isActive())
                .findFirst();

        if (existingBooking.isPresent()) {
            System.out.println("Room " + room.getNumber() + " is already booked.");
        } else {
            Bookings reservation = new Bookings(client, room);
            bookings.add(reservation);
            room.setStatus(Booked.Yes);
            System.out.println("Room " + room.getNumber() + " successfully booked by " + client.getName() + ".");
        }
    }

    public void cancelBooking(Client client, Room room) {
        Optional<Bookings> reservation = bookings.stream()
                .filter(b -> b.getClient().equals(client) && b.getRoom().equals(room) && b.isActive())
                .findFirst();

        if (reservation.isPresent()) {
            reservation.get().setActive(false);
            room.setStatus(Booked.Nope);
            System.out.println("Reservation for room " + room.getNumber() + " has been cancelled for " + client.getName() + ".");
        } else {
            System.out.println("No active reservation found for room " + room.getNumber() + " for " + client.getName() + ".");
        }
    }

    public void showAllBookings() {
        List<Bookings> activeBookings = new ArrayList<>();
        for (Bookings booking : bookings) {
            if (booking.isActive()) {
                activeBookings.add(booking);
            }
        }

        if (activeBookings.isEmpty()) {
            System.out.println("No active bookings found.");
        } else {
            System.out.println("\n-- Active bookings --");
            for (Bookings booking : activeBookings) {
                System.out.println("Client: " + booking.getClient().getName() + ", Room: " + booking.getRoom());
            }
        }
    }
}

class GestorMenuHotel {
    private final GestorHotel gestorHotel;
    private final Scanner scanner;

    public GestorMenuHotel(GestorHotel gestorHotel) {
        this.gestorHotel = gestorHotel;
        this.scanner = new Scanner(System.in);
    }

    private void menuPrint() {
        System.out.println("\n--- Hotel Reservation System Menu ---");
        System.out.println("1. Add a new client");
        System.out.println("2. Book a room");
        System.out.println("3. Cancel a booking");
        System.out.println("4. Show all active bookings");
        System.out.println("5. Exit");
        System.out.print("Please select an option: ");
    }

    public void menuOptions() {
        List<Room> rooms = List.of(
                new Room("101"), new Room("102"), new Room("103"), new Room("104"), new Room("105")
        );

        List<Client> clients = new ArrayList<>();
        boolean exit = false;

        while (!exit) {
            menuPrint();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Enter client name:");
                    String clientName = scanner.nextLine();
                    Client client = new Client(clientName);
                    clients.add(client);
                    System.out.println("Client " + clientName + " added successfully!");
                    break;
                case "2":
                    if (clients.isEmpty()) {
                        System.out.println("No clients registered. Please add a client first.");
                        continue;
                    }
                    System.out.println("Enter client name:");
                    clientName = scanner.nextLine();
                    client = clients.stream().filter(c -> c.getName().equals(clientName)).findFirst().orElse(null);

                    if (client != null) {
                        System.out.println("Enter room number:");
                        String roomNumber = scanner.nextLine();
                        Room room = rooms.stream().filter(r -> r.getNumber().equals(roomNumber)).findFirst().orElse(null);

                        if (room != null) {
                            gestorHotel.bookRoom(client, room);
                        } else {
                            System.out.println("Room " + roomNumber + " does not exist.");
                        }
                    } else {
                        System.out.println("Client " + clientName + " not found.");
                    }
                    break;
                case "3":
                    if (clients.isEmpty()) {
                        System.out.println("No clients registered. Please add a client first.");
                        continue;
                    }
                    System.out.println("Enter client name:");
                    clientName = scanner.nextLine();
                    client = clients.stream().filter(c -> c.getName().equals(clientName)).findFirst().orElse(null);

                    if (client != null) {
                        System.out.println("Enter room number to cancel:");
                        String roomNumber = scanner.nextLine();
                        Room room = rooms.stream().filter(r -> r.getNumber().equals(roomNumber)).findFirst().orElse(null);

                        if (room != null) {
                            gestorHotel.cancelBooking(client, room);
                        } else {
                            System.out.println("Room " + roomNumber + " does not exist.");
                        }
                    } else {
                        System.out.println("Client " + clientName + " not found.");
                    }
                    break;
                case "4":
                    gestorHotel.showAllBookings();
                    break;
                case "5":
                    System.out.println("Exiting...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}

class HotelManagementSystem {
    public static void main(String[] args) {
        GestorHotel gestorHotel = new GestorHotel();
        GestorMenuHotel menu = new GestorMenuHotel(gestorHotel);
        menu.menuOptions();
    }
}