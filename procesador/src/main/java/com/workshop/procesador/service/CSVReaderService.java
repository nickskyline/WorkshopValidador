package com.workshop.procesador.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.workshop.procesador.dto.FileRequestDTO;
import com.workshop.procesador.dto.FileDTO;
import com.workshop.procesador.dto.FileProcessorDTO;
import com.workshop.procesador.feign.FeignFileClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Component
public class CSVReader implements FileProcessor {
    private CSVReader reader;
    private String records[] = null;
    private FeignFileClient feignFileClient;
    @Autowired
    public CSVReader(FeignFileClient feignFileClient) {
        this.feignFileClient = feignFileClient;
    }

    public Map<String, Integer> fileProcess(String file) {
        int validRows = 0;
        int invalidRows = 0;
        Map<String, Integer> validations = new HashMap<>();

        try {
            reader = new CSVReader(new FileReader(file));
            boolean firstRow = true;

            while ((records = reader.readNext()) != null) {

                if (firstRow) {
                    // Saltar la primera fila (encabezados)
                    firstRow = false;
                    continue;
                }

                FileRequestDTO fileRequestDTO = new FileRequestDTO();
                fileRequestDTO.setRecords(records);
                fileRequestDTO.setType(".csv");

                if (feignFileClient.upload(fileRequestDTO)) {
                    validRows++;
                } else {
                    invalidRows++;
                }
            }
            reader.close();

        } catch (Exception e) {
            e.getMessage();
        }
        validations.put("validRows", validRows);
        validations.put("invalidRows", invalidRows);
        return validations;
    }

}