package com.workshop.validador.controller;

import com.workshop.validador.service.ValidadorCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class ValidadorCSVController {

    private ValidadorCSV validadorCSV;
    @Autowired
    public ValidadorCSVController(ValidadorCSV validadorCSV) {
        this.validadorCSV = validadorCSV;
    }

    @PostMapping("/csv")
    public boolean validarRegistros(@RequestBody String[] file) {
        boolean res = this.validadorCSV.validarRegistros(file);
        return res;
    }
}
