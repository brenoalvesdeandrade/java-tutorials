package org.nklkarthi.web.controller;

import java.util.Date;

import org.nklkarthi.web.dto.Item;
import org.nklkarthi.web.dto.ItemManager;
import org.nklkarthi.web.dto.Views;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class ItemController {

    @JsonView(Views.Public.class)
    @RequestMapping("/items/{id}")
    public Item getItemPublic(@PathVariable final int id) {
        return ItemManager.getById(id);
    }

    @JsonView(Views.Internal.class)
    @RequestMapping("/items/internal/{id}")
    public Item getItemInternal(@PathVariable final int id) {
        return ItemManager.getById(id);
    }

    @RequestMapping("/date")
    public Date getCurrentDate() throws Exception {
        return new Date();
    }

    @RequestMapping("/delay/{seconds}")
    public void getCurrentTime(@PathVariable final int seconds) throws Exception {

        Thread.sleep(seconds * 1000);
    }
}