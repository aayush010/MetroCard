package com.example.geektrust.Services;

import com.example.geektrust.PassengerType.PassengerType;
import lombok.Getter;

@Getter
public class MetroServiceImpl {
    BalanceService balanceService ;
    CheckInService checkInService ;
    MetroServiceImpl(){
        balanceService = new BalanceService();
        checkInService = new CheckInService();
    }

    public void insertCard(String id, int balance) {
        balanceService.addCard(id, balance);
    }

    public void checkIn(String key, PassengerType passengerType, String stationName) {
        checkInService.checkIn(key, passengerType, stationName, balanceService);
    }
}
