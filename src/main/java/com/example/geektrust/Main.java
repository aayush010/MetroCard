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
                service.readInput(sc);
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {

        }

    }
}
