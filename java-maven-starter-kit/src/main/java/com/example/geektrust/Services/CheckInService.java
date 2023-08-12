package com.example.geektrust.Services;

import com.example.geektrust.PassengerType.PassengerType;
import com.example.geektrust.Stations.Airport;
import com.example.geektrust.Stations.Central;
import com.example.geektrust.Stations.Station;
import lombok.Getter;

import static com.example.geektrust.Constants.Constants.DISCOUNT;

@Getter
public class CheckInService {


    private Station airport ;
    private Station central ;
    CheckInService(){
        airport = new Airport();
        central = new Central();
    }

    public void updateStationDetail(Station station, PassengerType passengerType, int costOfTrip, boolean b) {
        int discount = b ? (int)(DISCOUNT * passengerType.getTravelCharge()) : 0;
        station.setTotalDiscount(station.getTotalDiscount() + discount);
        station.setTotalCollection(station.getTotalCollection() + costOfTrip);
        station.updatePassengerTotalCount(passengerType);
    }
    public void checkIn(String key, PassengerType passengerType, String stationName, BalanceService balanceService) {
        balanceService.updateTravelCount(key);
        int costOfTrip = balanceService.costOfTrip(key, passengerType);

        if(stationName.equals(airport.getId())){
            updateStationDetail(airport, passengerType, costOfTrip, balanceService.discountAvailable(key));
        }

        if(stationName.equals(central.getId())){
            updateStationDetail(central, passengerType, costOfTrip, balanceService.discountAvailable(key));
        }
    }
}
