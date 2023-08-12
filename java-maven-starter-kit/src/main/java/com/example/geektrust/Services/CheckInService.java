package com.example.geektrust.Services;

import com.example.geektrust.PassengerType.PassengerType;
import com.example.geektrust.Stations.Airport;
import com.example.geektrust.Stations.Central;
import com.example.geektrust.Stations.Station;
import lombok.Getter;

@Getter
public class CheckInService {


    private Station airport ;
    private Station central ;
    private StationService stationService;
    CheckInService(){
        airport = new Airport();
        central = new Central();
        stationService = new StationService();
    }
    public void checkIn(String key, PassengerType passengerType, String stationName, BalanceService balanceService) {
        balanceService.updateTravelCount(key);
        int costOfTrip = balanceService.costOfTrip(key, passengerType);

        if(stationName.equals(airport.getId())){
            stationService.updateStationDetail(airport, passengerType, costOfTrip, balanceService.discountAvailable(key));
        }

        if(stationName.equals(central.getId())){
            stationService.updateStationDetail(central, passengerType, costOfTrip, balanceService.discountAvailable(key));
        }
    }
}
