package com.workshop.procesador.feign;

import com.workshop.procesador.configuration.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Map;

@FeignClient(name = "DOCUMENT-MOCK-API", url = "http://localhost:8080", configuration = FeignClientConfig.class)
public interface DocumentFeignClient {

    @PostMapping(value = "api/v1/csv", consumes = MediaType.APPLICATION_JSON_VALUE)
    boolean upload(@RequestBody Map<String, Object> documento);

}
