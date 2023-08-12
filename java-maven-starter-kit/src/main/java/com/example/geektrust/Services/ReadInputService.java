package com.example.geektrust.Services;
import com.example.geektrust.PassengerType.PassengerType;

import java.util.Scanner;
import java.util.regex.Pattern;

import static com.example.geektrust.Constants.Constants.*;

public class ReadInputService {

    MetroServiceImpl service;

    //This Constructor is for unit Testing
    ReadInputService(MetroServiceImpl metroService) {
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
            service.insertCard(id, balance);

        }else if(input.equals(CHECK_IN_CONSTANT)){
            String key = sc.next(Pattern.compile(REGEX2));
            PassengerType passengerType = PassengerType.valueOf(sc.next(Pattern.compile(REGEX1)));
            String stationName = sc.next();
            service.checkIn(key, passengerType, stationName);
        }else{
            service.printSummary();
        }
    }
}
