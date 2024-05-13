package com.services.db.controllers;

import com.services.db.models.Answers;
import com.services.db.service.AnswersService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private static List<Answers> temp = new ArrayList<>();
    @Autowired
    AnswersService answersService;


    @GetMapping("/list-of-answers")
    public ResponseEntity<List<String>> getAnswers() throws JSONException {
        System.out.println("get");

        List<String> list = new ArrayList<>();
/*        for (Answers answers : answersService.getAllAnswers()) {*/
        for (Answers answers : temp) {
            JSONObject temp = new JSONObject();
            temp.put("y", answers.getY());
            temp.put("n", answers.getN());
            list.add(temp.toString());
        }
        return new ResponseEntity<List<String>>(list, HttpStatus.OK);
    }
    @PostMapping("/add-answers")
    @ResponseStatus(value = HttpStatus.OK)
    public void addAnswers(@RequestBody String list) throws JSONException {
        System.out.println(list);
        JSONObject listOfAnswers = new JSONObject(list);
        Answers answers = new Answers();
        answers.setY(listOfAnswers.getInt("y"));
        answers.setN(listOfAnswers.getInt("n"));
        System.out.println(answers);
        temp.add(answers);
        //answersService.insertAnswers(answers);
    }
}
