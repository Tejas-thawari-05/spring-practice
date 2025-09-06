package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/marks")
public class MarksController {

    @GetMapping("/{id}")
    public String getMarks(@PathVariable int id) {
        return "95";
    }
}

