package com.example.geektrust.service;

import com.example.geektrust.Enum.PassengerType;
import com.example.geektrust.Enum.Station;
import com.example.geektrust.domain.Airport;
import com.example.geektrust.domain.Central;
import com.example.geektrust.domain.MetroCard;

import java.sql.SQLOutput;
import java.util.*;

public class ServiceImpl {

    List<MetroCard> mcs= new ArrayList<MetroCard>();
    HashMap<String, Integer> locateIndexInList = new HashMap<>();
    int index = 0;
    Airport airport = new Airport();
    Central central = new Central();
    public void addMetroCard(MetroCard mc){
        mcs.add(mc);
        locateIndexInList.put(mc.getId(), index++);
    }

    public void processData(String key, PassengerType passengerType, Station station) {
        int indexInList = locateIndexInList.get(key);
        mcs.get(indexInList).setTravelCount(mcs.get(indexInList).getTravelCount() + 1);
        if(Objects.isNull(mcs.get(indexInList).getPassengerType()))
            mcs.get(indexInList).setPassengerType(passengerType);
        if(station.equals(Station.AIRPORT) ){
            airport.updatePassengerTotalCount(passengerType, airport.getPassengerTotalCount(passengerType) + 1);
        }

        if(station.equals(Station.CENTRAL)){
            central.updatePassengerTotalCount(passengerType, central.getPassengerTotalCount(passengerType) + 1);
        }
        processExpenditure(indexInList, station);
    }

    private void processExpenditure(int index, Station station) {
        MetroCard ms = mcs.get(index);
        int count = ms.getTravelCount();
        int serviceFee = 0;
        PassengerType passengerType = ms.getPassengerType();
        int costToTravel = (count%2==0) ? (int)(0.5 * passengerType.getTravelCharge()) : passengerType.getTravelCharge();
        int discount = passengerType.getTravelCharge() - costToTravel ;
        if(costToTravel > ms.getBalance()){
            serviceFee = (int)(0.02*(costToTravel - ms.getBalance()));
            mcs.get(index).setBalance(costToTravel);
        }
        mcs.get(index).setBalance(mcs.get(index).getBalance() - costToTravel);
        costToTravel += serviceFee;
        //System.out.println("Cost to travel :" +costToTravel);
        if(station.equals(Station.AIRPORT)){
            airport.setTotalCollection(airport.getTotalCollection() + costToTravel);
            airport.setTotalDiscount(airport.getTotalDiscount() + discount);
        }
        if(station.equals(Station.CENTRAL)){
            central.setTotalCollection(central.getTotalCollection() + costToTravel);
            central.setTotalDiscount(central.getTotalDiscount() + discount);
        }
    }

    public void printSummary() {

        System.out.println("TOTAL_COLLECTION"+"    "+Station.CENTRAL + "     " + central.getTotalCollection() + "       " + central.getTotalDiscount());
        System.out.println("PASSENGER_TYPE_SUMMARY");
        for (Map.Entry<PassengerType,Integer> mapElement : central.getPassengerTypeSummary().entrySet()){
            if(mapElement.getValue() != 0){
                System.out.println(mapElement.getKey() + "   " + mapElement.getValue());
            }
        }

        System.out.println("TOTAL_COLLECTION"+"    "+Station.AIRPORT + "     " + airport.getTotalCollection() + "       " + airport.getTotalDiscount());
        System.out.println("PASSENGER_TYPE_SUMMARY");
        for (Map.Entry<PassengerType,Integer> mapElement : airport.getPassengerTypeSummary().entrySet()){
            if(mapElement.getValue() != 0){
                System.out.println(mapElement.getKey() + "   " + mapElement.getValue());
            }
        }
    }
}
