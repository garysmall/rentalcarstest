package com.garysmall.rentalcars;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ProcessVehicle {

    private static final String JSON_PATH = "vehicles.json";

    public static void main(String[] args) throws IOException {

        BufferedReader reader;
        List<Vehicle> vehicleList = new ArrayList<Vehicle>();

        try {
            reader = new BufferedReader(new FileReader(JSON_PATH));
            Gson gson = new GsonBuilder().create();
            vehicleList = gson.fromJson(reader, new TypeToken<List<Vehicle>>(){}.getType());;

        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        }

        // PART 1
        System.out.println("--- PART 1 ---");

        Collections.sort(vehicleList, Vehicle.Comparators.PRICE);

        for (Vehicle vehicle : vehicleList) {
            System.out.println(vehicle.getName() + " - " + vehicle.getPrice());
        }

        // PART 2
        System.out.println("--- PART 2 ---");
       for (Vehicle vehicle : vehicleList) {
           String sipp = vehicle.getSipp();
           System.out.println(vehicle.getName() + " - " + vehicle.getSipp() + " - " + (lookupSipp(sipp))[0]
                + " - " + (lookupSipp(sipp))[1] + " - " + (lookupSipp(sipp))[2] + " - "
                   + splitFuelAc((lookupSipp(sipp))[3])[0] + " - " + splitFuelAc((lookupSipp(sipp))[3])[1]);
        }

        System.out.println("--- PART 3 ---");

        Collections.sort(vehicleList, Vehicle.Comparators.RATING);

        for (Vehicle vehicle : vehicleList) {
            System.out.println(vehicle.getName() + " - " + lookupSipp(vehicle.getSipp())[0] + " - " + vehicle.getSupplier()
                    + " - " + vehicle.getRating());
        }

        System.out.println("--- PART 4 ---");
        for (Vehicle vehicle : vehicleList) {
            int vehicleScore = scoreVehicle(lookupSipp(vehicle.getSipp()));
            double totalScore = (double) vehicleScore + vehicle.getRating();
            vehicle.setVehicleScore(vehicleScore);
            vehicle.setTotalScore(totalScore);
        }

        Collections.sort(vehicleList, Vehicle.Comparators.TOTALSCORE);

        for (Vehicle vehicle : vehicleList) {
            System.out.println(vehicle.getName() + " - " + vehicle.getVehicleScore() + " - "
                    + vehicle.getRating() + " - " + vehicle.getTotalScore());
        }

    }

    private static String[] lookupSipp(String sipp) {

        Map<Character, String> carType = new HashMap<>();
        carType.put('M', "Mini");
        carType.put('E', "Economy");
        carType.put('C', "Compact");
        carType.put('I', "Intermediate");
        carType.put('S', "Standard");
        carType.put('F', "Full size");
        carType.put('P', "Premium");
        carType.put('L', "Luxury");
        carType.put('X', "Special");

        Map<Character, String> door = new HashMap<>();
        door.put('B', "2 doors");
        door.put('C', "4 doors");
        door.put('D', "5 doors");
        door.put('W', "Estate");
        door.put('T', "Convertible");
        door.put('F', "SUV");
        door.put('P', "Pick up");
        door.put('V', "Passenger Van");

        Map<Character, String> transmission = new HashMap<>();
        transmission.put('M', "Manual");
        transmission.put('A', "Automatic");

        Map<Character, String> fuelac = new HashMap<>();
        fuelac.put('N', "Petrol/no AC");
        fuelac.put('R', "Petrol/AC");

        char[] ch = sipp.toCharArray();

        return new String[] { carType.get(ch[0]),  door.get(ch[1]), transmission.get(ch[2]), fuelac.get(ch[3]) };

    }


    private static int scoreVehicle(String[] sipp){
        String ac = (splitFuelAc(sipp[3]))[1];
        String transmission = sipp[2];

        int score = 0;
        if (ac.equals("AC")){
            score += 2;
        }
        if (transmission.equals("Manual")){
            score += 1;
        }
        if (transmission.equals("Automatic")){
            score += 5;
        }

        return score;
    }


    private static String[] splitFuelAc (String fuelAc){
        return fuelAc.split("/");
    }
}
