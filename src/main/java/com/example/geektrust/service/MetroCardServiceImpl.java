package com.example.geektrust.service;

import com.example.geektrust.Enum.PassengerType;
import com.example.geektrust.domain.MetroCard;
import com.example.geektrust.domain.StationSummary;

import java.util.*;

import static com.example.geektrust.Constants.Constants.*;
import static com.example.geektrust.Constants.Constants.Station.AIRPORT;
import static com.example.geektrust.Constants.Constants.Station.CENTRAL;

public class MetroCardServiceImpl {

    private List<MetroCard> metroCardList = new ArrayList<MetroCard>();
    private HashMap<String, Integer> locateMetroCardIndexInList = new HashMap<>();
    private int index = 0;
    StationSummary airport = new StationSummary();
    StationSummary central = new StationSummary();
    public void addMetroCard(MetroCard mc){
        metroCardList.add(mc);
        locateMetroCardIndexInList.put(mc.getId(), index++);
    }

    public void processData(String key, PassengerType passengerType, Station station) {
        int indexInList = locateMetroCardIndexInList.get(key);
        metroCardList.get(indexInList).setTravelCount(metroCardList.get(indexInList).getTravelCount() + 1);
        if(Objects.isNull(metroCardList.get(indexInList).getPassengerType()))
            metroCardList.get(indexInList).setPassengerType(passengerType);
        updatePassengerCountInMap(station, passengerType);
        processExpenditure(indexInList, station);
    }

    private void updatePassengerCountInMap(Station station, PassengerType passengerType) {
        if(station.equals(AIRPORT) ){
            airport.updatePassengerTotalCount(passengerType, airport.getPassengerTotalCount(passengerType) + 1);
        }

        if(station.equals(CENTRAL)){
            central.updatePassengerTotalCount(passengerType, central.getPassengerTotalCount(passengerType) + 1);
        }
    }

    private void processExpenditure(int index, Station station) {
        MetroCard ms = metroCardList.get(index);
        int count = ms.getTravelCount();
        int serviceFee = 0;
        PassengerType passengerType = ms.getPassengerType();
        int costToTravel = (count%2==0) ? (int)(DISCOUNT * passengerType.getTravelCharge()) : passengerType.getTravelCharge();
        int discount = passengerType.getTravelCharge() - costToTravel ;
        if(costToTravel > ms.getBalance()){
            serviceFee = (int)(SERVICE_FEE_CHARGE_PERCENT*(costToTravel - ms.getBalance()));
            metroCardList.get(index).setBalance(costToTravel);
        }
        metroCardList.get(index).setBalance(metroCardList.get(index).getBalance() - costToTravel);
        costToTravel += serviceFee;
        updateSummaryAtStation(costToTravel, discount, station);
    }

    private void updateSummaryAtStation(int costToTravel, int discount, Station station) {
        if(station.equals(Station.AIRPORT)){
            airport.setTotalCollection(airport.getTotalCollection() + costToTravel);
            airport.setTotalDiscount(airport.getTotalDiscount() + discount);
        }
        if(station.equals(CENTRAL)){
            central.setTotalCollection(central.getTotalCollection() + costToTravel);
            central.setTotalDiscount(central.getTotalDiscount() + discount);
        }
    }

    public void getSummary() {
        ReadWriteServiceImpl obj = new ReadWriteServiceImpl();
        obj.writeSummary(central, CENTRAL);
        obj.writeSummary(airport, AIRPORT);
    }
}
