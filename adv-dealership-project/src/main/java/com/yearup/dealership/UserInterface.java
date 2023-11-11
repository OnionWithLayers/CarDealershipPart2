package com.yearup.dealership;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    Scanner scanner = new Scanner(System.in);

    public UserInterface() {

    }

    public void display() {
        init();
        displayMenu();
    }

    private void init() {
        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        dealership = dealershipFileManager.getDealership();
    }

    public void processGetByPriceRequest() {
        try {
            System.out.println("What's the minimum price you would like to filter by: ");
            double min = scanner.nextDouble();

            System.out.println("What's the maximum price you would like to filter to: ");
            double max = scanner.nextDouble();
            scanner.nextLine();

            List<Vehicle> vehicles = dealership.getVehiclesByPrice(min, max);
            displayVehicles(vehicles);
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Oops, try again");
        }
    }

    public void processGetByMakeModelRequest() {
        try {
            System.out.println("What make is the car you are looking for: ");
            String make = scanner.nextLine();

            System.out.println("What model is the car you are looking for: ");
            String model = scanner.nextLine();

            List<Vehicle> vehicleMakeModel = dealership.getVehiclesByMakeModel(make, model);
            displayVehicles(vehicleMakeModel);
        } catch (Exception e) {
            System.out.println("Failed. Work on it again");
        }
    }

    public void processGetByYearRequest() {
        try {
            System.out.println("What vehicle year would you like to start the search at: ");
            int min = scanner.nextInt();

            System.out.println("What vehicle year would you like to end the search at: ");
            int max = scanner.nextInt();
            scanner.nextLine();

            List<Vehicle> vehicleYear = dealership.getVehiclesByYear(min, max);
            displayVehicles(vehicleYear);
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("This didn't work out. Try again");
        }
    }

    public void processGetByColorRequest() {
        try {
            System.out.println("What color is the car you're looking for: ");
            String color = scanner.nextLine();

            List<Vehicle> vehicleColor = dealership.getVehiclesByColor(color);
            displayVehicles(vehicleColor);
        } catch (Exception e) {
            System.out.println("Hmm, looks like something went wrong. Maybe try again?");
        }

    }

    public void processGetByMileageRequest() {
        try {
            System.out.println("What's the minimum mileage of the car you're looking for: ");
            int min = scanner.nextInt();

            System.out.println("What's the maximum mileage of the car you're looking for: ");
            int max = scanner.nextInt();
            scanner.nextLine();

            List<Vehicle> vehicleMileage = dealership.getVehiclesByMileage(min, max);
            displayVehicles(vehicleMileage);
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Looks like you did something wrong. Start over.");

        }
    }

    public void processGetByVehicleTypeRequest() {
        try {
            System.out.println("What's the type of the vehicle you're looking for: ");
            String type = scanner.nextLine();

            List<Vehicle> vehicleType = dealership.getVehiclesByType(type);
            displayVehicles(vehicleType);
        } catch (Exception e) {
            System.out.println("hm");
        }

    }


    public void processAddVehicleRequest() {
        System.out.println("Oooooh a new vehicle, huh. Aight, just give me the info for it");
        try {
            System.out.println("So what's the vin?");
            int vin = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Mhm, now the year");
            int year = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Alright, now the juicy part. What's the make?");
            String make = scanner.nextLine();

            System.out.println("Nice. Now the model?");
            String model = scanner.nextLine();

            System.out.println("Now gimme the vehicle type: ");
            String vehicleType = scanner.nextLine();

            System.out.println("What color is it?");
            String color = scanner.nextLine();

            System.out.println("What's the odometer?");
            int odometer = scanner.nextInt();
            scanner.nextLine();

            System.out.println("And how much was it?");
            double price = scanner.nextInt();
            scanner.nextLine();

            Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
            dealership.addVehicle(vehicle);

            System.out.println("Alright, new vehicle added! I hope you think that this was responsible");


        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Bro tell me the info I'm asking you. Now we gotta start over.");
        }


    }

    public void processRemoveVehicleRequest() {
        System.out.println("*Sigh* So you gotta remove a vehicle because you can't afford it right? I don't know" +
                "why you do this in the first place. Alright, give me the full information so there's no mistake");
        try {
            System.out.println("So what's the vin?");
            int vin = scanner.nextInt();

            System.out.println("Mhm, now the year");
            int year = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Okay so what's the make?");
            String make = scanner.nextLine();

            System.out.println("Now the model?");
            String model = scanner.nextLine();

            System.out.println("Now gimme the vehicle type: ");
            String vehicleType = scanner.nextLine();

            System.out.println("What color is it?");
            String color = scanner.nextLine();

            System.out.println("What's the odometer?");
            int odometer = scanner.nextInt();

            System.out.println("And how much was it?");
            double price = scanner.nextInt();
            scanner.nextLine();

            Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
            dealership.remove(vehicle);

            System.out.println("Alright, it's out of my system. Be more responsible next time.");
        } catch (Exception exception) {
            scanner.nextLine();
            System.out.println("Bro tell me the info I'm asking you. Now we gotta start over.");
        }
    }

    public void processAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles());
    }

    private void displayVehicles(List<Vehicle> listOfVehicles) {
        for (int i = 0; i < listOfVehicles.size(); i++) {
            System.out.println(listOfVehicles.get(i));
        }
    }

    public void processContract() {
        List<Vehicle> vehicles = dealership.getAllVehicles();

        System.out.println("What's the date of the contract: ");
        String date = scanner.nextLine();

        System.out.println("Customer name: ");
        String customerName = scanner.nextLine();

        System.out.println("Customer email: ");
        String customerEmail = scanner.nextLine();

        System.out.println("Contracted vehicle vin or ID: ");
        int vin = scanner.nextInt();
        scanner.nextLine();
        Vehicle vroom = null;
        for (Vehicle v : vehicles) {
            if (vin == v.getVin()) {
                vroom = v;
            }
        }

        System.out.println("Alright Rumplestiltkin, what kind of contract was it");
        System.out.println("1) LEASE");
        System.out.println("2) SALE");
        String type = scanner.nextLine();


        Contract contract = null;
        if (type.equalsIgnoreCase("1")) {
            contract = new LeaseContract(date, customerName, customerEmail, vroom);
        } else if (type.equalsIgnoreCase("2")) {
            System.out.println("Would you like to finance the vehicle");
            boolean financeOption = scanner.nextBoolean();
            contract = new SalesContract(date, customerName, customerEmail, vroom, financeOption);
        }

        ContractFileManager contractFileManager = new ContractFileManager();
        contractFileManager.saveContract(contract);
        dealership.remove(vroom);

    }


    public void displayMenu() {

        boolean running = true;
        while (running) {
            System.out.println("\nDa Dealership < ( + . + < )");
            System.out.println("What do you wanna do, bud?");
            System.out.println("1) Find vehicles within a price range");
            System.out.println("2) Find vehicles by make");
            System.out.println("3) Find vehicles by year range");
            System.out.println("4) Find vehicles by color");
            System.out.println("5) Find vehicles by mileage range");
            System.out.println("6) Find vehicles by type (car, truck, SUV, van, etc)");
            System.out.println("7) List ALL vehicles");
            System.out.println("8) Add a vehicle");
            System.out.println("9) Remove a vehicle");
            System.out.println("10) Add a contract");
            System.out.println("99) Leave me... ( T ^ T )");

            //this is a scanner that takes the input
            String input = scanner.nextLine().trim();

            //this switch case statement takes the scanner input and if
            //it matches any of these presented string, it'll activate the method
            switch (input) {
                case "1":
                    processGetByPriceRequest();
                    break;
                case "2":
                    processGetByMakeModelRequest();
                    break;
                case "3":
                    processGetByYearRequest();
                    break;
                case "4":
                    processGetByColorRequest();
                    break;
                case "5":
                    processGetByMileageRequest();
                    break;
                case "6":
                    processGetByVehicleTypeRequest();
                    break;
                case "7":
                    processAllVehiclesRequest();
                    break;
                case "8":
                    processAddVehicleRequest();
                    break;
                case "9":
                    processRemoveVehicleRequest();
                    break;
                case "10":
                    processContract();
                    break;
                case "99":
                    System.out.println("Fine, be that way T^T");
                    running = false;
                default:
                    break;
            }
        }
        scanner.close();
    }

    @Override
    public String toString() {
        return "UserInterface{" +
                "dealership=" + dealership +
                '}';
    }
}
