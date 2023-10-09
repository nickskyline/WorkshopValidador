package com.workshop.procesador.service;

import com.opencsv.CSVReader;
import com.workshop.procesador.dto.ProcesadorDocumento;
import com.workshop.procesador.feign.DocumentFeignClient;
import feign.Response;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LectorCSV implements ProcesadorDocumento {
    private CSVReader lector;
    private String linea;
    private String partes[] = null;

    private DocumentFeignClient documentFeignClient;

    public Map<String, Integer> procesarDocumento(String file) {
        int lineasValidas = 0;
        int lineasInvalidas = 0;
        Map<String, Integer> validaciones = new HashMap<>();

        try {
            lector = new CSVReader(new FileReader("/home/nicolas/Downloads/people.csv"));
            while ((linea = lector.readNext() != null)) {
                partes = linea.split(",");

                Response validacion = new RestTemplate().postForEntity(
                        "http://localhost:8080/api/v1/csv", linea, Response.class).getBody();

                if (documentFeignClient.upload(linea)) {
                    lineasValidas++;
                } else {
                    lineasInvalidas++;
                }

            }
            lector.close();
            linea = null;
            partes = null;
        } catch (Exception e) {
            e.getMessage();
        }
        validaciones.put("lineasValidas", lineasValidas);
        validaciones.put("lineasInvalidas", lineasInvalidas);
        return validaciones;
    }

}