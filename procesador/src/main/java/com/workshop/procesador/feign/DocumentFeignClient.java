package com.workshop.procesador.feign;

import com.workshop.procesador.dto.DocumentoDto;
import com.workshop.procesador.dto.ProcesadorDocumento;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.List;

@FeignClient(name = "Document Processor", url = "http://localhost:8080", configuration = FeignClient.class)
public interface DocumentFeignClient {

    @PostMapping(value = "/files", consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean upload(@RequestBody String documento);

}
