package com.workshop.procesador.service;

import com.poiji.bind.Poiji;
import com.workshop.procesador.dto.DocumentRequest;
import com.workshop.procesador.dto.DocumentXLSX;
import com.workshop.procesador.dto.ProcesadorDocumento;
import com.workshop.procesador.feign.DocumentFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LectorXLSX implements ProcesadorDocumento {

    private FeignFileClient feignFileClient;

    @Autowired
    public XLSXReader(FeignFileClient feignFileClient) {
        this.feignFileClient = feignFileClient;
    }

    @Override
    public Map<String, Integer> processFile(String file) {

        int validRows = 0;
        int invalidRows = 0;

        File file = new File(file);
        List<XLSXFile> records = Poiji.fromExcel(file, XLSXFile.class);

        Map<String, Integer> validations = new HashMap<>();

        for (XLSXFile record: records) {
            String row = record.getInjuryLocation()+","+record.getReportType();
            String[] arrayRow = row.split(",");


            FileRequestDTO fileRequestDTO = new FileRequestDTO();
            fileRequestDTO.setRecords(arrayRow);
            fileRequestDTO.setType(".xlsx");

            if (feignFileClient.upload(fileRequestDTO)) {
                validRows++;
            } else {
                invalidRows++;
            }
        }

        validations.put("validRows", validRows);
        validations.put("invalidRows", invalidRows);

        return validations;
    }
}
