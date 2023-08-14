package com.example.geektrust.Services;

import com.example.geektrust.PassengerType.PassengerType;
import com.example.geektrust.Stations.Airport;
import com.example.geektrust.Stations.Central;
import com.example.geektrust.Stations.Station;
import lombok.Getter;

import java.util.Map;

import static com.example.geektrust.Constants.Constants.DISCOUNT;

@Getter
public class MetroServiceImpl {
    public TransactionService transactionService;
    private Station airport ;
    private Station central ;
    public MetroServiceImpl(){
        transactionService = new TransactionService();
        airport = new Airport();
        central = new Central();
    }

    public void addCard(String id, int balance) {
        transactionService.addCard(id, balance);
    }

    public void checkIn(String key, PassengerType passengerType, Station station) {
        transactionService.updateTravelCount(key);
        int costOfTrip = transactionService.costOfTrip(key, passengerType);
        updateStationDetail(station,passengerType, costOfTrip, transactionService.discountAvailable(key));
    }

    public void printSummary(Station station) {
        System.out.println("TOTAL_COLLECTION"+"    "+ station.getId() + "     " + station.getTotalCollection() + "       " + station.getTotalDiscount());
        System.out.println("PASSENGER_TYPE_SUMMARY");
        for (Map.Entry<PassengerType,Integer> mapElement : station.getPassengerTypeSummary().entrySet()){
            if(mapElement.getValue() != 0){
                System.out.println(mapElement.getKey() + "   " + mapElement.getValue());
            }
        }
    }

    private void updateStationDetail(Station station, PassengerType passengerType, int costOfTrip, boolean b) {
        int discount = b ? (int)(DISCOUNT * passengerType.getTravelCharge()) : 0;
        station.setTotalDiscount(station.getTotalDiscount() + discount);
        station.setTotalCollection(station.getTotalCollection() + costOfTrip);
        station.updatePassengerTotalCount(passengerType);
    }
}
