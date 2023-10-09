package com.workshop.validador.service;

import com.workshop.validador.model.People;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
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
        LocalDate fechaMinima = LocalDate.of(1980, 1, 1);
        LocalDate fechaNac = LocalDate.parse(fechaNacimiento);
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
        public boolean validarRegistro(String file) {

            String[] parametros = file.split(",");
            People people = new People();
            people.setEmail(parametros[0]);
            people.setBirthDate(LocalDate.parse(parametros[1]));
            people.setJobTitle(parametros[2]);

            boolean correoValido = validarCorreoElectronico(people.getEmail());
            boolean fechaValida = validarFechaNacimiento(String.valueOf(people.getBirthDate()));
            boolean tituloValido = validarTituloTrabajo(people.getJobTitle());

            return correoValido && fechaValida && tituloValido;
        }
    }


