package com.workshop.validador.controller;

import com.workshop.validador.model.People;
import com.workshop.validador.service.ValidadorCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ValidadorCSVController {
    @Autowired
    private ValidadorCSV validadorCSV;

    @PostMapping("/csv")
    public boolean validarRegistros(@RequestBody String file) {
        return validarRegistros(file);
    }
}
