package com.example.geektrust.Stations;

import lombok.Getter;

public class Central extends Station{
    String id = "CENTRAL";

    @Override
    public String getId() {
        return id;
    }
}
