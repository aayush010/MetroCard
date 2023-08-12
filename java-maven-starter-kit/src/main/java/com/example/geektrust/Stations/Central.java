package com.example.geektrust.Stations;

import com.example.geektrust.Constants.Constants;
import lombok.Getter;

public class Central extends Station{
    private String id = Constants.CENTRAL;

    @Override
    public String getId() {
        return id;
    }
}
