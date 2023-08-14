package com.example.geektrust.Services;
import com.example.geektrust.PassengerType.PassengerType;
import com.example.geektrust.Stations.Airport;
import com.example.geektrust.Stations.Central;
import com.example.geektrust.Stations.Station;

import java.util.Scanner;
import java.util.regex.Pattern;

import static com.example.geektrust.Constants.Constants.*;

public class ReadInputService {

    private MetroServiceImpl service;
    private Station airport ;
    private Station central ;

    //This Constructor is for unit Testing
    public ReadInputService(MetroServiceImpl metroService, Station airport, Station central) {
        service = metroService;
        this.airport = airport;
        this.central = central;
    }
    public ReadInputService(){
        service = new MetroServiceImpl();
        airport = new Airport();
        central = new Central();
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
            checkIn(key, passengerType, stationName);

        }else{
            service.printSummary(airport);
            service.printSummary(central);
        }
    }

    private void checkIn(String key, PassengerType passengerType, String stationName) {
        if(stationName.equals(AIRPORT))
            service.checkIn(key, passengerType, airport);
        if(stationName.equals(CENTRAL))
            service.checkIn(key, passengerType, central);
    }
}
