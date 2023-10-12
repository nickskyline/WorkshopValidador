package com.workshop.procesador.dto;

import java.io.FileNotFoundException;
import java.util.Map;

public interface FileProcessorDTO {
    Map<String, Integer> FileProcess(String file) throws FileNotFoundException;
}
