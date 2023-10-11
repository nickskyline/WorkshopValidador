package com.workshop.procesador.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class DocumentRequest {
    private String tipo;
    private String[] registros;
}
