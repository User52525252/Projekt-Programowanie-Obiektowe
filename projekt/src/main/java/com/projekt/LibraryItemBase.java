package com.projekt;
public abstract class LibraryItemBase implements LibraryItem {
    private int id;
    private String title;
    private boolean isBorrowed;

    public LibraryItemBase(int id, String title, boolean isBorrowed) {
        this.id = id;
        this.title = title;
        this.isBorrowed = isBorrowed;
    }

    @Override
    public void borrow() {
        if (!isBorrowed) {
            isBorrowed = true;
        } else {
            throw new IllegalStateException("Item is already borrowed.");
        }
    }

    @Override
    public void returnItem() {
        if (isBorrowed) {
            isBorrowed = false;
        } else {
            throw new IllegalStateException("Item is not borrowed.");
        }
    }

    @Override
    public boolean isBorrowed() {
        return isBorrowed;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null && !title.isEmpty()) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
    }

  
}
