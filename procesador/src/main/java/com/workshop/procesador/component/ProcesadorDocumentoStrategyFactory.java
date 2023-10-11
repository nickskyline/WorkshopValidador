package com.workshop.procesador.component;

import com.workshop.procesador.dto.ProcesadorDocumento;
import com.workshop.procesador.service.LectorCSV;
import com.workshop.procesador.service.LectorXLSX;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ProcesadorDocumentoStrategyFactory {

    private LectorCSV lectorCSV;
    private LectorXLSX lectorXLSX;

    private Map<String, ProcesadorDocumento> strategies;

    @Autowired
    public ProcesadorDocumentoStrategyFactory(LectorCSV lectorCSV, LectorXLSX lectorXLSX) {
        this.lectorCSV = lectorCSV;
        this.lectorXLSX = lectorXLSX;
        strategies = new HashMap<>(){{
            put(".csv", lectorCSV);
            put(".xlsx", lectorXLSX);
        }};
    }

    public ProcesadorDocumento getStrategy(String fileType) {
            return strategies.get(fileType);
        }
    }

