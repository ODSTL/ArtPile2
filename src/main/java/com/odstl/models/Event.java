package com.odstl.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=30)
    private String name;

    @ManyToMany
    private List<Artwork> artworks;

    public Event() {}

    public void addItem(Artwork item) { artworks.add(item); }

    public int getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

   public List<Artwork> getArtworks() { return artworks; }

}
