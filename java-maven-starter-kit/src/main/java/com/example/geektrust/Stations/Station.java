package com.example.geektrust.Stations;

import com.example.geektrust.PassengerType.PassengerType;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

@Getter
@Setter
public abstract class Station {
    private LinkedHashMap<PassengerType, Integer> passengerTypeSummary = new LinkedHashMap<>();
    private Integer totalCollection;
    private Integer totalDiscount;

    public Station() {
        this.passengerTypeSummary.put(PassengerType.ADULT,0);
        this.passengerTypeSummary.put(PassengerType.KID,0);
        this.passengerTypeSummary.put(PassengerType.SENIOR_CITIZEN,0);
        this.totalCollection = 0;
        this.totalDiscount = 0;
    }

    public abstract String getId();

    public void updatePassengerTotalCount(PassengerType passengerType){
        int value = passengerTypeSummary.get(passengerType) + 1;
        passengerTypeSummary.replace(passengerType, value);
    }

    public int getPassengerTotalCount(PassengerType passengerType){

        return passengerTypeSummary.get(passengerType);
    }
}
