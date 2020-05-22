package com.odstl.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
public class Artwork {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=30)
    private String name;

    @NotNull
    @Size(min=1, max=4)
    private String year;

    @NotNull
    @Size(min=1, message = "Field must not be empty")
    private String description;

    @NotNull
    @Size(min=1, message = "Field must not be empty")
    private String dimension;

    @NotNull
    @Size(min=1, max=10, message = "Field must not be empty")
    private String cost;

    @NotNull
    @Size(min=1, max=10, message = "Field must not be empty")
    private String price;

    @ManyToOne
    private Location location;

    @ManyToMany(mappedBy = "artworks")
    private List<Event> events;

    public Artwork(String name, String description, String dimension, String cost, String price) {
        this.name = name;
        this.year = year;
        this.description = description;
        this.dimension = dimension;
        this.cost = cost;
        this.price = price;
    }

    public Artwork() { }

    private List<Event> getEvents() { return events; }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Location getType() { return location; }

    public void setType(Location type) { this.location = location; }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
