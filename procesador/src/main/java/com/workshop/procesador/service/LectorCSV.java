package com.workshop.procesador.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.workshop.procesador.dto.ProcesadorDocumento;
import com.workshop.procesador.feign.DocumentFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Component
public class LectorCSV implements ProcesadorDocumento {
    private CSVReader lector;
    private String linea;
    private String partes[] = null;

    private DocumentFeignClient documentFeignClient;
    @Autowired
    public LectorCSV(DocumentFeignClient documentFeignClient) {
        this.documentFeignClient = documentFeignClient;
    }

    public Map<String, Integer> procesarDocumento(String file) {
        int lineasValidas = 0;
        int lineasInvalidas = 0;
        Map<String, Integer> validaciones = new HashMap<>();
        Map<String, Object> datos = new HashMap<>();

        try {
            lector = new CSVReader(new FileReader(file));

            while ((partes = lector.readNext()) != null) {
                datos.put("tipo",".csv");
                datos.put("datos",partes);

                if (documentFeignClient.upload(datos)) {
                    lineasValidas++;
                } else {
                    lineasInvalidas++;
                }
                datos.clear();
            }
            lector.close();
            linea = "";
            //partes = null;
        } catch (Exception e) {
            e.getMessage();
        }
        validaciones.put("lineasValidas", lineasValidas);
        validaciones.put("lineasInvalidas", lineasInvalidas);
        return validaciones;
    }

}