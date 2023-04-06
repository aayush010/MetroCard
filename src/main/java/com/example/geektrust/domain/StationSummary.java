package com.example.geektrust.domain;

import com.example.geektrust.Enum.PassengerType;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

@Getter
@Setter
public class StationSummary {
    private LinkedHashMap<PassengerType, Integer> passengerTypeSummary = new LinkedHashMap<>();
    private Integer totalCollection;
    private Integer totalDiscount;

    public StationSummary() {
        this.passengerTypeSummary.put(PassengerType.ADULT,0);
        this.passengerTypeSummary.put(PassengerType.KID,0);
        this.passengerTypeSummary.put(PassengerType.SENIOR_CITIZEN,0);
        this.totalCollection = 0;
        this.totalDiscount = 0;
    }

    public void updatePassengerTotalCount(PassengerType passengerType, Integer value){
        this.passengerTypeSummary.replace(passengerType, value);
    }

    public int getPassengerTotalCount(PassengerType passengerType){
        return passengerTypeSummary.get(passengerType);
    }
}
