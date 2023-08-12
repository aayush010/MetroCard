package com.example.geektrust;

import com.example.geektrust.Services.ReadInputService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(args[0]);
        Scanner sc = new Scanner(fis);
        ReadInputService readInputService = new ReadInputService();
        while(sc.hasNext()){
            readInputService.processInput(sc);
        }
        sc.close();
    }
}
