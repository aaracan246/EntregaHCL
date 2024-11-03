package org.example;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

enum Status {
    BORROWED,
    AVAILABLE
}

class Book {
    private final String name;
    private final UUID isbn;
    private Status status;

    public Book(String name, UUID isbn, Status status) {
        this.name = name;
        this.isbn = isbn;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public UUID getIsbn() {
        return isbn;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return name + " - " + status;
    }
}

class User2 {
    private final String name;
    private final Set<Book> booksBorrowed;

    public User2(String name) {
        this.name = name;
        this.booksBorrowed = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public Set<Book> getBooksBorrowed() {
        return booksBorrowed;
    }
}

class Library {
    private final Set<Book> catalog;
    private final Set<User2> users;

    public Library() {
        this.catalog = new HashSet<>();
        this.users = new HashSet<>();
    }

    public void addUser(Scanner scanner) {
        System.out.print("Please, insert your name: ");
        String name = scanner.nextLine();
        User2 newUser = new User2(name);
        users.add(newUser);
        System.out.println("User " + name + " registered successfully!");
    }

    public void addBook(Scanner scanner) {
        System.out.print("Please, insert the name of the book: ");
        String name = scanner.nextLine();
        UUID isbn = UUID.randomUUID();
        Book newBook = new Book(name, isbn, Status.AVAILABLE);

        if (catalog.stream().anyMatch(book -> book.getName().equals(newBook.getName()))) {
            System.out.println("That book already exists in the catalog!");
        } else {
            catalog.add(newBook);
            System.out.println("Book '" + newBook.getName() + "' added successfully!");
        }
    }

    public void borrowBook(User2 user, String bookName) {
        Book book = catalog.stream()
                .filter(b -> b.getName().equals(bookName) && b.getStatus() == Status.AVAILABLE)
                .findFirst()
                .orElse(null);

        if (book != null) {
            user.getBooksBorrowed().add(book);
            book.setStatus(Status.BORROWED); // Update book status
            System.out.println("Book '" + book.getName() + "' borrowed successfully by " + user.getName() + ".");
        } else {
            System.out.println("That book is either borrowed already or does not exist!");
        }
    }

    public Set<Book> getCatalog() {
        return catalog;
    }

    public Set<User2> getUsers() {
        return users;
    }
}


class GestorMenuBiblio {
    private final Library library;

    public GestorMenuBiblio(Library library) {
        this.library = library;
    }

    private void menuPrint() {
        System.out.println("\n--- Library Menu ---");
        System.out.println("1. Add a new user");
        System.out.println("2. Add a new book");
        System.out.println("3. Borrow a book");
        System.out.println("4. Show catalog");
        System.out.println("5. Exit");
        System.out.print("Please select an option: ");
    }

    public void menuOptions() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            menuPrint();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    library.addUser(scanner);
                    break;
                case "2":
                    library.addBook(scanner);
                    break;
                case "3":
                    System.out.print("Please enter the user name: ");
                    String userName = scanner.nextLine();
                    User2 user = library.getUsers().stream().filter(u2 -> u2.getName().equals(userName)).findFirst().orElse(null);

                    if (user != null) {
                        System.out.print("Please enter the name of the book to borrow: ");
                        String bookName = scanner.nextLine();
                        library.borrowBook(user, bookName);
                    } else {
                        System.out.println("User not found!");
                    }
                    break;
                case "4":
                    System.out.println("\n--- Catalog ---");
                    library.getCatalog().forEach(System.out::println);
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


class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        GestorMenuBiblio menu = new GestorMenuBiblio(library);
        menu.menuOptions();
    }
}