package com.example.geektrust.PassengerType;

public enum PassengerType {
    ADULT(200), SENIOR_CITIZEN(100), KID(50);

    int travelCharge;

    PassengerType(int travelCharge){
        this.travelCharge = travelCharge;
    }
    public int getTravelCharge() {
        return travelCharge;
    }
}
