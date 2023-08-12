package com.example.geektrust.Stations;

import lombok.Getter;


public class Airport extends Station {
    String id = "AIRPORT";

    @Override
    public String getId() {
        return id;
    }
}
