package com.projekt;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseHelper.createNewDatabase();
        DatabaseHelper.createNewTable();

        LibraryManager manager = new LibraryManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add a book");
            System.out.println("2. View all books");
            System.out.println("3. Update a book");
            System.out.println("4. Delete a book");
            System.out.println("5. Borrow a book");
            System.out.println("6. Return a book");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    manager.addBook(new Book(0, title, author, false));
                    System.out.println("Book added successfully.");
                    break;
                    

                case 2:
                    List<Book> books = manager.getAllBooks();
                    System.out.println("\n=== List of Books ===");
                    for (Book book : books) {
                        System.out.println(book);
                    }
                    break;
                    

                case 3:
                    System.out.print("Enter the ID of the book to update: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine(); 
                    Book bookToUpdate = manager.getBookById(idToUpdate);
                    if (bookToUpdate != null) {
                        System.out.print("Enter new title: ");
                        String newTitle = scanner.nextLine();
                        System.out.print("Enter new author: ");
                        String newAuthor = scanner.nextLine();
                        bookToUpdate.setTitle(newTitle);
                        bookToUpdate.setAuthor(newAuthor);
                        manager.updateBook(bookToUpdate);
                        System.out.println("Book updated successfully.");
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                    

                case 4:
                    System.out.print("Enter the ID of the book to delete: ");
                    int idToDelete = scanner.nextInt();
                    manager.deleteBook(idToDelete);
                    System.out.println("Book deleted successfully.");
                    break;
                    
                    

                case 5:
                    System.out.print("Enter the ID of the book to borrow: ");
                    int idToBorrow = scanner.nextInt();
                    manager.borrowBook(idToBorrow);
                    break;

                    

                case 6:
                    System.out.print("Enter the ID of the book to return: ");
                    int idToReturn = scanner.nextInt();
                    manager.returnBook(idToReturn);
                    break;
                    
                    

                case 7:
                    System.out.println("Exiting the program...");
                    scanner.close();
                    System.exit(0);
                    

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
