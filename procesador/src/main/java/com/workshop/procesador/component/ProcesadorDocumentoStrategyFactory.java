package com.workshop.procesador.component;

import com.workshop.procesador.dto.ProcesadorDocumento;
import com.workshop.procesador.feign.DocumentFeignClient;
import com.workshop.procesador.service.LectorCSV;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ProcesadorDocumentoStrategyFactory {

    private LectorCSV lectorCSV;
    private Map<String, ProcesadorDocumento> strategies;
    @Autowired
    public ProcesadorDocumentoStrategyFactory(LectorCSV lectorCSV) {
        this.lectorCSV = lectorCSV;
        strategies = new HashMap<>(){{
            put(".csv", lectorCSV);
        }};
    }

    public ProcesadorDocumento getStrategy(String fileType) {
            return strategies.get(fileType);
        }
    }

