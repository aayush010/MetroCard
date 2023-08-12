package com.example.geektrust.Services;

import com.example.geektrust.Models.MetroCard;
import com.example.geektrust.PassengerType.PassengerType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class ReadInputServiceTest {

    @Mock
    MetroServiceImpl metroService = new MetroServiceImpl();
    ReadInputService readInputService = new ReadInputService(metroService);

    @Test
    void processInputForBalance() {
        Scanner scanner = new Scanner("BALANCE MC3 1000");
        readInputService.processInput(scanner);

        assertEquals(1000, metroService.balanceService.getRepository().getMetroCardById("MC3").getBalance());
    }

    @Test
    void processInputForCheckIn() {
        Scanner scanner1 = new Scanner("BALANCE MC3 3000");
        readInputService.processInput(scanner1);
        Scanner scanner2 = new Scanner("CHECK_IN MC3 ADULT CENTRAL");
        readInputService.processInput(scanner2);
        Scanner scanner3 = new Scanner("CHECK_IN MC3 ADULT AIRPORT");
        readInputService.processInput(scanner3);

        assertEquals(2, metroService.balanceService.getRepository().getTravelCount("MC3"));
        //assertEquals(200, metroService.checkInService.getAirport().getTotalCollection() + metroService.checkInService.getCentral().getTotalCollection());
    }
}