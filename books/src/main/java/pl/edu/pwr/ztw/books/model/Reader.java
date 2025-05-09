package pl.edu.pwr.ztw.books.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Reader {

    private int id;

    @NotEmpty(message = "Name cannot be empty")
    @NotBlank
    private String name;

    public Reader(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //Getters and setters
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
