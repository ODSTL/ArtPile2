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
    @Size(min=3, max=50)
    private String name;

    @NotNull
    @Size(min=1, message = "Field must not be empty")
    private String description;

    @NotNull
    @Size(min=1, message = "Field must not be empty")
    private String dimension;

    @ManyToOne
    private Location location;

    @ManyToMany(mappedBy = "artworks")
    private List<Event> events;

    public Artwork(String name, String description, String size) {
        this.name = name;
        this.description = description;
        this.dimension = dimension;
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

    public Location getType() { return location; }

    public void setType(Location type) { this.location = location; }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
