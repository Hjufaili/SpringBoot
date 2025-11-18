package com.codeline.demo.controllers;

import com.codeline.demo.entity.Info;
import org.springframework.web.bind.annotation.*;


@RestController
public class DemoController {

    @GetMapping
    public String nothing(){
        return "add path";
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello Guest" ;
    }

    @GetMapping("/hi")
    public String hi(@RequestParam String name){
        return "Hi " + name;
    }

    @GetMapping("/sum")
    public int sum(
            @RequestParam int a,
            @RequestParam int b
    ) {
        return a + b;
    }


//    public record Info(String name, String city, String language) {}
//    @GetMapping("/info")
//    public Info getInfo() {
//        return new Info("Al-Harith", "Muscat", "Java");
//    }

    @GetMapping("/info")
    public Info getinfo(@RequestParam String name, @RequestParam String city, @RequestParam String language){
        return new Info(name, city, language);
    }

    @GetMapping("/greet")
    public String greet(@RequestParam String name) {
        return "Hello " + name + ", welcome!";
    }


    @GetMapping("/upper")
    public String toUpper(@RequestParam String text) {
        return text.toUpperCase();
    }

    @GetMapping("/random")
    public int getRandom() {
        return (int) (Math.random() * 100) + 1;
    }




}

