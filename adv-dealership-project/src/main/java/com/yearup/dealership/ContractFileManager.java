package com.yearup.dealership;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {
    public void saveContract(Contract contract) {

        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("contracts.csv", true));
            bufferedWriter.write(contract + "\n");

            // checks if the object matches whatever you put after 'instanceof'
            if (contract instanceof SalesContract) {
                stringBuilder.append("SALE|" + contract);
            } else if (contract instanceof LeaseContract) {
                stringBuilder.append("LEASE|" + contract);
            }
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
