package com.example.geektrust.Stations;

import com.example.geektrust.Constants.Constants;
import lombok.Getter;

@Getter
public class Airport extends Station {
    private String id = Constants.AIRPORT;

    @Override
    public String getId() {
        return id;
    }
}
