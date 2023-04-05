package com.example.geektrust.domain;

import com.example.geektrust.Enum.PassengerType;

import java.util.HashMap;

public class Summary {
    HashMap<PassengerType, Integer> passengerTypeSummary = new HashMap<>();
    Integer totalCollection;
    Integer totalDiscount;

    public Summary() {
        this.passengerTypeSummary.put(PassengerType.ADULT,0);
        this.passengerTypeSummary.put(PassengerType.KID,0);
        this.passengerTypeSummary.put(PassengerType.SENIOR_CITIZEN,0);
        this.totalCollection = 0;
        this.totalDiscount = 0;
    }

    public HashMap<PassengerType, Integer> getPassengerTypeSummary() {
        return passengerTypeSummary;
    }

    public void setPassengerTypeSummary(HashMap<PassengerType, Integer> passengerTypeSummary) {
        this.passengerTypeSummary = passengerTypeSummary;
    }

    public void updatePassengerTotalCount(PassengerType passengerType, Integer value){
        this.passengerTypeSummary.replace(passengerType, value);
    }

    public int getPassengerTotalCount(PassengerType passengerType){
        return passengerTypeSummary.get(passengerType);
    }

    public Integer getTotalCollection() {
        return totalCollection;
    }

    public void setTotalCollection(Integer totalCollection) {
        this.totalCollection = totalCollection;
    }

    public Integer getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Integer totalDiscount) {
        this.totalDiscount = totalDiscount;
    }
}
