package com.example.geektrust.Services;
import com.example.geektrust.PassengerType.PassengerType;
import com.example.geektrust.Stations.Station;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import static com.example.geektrust.Constants.Constants.*;

public class ReadInputService {

    private MetroServiceImpl service;

    //This Constructor is for unit Testing
    public ReadInputService(MetroServiceImpl metroService) {
        service = metroService;
    }
    public ReadInputService(){
        service = new MetroServiceImpl();
    }

    public void processInput(Scanner sc) {
        String input = sc.next(Pattern.compile(REGEX1));
        if(input.equals(BALANCE_CONSTANT)){
            String id = sc.next();
            int balance = sc.nextInt();
            service.addCard(id, balance);

        }else if(input.equals(CHECK_IN_CONSTANT)){
            String key = sc.next(Pattern.compile(REGEX2));
            PassengerType passengerType = PassengerType.valueOf(sc.next(Pattern.compile(REGEX1)));
            String stationName = sc.next();
            service.checkIn(key, passengerType, stationName);
        }else{
            printSummary(service.getCentral());
            printSummary(service.getAirport());
        }
    }

    public void printSummary(Station station) {
        System.out.println("TOTAL_COLLECTION"+"    "+ station.getId() + "     " + station.getTotalCollection() + "       " + station.getTotalDiscount());
        System.out.println("PASSENGER_TYPE_SUMMARY");
        for (Map.Entry<PassengerType,Integer> mapElement : station.getPassengerTypeSummary().entrySet()){
            if(mapElement.getValue() != 0){
                System.out.println(mapElement.getKey() + "   " + mapElement.getValue());
            }
        }
    }
}
