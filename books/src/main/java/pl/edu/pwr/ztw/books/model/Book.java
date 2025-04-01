package pl.edu.pwr.ztw.books.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Book {

    private int id;

    @NotEmpty(message = "Title cannot be empty")
    @NotBlank
    private String title;

    @NotEmpty(message = "Author cannot be empty")
    private Author author;

    @Min(value = 1, message = "Pages must be greater than 0")
    private int pages;

    public Book(int id, String title, Author author, int pages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}