package com.example.geektrust.Services;

import com.example.geektrust.Models.MetroCard;
import com.example.geektrust.PassengerType.PassengerType;
import com.example.geektrust.Repository.MetroCardRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class BalanceServiceTest {

    @Mock
    MetroCardRepository metroCardRepository = new MetroCardRepository();

    BalanceService balanceService = new BalanceService(metroCardRepository);

    @Test
    void addCard() {
        balanceService.addCard("MC3", 1001);

        assertEquals(1001,metroCardRepository.getMetroCardById("MC3").getBalance());
    }

    @Test
    void updateTravelCount() {
        balanceService.addCard("MC3", 1001);
        balanceService.updateTravelCount("MC3");

        assertEquals(1,balanceService.getRepository().getTravelCount("MC3"));
    }

    @Test
    void discountAvailable() {
        balanceService.addCard("MC3", 1001);
        balanceService.updateTravelCount("MC3");
        balanceService.updateTravelCount("MC3");

        assertEquals(true,balanceService.discountAvailable("MC3"));
    }
}