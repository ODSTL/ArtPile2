package com.odstl.controllers;

import com.odstl.models.Artwork;
import com.odstl.models.Location;
import com.odstl.models.data.ArtworkDao;
import com.odstl.models.data.LocationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@Controller
@RequestMapping("artwork")
public class ArtworkController {

    @Autowired
    private ArtworkDao artworkDao;

    @Autowired
    private LocationDao locationDao;

    // Request path: /artwork
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("artworks", artworkDao.findAll());
        model.addAttribute("title", "ArtPile");

        return "artwork/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddArtworkForm(Model model) {
        model.addAttribute("title", "Add Artwork");
        model.addAttribute(new Artwork());
        model.addAttribute("locations", locationDao.findAll());
        return "artwork/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddArtworkForm(
            @ModelAttribute  @Valid Artwork newArtwork,
            Errors errors,
            @RequestParam int locationId,
            Model model) {

        Location cat = locationDao.findOne(locationId);
        newArtwork.setLocation(cat);

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Artwork");
            return "artwork/add";
        }
        artworkDao.save(newArtwork);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveArtworkForm(Model model) {
        model.addAttribute("artworks", artworkDao.findAll());
        model.addAttribute("title", "Remove Artwork");
        return "artwork/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveArtworkForm(@RequestParam int[] artworkIds) {

        for (int artworkId : artworkIds) {
            artworkDao.delete(artworkId);
        }

        return "redirect:";
    }

}