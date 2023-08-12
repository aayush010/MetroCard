package com.example.geektrust.Services;

import com.example.geektrust.Models.MetroCard;
import com.example.geektrust.PassengerType.PassengerType;
import com.example.geektrust.Repository.MetroCardRepository;

import static com.example.geektrust.Constants.Constants.SERVICE_FEE_CHARGE_PERCENT;

public class BalanceService {

    MetroCardRepository repository;
    BalanceService(){
        repository = new MetroCardRepository();
    }


    public void addCard(String id, int balance) {
        MetroCard card = new MetroCard();
        card.setId(id);
        card.setBalance(balance);
        repository.addMetroCard(id, card);
    }

    public void updateTravelCount(String key) {
        repository.updateTravelCount(key);
    }

    public boolean discountAvailable(String key) {
        return repository.isDiscountAvailable(key);
    }

    public int costOfTrip(String key, PassengerType passengerType) {
        int cost = discountAvailable(key) ? passengerType.getTravelCharge()/2 : passengerType.getTravelCharge();
        int balance = repository.getMetroCardById(key).getBalance();
        if(balance < cost){
            int serviceFee = (int)(SERVICE_FEE_CHARGE_PERCENT*(cost - balance));
            repository.updateBalanceOfMetroCard(key, cost);
            cost += serviceFee;
        }else{
            repository.updateBalanceOfMetroCard(key, balance - cost);
        }
        return cost;
    }
}