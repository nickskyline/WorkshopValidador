package com.workshop.procesador.controller;

import com.workshop.procesador.component.ProcesadorDocumentoStrategyFactory;
import com.workshop.procesador.dto.DocumentoCsvDto;
import com.workshop.procesador.dto.DocumentoDto;
import com.workshop.procesador.dto.ProcesadorDocumento;
import com.workshop.procesador.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/files")

public class DocumentoController {
    private final DocumentService documentService;
    @Autowired
    public DocumentoController(DocumentService documentService) {
        this.documentService = documentService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadFile(@RequestBody DocumentoDto documento) {
        uploadFile(documento);
    }
}
