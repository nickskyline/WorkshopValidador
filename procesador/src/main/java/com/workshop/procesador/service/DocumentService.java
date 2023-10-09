package com.workshop.procesador.service;

import com.workshop.procesador.component.ProcesadorDocumentoStrategyFactory;
import com.workshop.procesador.dto.DocumentoDto;
import com.workshop.procesador.dto.ProcesadorDocumento;
import com.workshop.procesador.feign.DocumentFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final ProcesadorDocumentoStrategyFactory procesadorDocumentoStrategyFactory;
    private DocumentFeignClient documentFeignClient;

   public void upload(String documentoDto) {
        documentFeignClient.upload(documentoDto);
    }

    private String getFileType(String fileName) {
        // Implementa la lógica para detectar el tipo de archivo (por ejemplo, por extensión)
        String[] parts = fileName.split("\\.");
        if (parts.length > 0) {
            return parts[parts.length - 1].toLowerCase();
        }
        return "";
    }

    @PostMapping("/upload")
    public Map<String, Integer> uploadFile(@RequestBody DocumentoDto documentoDto) {

        // Determina el tipo de archivo
        String fileType = documentoDto.getFileType();

        // Obtén la estrategia adecuada del factory
        ProcesadorDocumento strategy = procesadorDocumentoStrategyFactory.getStrategy(fileType);

        return strategy.procesarDocumento(fileType);
    }
}
