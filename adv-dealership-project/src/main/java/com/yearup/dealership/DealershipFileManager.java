package com.yearup.dealership;

import java.io.*;

public class DealershipFileManager {
    public Dealership getDealership() {
        Dealership dealership = new Dealership("", "", "");
        String line;
        int lineNumber = 1;

        try {
            BufferedReader br = new BufferedReader(new FileReader("inventory.csv"));
            while ((line = br.readLine()) != null) {

                //make an array, and split each part of the line into parts sectioned off by '|' PIPES
                String[] parts = line.split("\\|");
                if (lineNumber == 1) {
                    //assign each part in the array to a separate String
                    String name = parts[0];
                    //set the String made to its corresponding part
                    dealership.setName(name);
                    String address = parts[1];
                    dealership.setAddress(address);
                    String phoneNumber = parts[2];
                    dealership.setPhone(phoneNumber);

                } else {
                    int vin = Integer.parseInt(parts[0]);
                    int year = Integer.parseInt(parts[1]);
                    String make = parts[2];
                    String model = parts[3];
                    String vehicleType = parts[4];
                    String color = parts[5];
                    int odometer = Integer.parseInt(parts[6]);
                    double price = Double.parseDouble(parts[7]);

                    Vehicle vehicle = new Vehicle(vin, year, make, model,
                            vehicleType, color, odometer, price);

                    dealership.addVehicle(vehicle);
                }
                lineNumber++;

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dealership;
    }


    public void saveDealership(Dealership dealership){

        try {
            //accesses the file "transactions.csv"   append: true makes it so you can add on to the file, if nothing is there, it'll default to false and rewirte the file
            //writes in the file
            BufferedWriter buffWriter = new BufferedWriter(new FileWriter("inventory.csv"));
            //turn the transaction to a string, so I can add it to the file
            buffWriter.write(dealership + "\n");
            buffWriter.close();
            try {
                BufferedWriter bufferWriter = new BufferedWriter(new FileWriter("inventory.csv", true));
                for(Vehicle vehicle : dealership.getAllVehicles()){
                    String stringFormat = vehicle.getVin() + "|" + vehicle.getYear() + "|" + vehicle.getMake()
                            + "|" + vehicle.getModel() + "|" + vehicle.getVehicleType() + "|" + vehicle.getColor()
                            + "|" + vehicle.getOdometer() + "|" + vehicle.getPrice();
                    bufferWriter.write(stringFormat);
                }
                bufferWriter.close();
            }catch (Exception exception){
                System.out.println("Oooooh. Looks like something went wrong.");
            }


            String saveDealership = "\n" + dealership.inventory;
            //write into the file
            buffWriter.write(saveDealership);
            //close the writer so the info saves
            buffWriter.close();

        } catch (IOException e) {
            System.out.println("Oops. Failed to save data");
            e.printStackTrace();
        }
    }
}
