package com.workshop.validador.model;

import com.opencsv.CSVReader;
import com.opencsv.bean.util.OpencsvUtils;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class LectorCSV {
    private CSVReader lector;
    private String linea;
    private String partes[] = null;

    public void leerArchivo(String nombreArchivo) {
        try {
            lector = new CSVReader(new FileReader(nombreArchivo));
            while ((linea = Arrays.toString(lector.readNext())) != null) {
                partes = linea.split(",");
                imprimirLinea();
                System.out.println();
            }
            lector.close();
            linea = null;
            partes = null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void imprimirLinea() {
        for (int i = 0; i < partes.length; i++) {
            System.out.print(partes[i] + "   |   ");
        }
    }
}
