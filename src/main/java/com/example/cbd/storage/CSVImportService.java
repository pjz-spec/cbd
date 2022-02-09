package com.example.cbd.storage;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Service;

import java.io.FileReader;

@Service
public class CSVImportService {

    public void readDataLineByLine(String file) {

        try {

            // Create an object of filereader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(file);

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readDataLine(String file, Long id) {
        int line = id.intValue();
        String read = "";

        try {

            // Create an object of filereader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(file);

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(line).build();
            String[] nextRecord;

            nextRecord = csvReader.readNext();
            for (String cell : nextRecord) {
                read = read + cell + " ";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return read;
    }

}
