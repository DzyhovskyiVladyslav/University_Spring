package com.services.main.controllers;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;


@Controller
public class MainController {

    private Bucket bucket;
    public MainController() {

        Refill refill = Refill.of(4, Duration.ofMinutes(1));
        bucket = Bucket4j.builder().addLimit(Bandwidth.classic(4, refill)).build();
    }
    private int n = 0;
    @GetMapping("/")
    public String index(Model model) {
        if (isTooManyRequests()) {
            return "tooManyRequest";
        }
        return "index";
    }

    @PostMapping("/answers")
    public String test(@RequestParam int a1, @RequestParam int a2, @RequestParam int a3, @RequestParam int a4, @RequestParam int a5,
                       @RequestParam int a6, @RequestParam int a7, @RequestParam int a8, @RequestParam int a9, @RequestParam int a10, Model model) throws JSONException {
        if (!bucket.tryConsume(1)) {
            System.out.println("token, no " + bucket.getAvailableTokens());
            return "tooManyRequest";
        }
        System.out.println("token, ok " + bucket.getAvailableTokens());
        int y = a1 + a2 + a3 + a4 + a5 + a6 + a7 + a8 + a9 + a10;
        int n = 10 - y;
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("y", String.valueOf(y));
        jsonRequest.put("n", String.valueOf(n));
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForEntity("http://calculate-service:8081/calculate",  new HttpEntity<>(jsonRequest.toString()), String.class).getBody();
        System.out.println(response);
        JSONObject jsonResponse = new JSONObject(response);
        model.addAttribute("yp", jsonResponse.get("y_percent"));
        model.addAttribute("np", jsonResponse.get("n_percent"));
        response = restTemplate.getForEntity("http://calculate-service:8081/global", String.class).getBody();
        System.out.println(response);
        jsonResponse = new JSONObject(response);
        model.addAttribute("ys", jsonResponse.get("ys"));
        model.addAttribute("ns", jsonResponse.get("ns"));
        model.addAttribute("y", y);
        model.addAttribute("n", n);
        return "answers";
    }

    public boolean isTooManyRequests() {
        if (n == 5) {
            return true;
        }
        n++;
        int delay = 60000;
        int period = 1000;
        Timer timer = new Timer();
        System.out.println("Timer Created " + n);
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                n--;
                System.out.println("Timer End " + n);
                this.cancel();
            }

        }, delay, period);
        System.out.println("Timer Start " + n);
        return false;
    }
}
