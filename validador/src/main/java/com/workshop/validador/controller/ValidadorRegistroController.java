package com.workshop.validador.controller;

import com.workshop.validador.service.ValidadorRegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class ValidadorRegistroController {

    @Autowired
    private ValidadorRegistroService validadorRegistroService;

    @PostMapping("/files")
    public boolean validarRegistros(@RequestBody Map<String, String[]> datos) {

        return this.validadorRegistroService.validarRegistros(datos);
    }
}
