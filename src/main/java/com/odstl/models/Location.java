package com.odstl.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Location {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    @OneToMany
    @JoinColumn(name = "location_id")
    private List<Artwork> artworks = new ArrayList<>();

    public List<Artwork> getArtworks() { return artworks; }

    public void setArtworks(List<Artwork> artworks) { this.artworks = artworks; }

    public Location() {}

    public Location(String name) { this.name = name; }

    public int getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

}

