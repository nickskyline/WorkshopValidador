package com.workshop.procesador.component;

import com.workshop.procesador.dto.ProcesadorDocumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProcesadorDocumentoStrategyFactory {

        private final Map<String, ProcesadorDocumento> strategies;

        @Autowired
        public ProcesadorDocumentoStrategyFactory(Map<String, ProcesadorDocumento> strategies) {
            this.strategies = strategies;
        }

        public ProcesadorDocumento getStrategy(String fileType) {
            return strategies.get(fileType);
        }
    }

