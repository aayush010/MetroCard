package com.example.geektrust.Services;

import com.example.geektrust.PassengerType.PassengerType;
import com.example.geektrust.Stations.Station;

import java.util.Map;

public class PrintSummaryService {
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
