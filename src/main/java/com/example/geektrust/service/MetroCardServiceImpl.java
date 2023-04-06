package com.example.geektrust.service;

import com.example.geektrust.Enum.PassengerType;
import com.example.geektrust.domain.MetroCard;
import com.example.geektrust.domain.StationSummary;

import java.util.*;

import static com.example.geektrust.Constants.Constants.*;

public class MetroCardServiceImpl {

    private List<MetroCard> mcs= new ArrayList<MetroCard>();
    private HashMap<String, Integer> locateIndexInList = new HashMap<>();
    private int index = 0;
    StationSummary airport = new StationSummary();
    StationSummary central = new StationSummary();
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
        int costToTravel = (count%2==0) ? (int)(DISCOUNT * passengerType.getTravelCharge()) : passengerType.getTravelCharge();
        int discount = passengerType.getTravelCharge() - costToTravel ;
        if(costToTravel > ms.getBalance()){
            serviceFee = (int)(SERVICE_FEE_CHARGE_PERCENT*(costToTravel - ms.getBalance()));
            mcs.get(index).setBalance(costToTravel);
        }
        mcs.get(index).setBalance(mcs.get(index).getBalance() - costToTravel);
        costToTravel += serviceFee;
        updateSummaryAtStation(costToTravel, discount, station);
    }

    private void updateSummaryAtStation(int costToTravel, int discount, Station station) {
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
        ReadWriteServiceImpl obj = new ReadWriteServiceImpl();
        obj.writeSummary(central);
        obj.writeSummary(airport);
    }
}
