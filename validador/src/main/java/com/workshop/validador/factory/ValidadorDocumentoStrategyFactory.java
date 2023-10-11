package com.workshop.validador.factory;

import com.workshop.validador.model.ValidadorRegistro;
import com.workshop.validador.service.ValidadorCSV;
import com.workshop.validador.service.ValidadorXLSX;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ValidadorDocumentoStrategyFactory {

    private ValidadorCSV validadorCSV;
    private ValidadorXLSX validadorXLSX;
    private Map<String, ValidadorRegistro> strategies;

    @Autowired
    public ValidadorDocumentoStrategyFactory(ValidadorCSV validadorCSV, ValidadorXLSX validadorXLSX) {
        this.validadorCSV = validadorCSV;
        this.validadorXLSX = validadorXLSX;
        this.strategies = new HashMap<>(){{
            put(".csv", validadorCSV);
            put(".xlsx", validadorXLSX);
        }};
    }

    public ValidadorRegistro getStrategy(String fileType){
        return strategies.get(fileType);
    }
}
