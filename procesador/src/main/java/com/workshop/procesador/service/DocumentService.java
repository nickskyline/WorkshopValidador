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
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final ProcesadorDocumentoStrategyFactory procesadorDocumentoStrategyFactory;

    public Map<String, Integer> uploadFile(DocumentoDto documentoDto) throws FileNotFoundException {

        String fileType = documentoDto.getFileType();

        ProcesadorDocumento strategy = procesadorDocumentoStrategyFactory.getStrategy(fileType);

        return strategy.procesarDocumento(documentoDto.getRuta());
    }
}
