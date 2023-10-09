package com.workshop.procesador.controller;

import com.workshop.procesador.dto.DocumentoDto;
import com.workshop.procesador.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public ResponseEntity<Map<String, Integer>> uploadFile(@RequestBody DocumentoDto documento) {
        return new ResponseEntity<>(this.documentService.uploadFile(documento), HttpStatus.CREATED);
    }
}
