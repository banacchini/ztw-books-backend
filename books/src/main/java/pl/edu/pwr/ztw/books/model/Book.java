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

    @NotNull(message = "Author Id cannot be null")
    private int author_id;

    @Min(value = 1, message = "Pages must be greater than 0")
    private int pages;

    public Book(int id, String title, int author_id, int pages) {
        this.id = id;
        this.title = title;
        this.author_id = author_id;
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

    public int getAuthorId() {
        return author_id;
    }

    public void setAuthorId(int author) {
        this.author_id = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}