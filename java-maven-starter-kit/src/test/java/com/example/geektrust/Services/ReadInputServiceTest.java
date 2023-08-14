package com.example.geektrust.Services;

import com.example.geektrust.Stations.Airport;
import com.example.geektrust.Stations.Central;
import com.example.geektrust.Stations.Station;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ReadInputServiceTest {

   // @Mock
    MetroServiceImpl metroService = new MetroServiceImpl();
    Station airport = new Airport();
    Station central = new Central();
    ReadInputService readInputService = new ReadInputService(metroService,airport,central);

    @Test
    void processInputForBalance() {
        Scanner scanner = new Scanner("BALANCE MC3 1000");
        readInputService.processInput(scanner);

        assertEquals(1000, metroService.transactionService.getRepository().getMetroCardById("MC3").getBalance());
    }

    @Test
    void processInputForCheckIn() {
        Scanner scanner1 = new Scanner("BALANCE MC3 3000");
        readInputService.processInput(scanner1);
        Scanner scanner2 = new Scanner("CHECK_IN MC3 ADULT CENTRAL");
        readInputService.processInput(scanner2);
        Scanner scanner3 = new Scanner("CHECK_IN MC3 ADULT AIRPORT");
        readInputService.processInput(scanner3);

        assertEquals(2, metroService.transactionService.getRepository().getTravelCount("MC3"));
        //assertEquals(200, metroService.checkInService.getAirport().getTotalCollection() + metroService.checkInService.getCentral().getTotalCollection());
    }
}