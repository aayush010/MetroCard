package com.example.geektrust;

import com.example.geektrust.domain.MetroCard;
import com.example.geektrust.Enum.PassengerType;
import com.example.geektrust.Enum.Station;
import com.example.geektrust.service.ServiceImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        try {
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis);// file to be scanned
            ServiceImpl service = new ServiceImpl();
            int i = 0;
            while (sc.hasNext()) {
                if(sc.next(Pattern.compile("BALANCE")).equals("BALANCE")){
                    MetroCard mc = new MetroCard();
                    mc.setId(sc.next(Pattern.compile("MC.")));
                    mc.setBalance(sc.nextInt());
                    service.addMetroCard(mc);

                }else if(sc.next(Pattern.compile("CHECK_IN")).equals("CHECK_IN")){
                    String key = sc.next(Pattern.compile("MC."));
                    String pattern = [A-Z];
                    PassengerType passengerType = PassengerType.valueOf(sc.next(Pattern.compile("")));
                    Station station = Station.valueOf(sc.next());
                    service.processData(key, passengerType, station);
                }else{
                    service.printSummary();
                }
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {

        }

    }
}
