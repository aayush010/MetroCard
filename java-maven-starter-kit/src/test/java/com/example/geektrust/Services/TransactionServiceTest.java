package com.example.geektrust.Services;

import com.example.geektrust.Repository.MetroCardRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {

 //   @Mock
    MetroCardRepository metroCardRepository = new MetroCardRepository();

    TransactionService transactionService = new TransactionService(metroCardRepository);

    @Test
    void addCard() {
        transactionService.addCard("MC3", 1001);

        assertEquals(1001,metroCardRepository.getMetroCardById("MC3").getBalance());
    }

    @Test
    void updateTravelCount() {
        transactionService.addCard("MC3", 1001);
        transactionService.updateTravelCount("MC3");

        assertEquals(1, transactionService.getRepository().getTravelCount("MC3"));
    }

    @Test
    void discountAvailable() {
        transactionService.addCard("MC3", 1001);
        transactionService.updateTravelCount("MC3");
        transactionService.updateTravelCount("MC3");

        assertEquals(true, transactionService.discountAvailable("MC3"));
    }
}