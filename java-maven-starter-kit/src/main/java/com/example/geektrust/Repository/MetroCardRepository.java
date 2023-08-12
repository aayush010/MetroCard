package com.example.geektrust.Repository;

import com.example.geektrust.Models.MetroCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetroCardRepository {

    private Map<String, MetroCard> metroCards = new HashMap<>();

    public void addMetroCard(String id, MetroCard metroCard){
        metroCards.put(id, metroCard);
    }

    public MetroCard getMetroCardById(String id){
        return metroCards.get(id);
    }

    public void updateBalanceOfMetroCard(String id, int amount){
        metroCards.get(id).setBalance(amount);
    }

    public int getTravelCount(String id){
        return metroCards.get(id).getTravelCount();
    }
    public void updateTravelCount(String id){
        int count = metroCards.get(id).getTravelCount();
        metroCards.get(id).setTravelCount(count+1);
    }

    public boolean isDiscountAvailable(String key) {
        return metroCards.get(key).getTravelCount() % 2 == 0;
    }
}