package com.example.geektrust.Services;

import com.example.geektrust.Models.MetroCard;
import com.example.geektrust.PassengerType.PassengerType;
import com.example.geektrust.Repository.MetroCardRepository;
import lombok.Getter;

import static com.example.geektrust.Constants.Constants.DISCOUNT;
import static com.example.geektrust.Constants.Constants.SERVICE_FEE_CHARGE_PERCENT;

@Getter
public class BalanceService {

    private MetroCardRepository repository;
    BalanceService(){
        repository = new MetroCardRepository();
    }

    public BalanceService(MetroCardRepository metroCardRepository) {
        repository = metroCardRepository;
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
        int cost = discountAvailable(key) ? (int)(DISCOUNT*passengerType.getTravelCharge()) : passengerType.getTravelCharge();
        int balance = repository.getMetroCardById(key).getBalance();
        if(balance < cost){
            int serviceFee = (int)(SERVICE_FEE_CHARGE_PERCENT*(cost - balance));
            repository.updateBalanceOfMetroCard(key, 0);
            cost += serviceFee;
        }else{
            repository.updateBalanceOfMetroCard(key, balance - cost);
        }
        return cost;
    }
}
