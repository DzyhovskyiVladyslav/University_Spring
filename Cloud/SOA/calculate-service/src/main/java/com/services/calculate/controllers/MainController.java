package com.services.calculate.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {

    @GetMapping("/global")
    public ResponseEntity<String> calculateGlobal() throws JSONException {
        RestTemplate restTemplate = new RestTemplate();

        String[] objects =  restTemplate.getForObject("http://db-service:8082/list-of-answers", String[].class);
        List<String> response = Arrays.asList(objects);
        System.out.println(response);
        int ys = 0, ns = 0, i = 0;
        for (String raw : response) {
            JSONObject temp = new JSONObject(raw);
            ys += temp.getInt("y");
            ns += temp.getInt("n");
            i++;
        }
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("ys", ys*10/i);
        jsonResponse.put("ns", ns*10/i);
        return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.OK);
    }

    @PostMapping("/calculate")
    public ResponseEntity<String> calculate(@RequestBody String answers) throws JSONException {
        System.out.println(answers);
        JSONObject jsonAnswers= new JSONObject(answers);
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("y_percent", jsonAnswers.getDouble("y")*10);
        jsonResponse.put("n_percent", jsonAnswers.getDouble("n")*10);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity("http://db-service:8082/add-answers",  new HttpEntity<>(answers), String.class);
        return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.OK);
    }
}
