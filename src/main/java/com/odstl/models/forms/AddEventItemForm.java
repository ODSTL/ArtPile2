package com.odstl.models.forms;

import com.odstl.models.Artwork;
import com.odstl.models.Event;

import javax.validation.constraints.NotNull;

public class AddEventItemForm {

    @NotNull
    private int eventId;

    @NotNull
    private int artworkId;

    private Iterable<Artwork> artworks;

    private Event event;

    public AddEventItemForm() {}

    public AddEventItemForm(Iterable<Artwork> artworks, Event event) {
        this.artworks = artworks;
        this.event = event;
    }

    public int getEventId() { return eventId; }

    public void setEventId(int eventId) { this.eventId = eventId; }

    public int getArtworkId() { return artworkId; }

    public void setArtworkId(int artworkId) { this.artworkId = artworkId; }

    public Event getEvent() { return event; }

    public void setEvent(Event event) { this.event = event; }

    public Iterable<Artwork> getArtworks() { return artworks; }

    public void setArtworks(Iterable<Artwork> artworks) { this.artworks = artworks; }
    }

