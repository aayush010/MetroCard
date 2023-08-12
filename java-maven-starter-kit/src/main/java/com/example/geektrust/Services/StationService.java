package com.example.geektrust.Services;

import com.example.geektrust.PassengerType.PassengerType;
import com.example.geektrust.Stations.Station;

import static com.example.geektrust.Constants.Constants.DISCOUNT;

public class StationService {
    public void updateStationDetail(Station station, PassengerType passengerType, int costOfTrip, boolean b) {
        int discount = b ? (int)(DISCOUNT * passengerType.getTravelCharge()) : 0;
        station.setTotalDiscount(station.getTotalDiscount() + discount);
        station.setTotalCollection(station.getTotalCollection() + costOfTrip);
        station.updatePassengerTotalCount(passengerType);
    }
}
