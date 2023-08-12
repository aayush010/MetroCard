package com.example.geektrust.Repository;

import com.example.geektrust.Models.MetroCard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MetroCardRepositoryTest {

    MetroCardRepository metroCardRepository = new MetroCardRepository();

    @BeforeEach
    void setUp() {
        System.out.println("setting up...");
    }

    @AfterEach
    void tearDown() {
        metroCardRepository = null;
        System.out.println("Tearing Down..");
    }

    @Test
    void addMetroCard() {
        MetroCard metroCard = new MetroCard();
        metroCard.setId("MC3");
        metroCard.setBalance(1000);
        metroCardRepository.addMetroCard("MC3", metroCard);

        assertEquals(metroCard, metroCardRepository.getMetroCardById("MC3"));
    }

    @Test
    void getMetroCardById() {
        MetroCard metroCard = new MetroCard();
        metroCard.setId("MC3");
        metroCard.setBalance(1000);
        metroCardRepository.addMetroCard("MC3", metroCard);

        assertEquals(metroCard, metroCardRepository.getMetroCardById("MC3"));
    }


    @Test
    void updateBalanceOfMetroCard() {
        MetroCard metroCard = new MetroCard();
        metroCard.setId("MC3");
        metroCard.setBalance(1000);
        metroCardRepository.addMetroCard("MC3", metroCard);
        metroCardRepository.updateBalanceOfMetroCard("MC3", 500);

        assertEquals(500, metroCardRepository.getMetroCardById("MC3").getBalance());
    }

    @Test
    void getTravelCount() {

        MetroCard metroCard = new MetroCard();
        metroCard.setId("MC3");
        metroCard.setTravelCount(10);
        metroCardRepository.addMetroCard("MC3", metroCard);

        assertEquals(10, metroCardRepository.getMetroCardById("MC3").getTravelCount());
    }

    @Test
    void updateTravelCount() {

        MetroCard metroCard = new MetroCard();
        metroCard.setId("MC3");
        metroCard.setTravelCount(10);
        metroCardRepository.addMetroCard("MC3", metroCard);
        metroCardRepository.updateTravelCount( "MC3");

        assertEquals(11, metroCardRepository.getMetroCardById("MC3").getTravelCount());
    }

    @Test
    void isDiscountAvailable() {
        MetroCard metroCard = new MetroCard();
        metroCard.setId("MC3");
        metroCard.setTravelCount(10);
        metroCardRepository.addMetroCard("MC3", metroCard);

        assertEquals(metroCardRepository.isDiscountAvailable("MC3"), true);
    }

}