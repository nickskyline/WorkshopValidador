package com.workshop.validador.service;

import com.workshop.validador.model.People;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidadorCSV {
    public boolean validarCorreoElectronico(String correo) {
        // Expresión regular para validar un correo electrónico
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }

    public boolean validarFechaNacimiento(String fechaNacimiento) {
        DateTimeFormatter formatoMmDdYyyy = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate fechaMinima = LocalDate.of(1980, 1, 1);
        LocalDate fechaNac = LocalDate.parse(fechaNacimiento, formatoMmDdYyyy);
        return fechaNac.isAfter(fechaMinima);
    }

    public boolean validarTituloTrabajo(String titulo) {
        String[] titulosValidos = {
                "Haematologist",
                "Phytotherapist",
                "Building surveyor",
                "Insurance account manager",
                "Educational psychologist"
        };
        for (String tituloValido : titulosValidos) {
            if (titulo.equalsIgnoreCase(tituloValido)) {
                return true;
            }
        }
        return false;
    }
        public boolean validarRegistros(String[] file) {
            Map<String, String> data = new HashMap<>();
            DateTimeFormatter formatoMmDdYyyy = DateTimeFormatter.ofPattern("yyyy-M-d");

            data.put("email", file[5]);
            data.put("birthDate", file[7]);
            data.put("jobTitle", file[8]);

            boolean correoValido = validarCorreoElectronico(data.get("email"));
            boolean fechaValida = validarFechaNacimiento(data.get("birthDate"));
            boolean tituloValido = validarTituloTrabajo(data.get("jobTitle"));

            return correoValido && fechaValida && tituloValido;
        }
    }


