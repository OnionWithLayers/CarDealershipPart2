package com.yearup.dealership;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {
    public void saveContract(Contract contract) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("contracts.csv", true));
            // checks if the object matches whatever you put after 'instanceof'
            if (contract instanceof SalesContract) {
                String finance = ((SalesContract) contract).isfinanceOption() ? "yes" : "no";
                bufferedWriter.write("LEASE|" + contract.getDateOfContract() + "|" + contract.getCustomerName() + "|" +
                        contract.getCustomerEmail() + "|" + contract.getVehicleSold().getVin() + "|" +
                        contract.getVehicleSold().getYear() + "|" + contract.getVehicleSold().getMake() + "|" +
                        contract.getVehicleSold().getModel() + "|" + contract.getVehicleSold().getVehicleType() + "|" +
                        contract.getVehicleSold().getColor() + "|" + contract.getVehicleSold().getOdometer() + "|" +
                        contract.getVehicleSold().getPrice() + "|" +
                        ((SalesContract) contract).getSalesTaxAmount() + "|" + ((SalesContract) contract).getRecordingFee()
                        + "|" + ((SalesContract) contract).getTotalPrice() + "|" + finance + "|" + contract.getMonthlyPayment());
                bufferedWriter.newLine();

            } else if (contract instanceof LeaseContract) {
                bufferedWriter.write("LEASE|" + contract.getDateOfContract() + "|" + contract.getCustomerName() + "|" +
                        contract.getCustomerEmail() + "|" + contract.getVehicleSold().getVin() + "|" +
                        contract.getVehicleSold().getYear() + "|" + contract.getVehicleSold().getMake() + "|" +
                        contract.getVehicleSold().getModel() + "|" + contract.getVehicleSold().getVehicleType() + "|" +
                        contract.getVehicleSold().getColor() + "|" + contract.getVehicleSold().getOdometer() + "|" +
                        contract.getVehicleSold().getPrice() + "|" +
                        ((LeaseContract) contract).getExpectedEndingValue() + "|" + ((LeaseContract) contract).getLeaseFee()
                        + "|" + ((LeaseContract) contract).getTotalPrice() + "|" + ((LeaseContract) contract).getMonthlyPayment());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
