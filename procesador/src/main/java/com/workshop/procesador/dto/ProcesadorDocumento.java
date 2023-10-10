package com.workshop.procesador.dto;

import java.io.FileNotFoundException;
import java.util.Map;

public interface ProcesadorDocumento {
    Map<String, Integer> procesarDocumento(String file) throws FileNotFoundException;
}
