package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Khnyznytskij Evgen
 * @version 1.0  24 of June 2020
 *
 * Classname - Main
 * Description - The main class
 *
 * Module 4. Files management.
 * 1. Parse the file logs.txt (see the attachment).  Extract to a separate file all the logs from October 2019
 * 2. Calculate the total number of logs (lines)
 * 3. Calculate the total  number of  ERROR logs. Use previous skills - String.split
 * 4. Calculate the total number of ERROR logs. Use Files.lines
 * 5. Compare two results
 **/

public class Main {

    public static void main(String[] args) throws IOException {

        // 1. Parse the file logs.txt. Extract to a separate file all the logs from November 2019
        String fileData = new String(Files.readAllBytes(Paths.get("F:\\FileReader\\src\\com\\company\\logs.txt")));
        // split file string into array by next line
        String[] lines = fileData.split("\\n");

        // Get all lines which are dated in November 2019
        List<String> novemberLines = Files.lines(Paths.get("F:\\FileReader\\src\\com\\company\\logs.txt"))
                .filter(line -> line.contains("2019-11"))
                .collect(Collectors.toList());

        String novemberLogs = "";
        // collect List elements to novemberLines string
        for (String line : novemberLines){
            novemberLogs += line + System.lineSeparator();
        }

        Path path = Paths.get("F:\\FileReader\\src\\com\\company\\NovemberLogs.txt");

        // write result to the file
        Files.write(path, novemberLogs.getBytes());

        //--------------------------------------------------------------------

        // 2. Calculate the total number of logs (lines)
        System.out.println("The total number of logs (lines): " + lines.length);

        //--------------------------------------------------------------------

        // 3. Calculate the total number of ERROR logs. Use previous skills - String.split
        int errorsLines = 0;
        // create a timestamp of execution start
        LocalDateTime start1 = LocalDateTime.now();

        // go for each line and check if "ERROR" word exists on it
        for (String line : lines) {
            if (line.contains("ERROR")) {
                errorsLines++;
            }
        }
        // create a timestamp of execution finish
        LocalDateTime finish1 = LocalDateTime.now();
        // calculate difference between start and finish
        long executionTime1 = ChronoUnit.MILLIS.between(start1, finish1);

        System.out.println("the total number of ERROR logs: (using String.split) " + errorsLines);

        //---------------------------------------------------------------------

        // 4. Calculate the total number of ERROR logs. Use Files.lines
        // create a timestamp of execution start
        LocalDateTime start2 = LocalDateTime.now();
        final long errorsLines2 = Files.lines(Paths.get("F:\\FileReader\\src\\com\\company\\logs.txt"))
                .filter(line -> line.contains("ERROR"))
                .count();

        // create a timestamp of execution finish
        LocalDateTime finish2 = LocalDateTime.now();
        // calculate difference between start and finish
        long executionTime2 = ChronoUnit.MILLIS.between(start2, finish2);

        System.out.println("The total number of ERROR logs: (using Files.lines) " + errorsLines2);

        //--------------------------------------------------------------------

        // 5. Compare two results
        System.out.println("It took " + executionTime1 + " ms. to count ERROR logs using String.split");
        System.out.println("It took " + executionTime2 + " ms. to count ERROR logs using Files.lines");

    }
}
