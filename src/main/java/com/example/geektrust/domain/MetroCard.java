package com.example.geektrust.domain;

import com.example.geektrust.Enum.PassengerType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetroCard {
    private String id;
    private int balance;
    private int travelCount = 0;
    PassengerType passengerType;

}
