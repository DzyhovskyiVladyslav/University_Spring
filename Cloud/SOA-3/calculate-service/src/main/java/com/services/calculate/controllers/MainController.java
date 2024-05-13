package com.services.calculate.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @PostMapping("/calculate")
    public ResponseEntity<String> calculate(@RequestBody String answers) throws JSONException {
        System.out.println(answers);
        JSONObject jsonAnswers= new JSONObject(answers);
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("y_percent", jsonAnswers.getDouble("y")*10);
        jsonResponse.put("n_percent", jsonAnswers.getDouble("n")*10);
        return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.OK);
    }
}
