package com.example.geektrust.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetroCard {
    private String id;
    private int balance;
    private int travelCount = 0;
}
