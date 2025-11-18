package com.codeline.demo.entity;

import lombok.Getter;

@Getter
public class Info {
    private String name;
    private String city;
    private String language;

    public Info(String name, String city, String language) {
        this.name = name;
        this.city = city;
        this.language = language;
    }

}
