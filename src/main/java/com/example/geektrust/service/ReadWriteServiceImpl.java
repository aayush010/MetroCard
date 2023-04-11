package com.example.geektrust.service;

import com.example.geektrust.Enum.PassengerType;
import com.example.geektrust.domain.MetroCard;
import com.example.geektrust.domain.StationSummary;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import static com.example.geektrust.Constants.Constants.*;
import static com.example.geektrust.Constants.Constants.REGEX1;

public class ReadWriteServiceImpl {

    MetroCardServiceImpl service = new MetroCardServiceImpl();
    public void readInput(Scanner sc) {
        String input = sc.next(Pattern.compile(REGEX1));
        if(input.equals(BALANCE_CONSTANT)){
            MetroCard mc = new MetroCard();
            mc.setId(sc.next(Pattern.compile(REGEX2)));
            mc.setBalance(sc.nextInt());
            service.addMetroCard(mc);

        }else if(input.equals(CHECK_IN_CONSTANT)){
            String key = sc.next(Pattern.compile(REGEX2));
            PassengerType passengerType = PassengerType.valueOf(sc.next(Pattern.compile(REGEX1)));
            Station station = Station.valueOf(sc.next());
            service.processData(key, passengerType, station);
        }else{
            service.getSummary();
        }
    }

    public void writeSummary(StationSummary station, Station stationName) {

        System.out.println("TOTAL_COLLECTION"+"    "+ stationName + "     " + station.getTotalCollection() + "       " + station.getTotalDiscount());
        System.out.println("PASSENGER_TYPE_SUMMARY");
        for (Map.Entry<PassengerType,Integer> mapElement : station.getPassengerTypeSummary().entrySet()){
            if(mapElement.getValue() != 0){
                System.out.println(mapElement.getKey() + "   " + mapElement.getValue());
            }
        }
    }
}
