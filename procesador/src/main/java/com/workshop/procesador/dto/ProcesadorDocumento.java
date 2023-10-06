package com.workshop.procesador.dto;

import org.springframework.web.multipart.MultipartFile;

public interface ProcesadorDocumento {
    void procesarDocumento(MultipartFile file);
}
