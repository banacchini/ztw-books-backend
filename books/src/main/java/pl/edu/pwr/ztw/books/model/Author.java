package pl.edu.pwr.ztw.books.model;


import jakarta.validation.constraints.NotEmpty;

public class Author {
    private int id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    public Author(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
