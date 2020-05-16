package com.odstl.controllers;

import com.odstl.models.Location;
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
@RequestMapping("location")
public class LocationController {
    @Autowired
    private LocationDao locationDao;
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("locations", locationDao.findAll());
        model.addAttribute("title", "Locations");

        return "location/index";
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddLocationForm(Model model) {
        model.addAttribute("title", "Add A Location");
        model.addAttribute(new Location());

        return "location/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddLocationForm(@ModelAttribute @Valid Location newLocation,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add A Location");
            return "location/add";
        }

        locationDao.save(newLocation);
        return "redirect:";
    }
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveLocationForm(Model model) {
        model.addAttribute("locations", locationDao.findAll());
        model.addAttribute("title", "Remove Location");
        return "location/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveLocationForm(@RequestParam int[] locationIds) {

        for (int locationId : locationIds) {
            locationDao.delete(locationId);
        }

        return "redirect:";
    }
}
