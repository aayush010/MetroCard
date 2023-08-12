package com.example.geektrust.Services;

import com.example.geektrust.PassengerType.PassengerType;
import com.example.geektrust.Repository.MetroCardRepository;
import com.example.geektrust.Stations.Airport;
import com.example.geektrust.Stations.Central;

public class MetroServiceImpl {
    BalanceService balanceService ;
    CheckInService checkInService ;
    PrintSummaryService printSummaryService ;
    MetroServiceImpl(){
        balanceService = new BalanceService();
        checkInService = new CheckInService();
        printSummaryService = new PrintSummaryService();
    }

    public void insertCard(String id, int balance) {
        balanceService.addCard(id, balance);
    }

    public void checkIn(String key, PassengerType passengerType, String stationName) {
        checkInService.checkIn(key, passengerType, stationName, balanceService);
    }

    public void printSummary() {
        printSummaryService.printSummary(checkInService.getCentral());
        printSummaryService.printSummary(checkInService.getAirport());
    }
}
