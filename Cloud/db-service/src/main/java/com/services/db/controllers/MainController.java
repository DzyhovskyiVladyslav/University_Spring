package com.services.db.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {


    private static List<String> temp = new ArrayList<>();
    @PostMapping("/add")
    @ResponseStatus(value = HttpStatus.OK)
    public void add(@RequestBody String answers) {
        temp.add(answers);
        System.out.println(temp);

    }
}
