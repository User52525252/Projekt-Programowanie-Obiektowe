package com.projekt;
public class Book extends LibraryItemBase {
    private String author;

    public Book(int id, String title, String author, boolean isBorrowed) {
        super(id, title, isBorrowed);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author != null && !author.isEmpty()) {
            this.author = author;
        } else {
            throw new IllegalArgumentException("Author cannot be null or empty.");
        }
    }

    @Override
    public String toString() {
        return "Book [id=" + getId() + ", title=" + getTitle() + ", author=" + author + ", isBorrowed=" + isBorrowed() + "]";
    }
}
