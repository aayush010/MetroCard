package com.example.geektrust;
import com.example.geektrust.Constants.Constants;
import com.example.geektrust.Enum.PassengerType;
import com.example.geektrust.domain.MetroCard;
import com.example.geektrust.service.MetroCardServiceImpl;
import com.example.geektrust.service.ReadWriteServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.mockito.Mockito.*;

public class ReadWriteServiceTest {

    @Mock
    private MetroCardServiceImpl metroCardService ;
    private ReadWriteServiceImpl readWriteService = new ReadWriteServiceImpl();
    @Test
    void readInputTest_BALANCE(){
        String inputString = "BALANCE MC1 100";
        System.setIn(new ByteArrayInputStream(inputString.getBytes()));
        Scanner sc = new Scanner(System.in);
        MetroCard mc = new MetroCard();
        doNothing().when(metroCardService).addMetroCard(mc);
        readWriteService.readInput(sc);
        verify(metroCardService, times(1)).addMetroCard(mc);
    }

    @Test
    void readInputTest_CheckIN(){

        String inputString = "CHECK_IN MC1 SENIOR_CITIZEN AIRPORT";
        System.setIn(new ByteArrayInputStream(inputString.getBytes()));
        Scanner sc = new Scanner(System.in);
        //metroCardService = mock(MetroCardServiceImpl.class);
        //doNothing().when(metroCardService).processData("MC1", PassengerType.SENIOR_CITIZEN, Constants.Station.AIRPORT);
        readWriteService.readInput(sc);
        verify(metroCardService, times(1)).processData("MC1", PassengerType.SENIOR_CITIZEN, Constants.Station.AIRPORT);
    }



}
