package com.workshop.procesador.service;

import com.workshop.procesador.component.ProcesadorDocumentoStrategyFactory;
import com.workshop.procesador.dto.ProcesadorDocumento;
import com.workshop.procesador.feign.DocumentFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final ProcesadorDocumentoStrategyFactory procesadorDocumentoStrategyFactory;
    private DocumentFeignClient documentFeignClient;

   /* @Autowired
    public DocumentService(ProcesadorDocumentoStrategyFactory procesadorDocumentoStrategyFactory, DocumentFeignClient documentFeignClient) {
        this.procesadorDocumentoStrategyFactory = procesadorDocumentoStrategyFactory;
        this.documentFeignClient = documentFeignClient;
    }

    */

    public void uploadFile(ProcesadorDocumento file) {
        documentFeignClient.uploadFile(file);
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
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        // Determina el tipo de archivo
        String fileType = getFileType(file.getOriginalFilename());

        // Obtén la estrategia adecuada del factory
        ProcesadorDocumento strategy = procesadorDocumentoStrategyFactory.getStrategy(fileType);

        if (strategy != null) {
            strategy.procesarDocumento(file);
            // Tu lógica de procesamiento adicional aquí

            return "Archivo cargado y procesado con éxito";
        } else {
            return "Tipo de archivo no compatible";
        }


    }
}
