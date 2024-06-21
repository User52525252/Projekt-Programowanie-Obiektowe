package com.projekt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LibraryManager {

    public void addBook(Book book) {
        String sql = "INSERT INTO books(title, author, isBorrowed) VALUES(?,?,?)";

        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setBoolean(3, book.isBorrowed());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Book> getAllBooks() {
        String sql = "SELECT id, title, author, isBorrowed FROM books";
        List<Book> books = new ArrayList<>();

        try (Connection conn = DatabaseHelper.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getBoolean("isBorrowed")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return books;
    }

    public Book getBookById(int id) {
        String sql = "SELECT id, title, author, isBorrowed FROM books WHERE id = ?";
        Book book = null;

        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getBoolean("isBorrowed")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return book;
    }

    public void updateBook(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, isBorrowed = ? WHERE id = ?";

        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setBoolean(3, book.isBorrowed());
            pstmt.setInt(4, book.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";

        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void borrowBook(int id) {
        Book book = getBookById(id);
        if (book != null && !book.isBorrowed()) {
            book.borrow();
            updateBook(book);
            System.out.println("Book borrowed successfully.");
        } else if (book != null) {
            System.out.println("Book is already borrowed.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void returnBook(int id) {
        Book book = getBookById(id);
        if (book != null && book.isBorrowed()) {
            book.returnItem();
            updateBook(book);
            System.out.println("Book returned successfully.");
        } else if (book != null) {
            System.out.println("Book was not borrowed.");
        } else {
            System.out.println("Book not found.");
        }
    }
}
