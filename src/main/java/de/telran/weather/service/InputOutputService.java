package de.telran.weather.service;

import java.util.Scanner;

public class InputOutputService {
    private Scanner scanner=new Scanner(System.in) ;

    public String readValue() {
        return scanner.nextLine();
    }

    public void print(String value) {
        System.out.println(value);
    }
}
