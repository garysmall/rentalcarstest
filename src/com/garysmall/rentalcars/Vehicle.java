package com.garysmall.rentalcars;

import java.util.Comparator;

public class Vehicle implements Comparable<Vehicle>{

    private String name;
    private double price;
    private String sipp;
    private String supplier;
    private double rating;
    private double totalScore;
    private int vehicleScore;

    public String getSipp() {
        return sipp;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public int getVehicleScore() {
        return vehicleScore;
    }

    public void setVehicleScore(int vehicleScore) {
        this.vehicleScore = vehicleScore;
    }

    public void setSipp(String sipp) {
        this.sipp = sipp;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int compareTo(Vehicle o) {
        return new Double(this.price).compareTo(new Double(o.price));
    }


    public static class Comparators {

        public static Comparator<Vehicle> PRICE = new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle v1, Vehicle v2) {
                return Double.compare(v1.getPrice(), v2.getPrice());
            }
        };

        public static Comparator<Vehicle> RATING = new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle v1, Vehicle v2) {
                return Double.compare(v2.getRating(), v1.getRating());
            }
        };

        public static Comparator<Vehicle> TOTALSCORE = new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle v1, Vehicle v2) {
                return Double.compare(v2.getTotalScore(), v1.getTotalScore());
            }
        };
    }
}
