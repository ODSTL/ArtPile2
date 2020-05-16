package com.odstl.controllers;

import com.odstl.models.data.ArtworkDao;
import com.odstl.models.data.EventDao;
import com.odstl.models.Artwork;
import com.odstl.models.forms.AddEventItemForm;
import com.odstl.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "event")
public class EventController {

    @Autowired
    EventDao eventDao;

    @Autowired
    ArtworkDao artworkDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Exhibits");
        model.addAttribute("events", eventDao.findAll());
        return "event/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add An Event");
        model.addAttribute(new Event());
        return "event/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Event event, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add An Exhibit");
            return "event/add";
        }

        eventDao.save(event);

        return "redirect:view/" + event.getId();

    }

    @RequestMapping(value = "view/{eventId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int eventId) {

        Event event = eventDao.findOne(eventId);



        model.addAttribute("title", "Add artworks to exhibit: " + event.getName());
        model.addAttribute("artworks", event.getArtworks());
        model.addAttribute("eventId" , eventId);
        return "event/view";

    }

    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem(Model model,
                          @ModelAttribute @Valid AddEventItemForm form, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "event/add-item";
        }

        Artwork theArtwork = artworkDao.findOne(form.getArtworkId());
        Event theEvent = eventDao.findOne(form.getEventId());
        theEvent.addItem(theArtwork);
        eventDao.save(theEvent);

        return "redirect:/event/view/" + theEvent.getId();
    }

    @RequestMapping(value = "add-item/{eventId}", method = RequestMethod.GET)
    public String addItems(Model model, @PathVariable int eventId ) {


        Event event = eventDao.findOne(eventId);

        AddEventItemForm form = new AddEventItemForm(
                artworkDao.findAll(),
                event);


        model.addAttribute("form",form);
        return "event/add-item";
    }
}
