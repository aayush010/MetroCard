package com.example.geektrust.service;

import com.example.geektrust.Enum.PassengerType;
import com.example.geektrust.Enum.Station;
import com.example.geektrust.domain.Airport;
import com.example.geektrust.domain.Central;
import com.example.geektrust.domain.MetroCard;

import java.sql.SQLOutput;
import java.util.*;
import java.util.regex.Pattern;

public class ServiceImpl {

    private List<MetroCard> mcs= new ArrayList<MetroCard>();
    private HashMap<String, Integer> locateIndexInList = new HashMap<>();
    private int index = 0;

    final double DISCOUNT = 0.5 ;
    final double SERVICE_FEE_CHARGE_PERCENT = 0.02;
    Airport airport = new Airport();
    Central central = new Central();
    private void addMetroCard(MetroCard mc){
        mcs.add(mc);
        locateIndexInList.put(mc.getId(), index++);
    }

    private void processData(String key, PassengerType passengerType, Station station) {
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
        int costToTravel = (count%2==0) ? (int)(DISCOUNT * passengerType.getTravelCharge()) : passengerType.getTravelCharge();
        int discount = passengerType.getTravelCharge() - costToTravel ;
        if(costToTravel > ms.getBalance()){
            serviceFee = (int)(SERVICE_FEE_CHARGE_PERCENT*(costToTravel - ms.getBalance()));
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

    private void printSummary() {

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

    public void readInput(Scanner sc) {
        String input = sc.next(Pattern.compile("[A-Z_]*"));
        if(input.equals("BALANCE")){
            MetroCard mc = new MetroCard();
            mc.setId(sc.next(Pattern.compile("MC.")));
            mc.setBalance(sc.nextInt());
            addMetroCard(mc);

        }else if(input.equals("CHECK_IN")){
            String key = sc.next(Pattern.compile("MC."));
            PassengerType passengerType = PassengerType.valueOf(sc.next(Pattern.compile("[A-Z_]*")));
            Station station = Station.valueOf(sc.next());
            processData(key, passengerType, station);
        }else{
            printSummary();
        }
    }
}
