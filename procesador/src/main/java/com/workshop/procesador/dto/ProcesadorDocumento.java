package com.workshop.procesador.dto;

import java.util.Map;

public interface ProcesadorDocumento {
    Map<String, Integer> procesarDocumento(String file);
}
