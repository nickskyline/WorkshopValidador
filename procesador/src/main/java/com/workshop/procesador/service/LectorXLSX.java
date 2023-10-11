package com.workshop.procesador.service;

import com.poiji.bind.Poiji;
import com.workshop.procesador.dto.DocumentXLSX;
import com.workshop.procesador.dto.ProcesadorDocumento;
import com.workshop.procesador.feign.DocumentFeignClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LectorXLSX implements ProcesadorDocumento {

    private DocumentFeignClient documentFeignClient;

    @Autowired
    public LectorXLSX(DocumentFeignClient documentFeignClient) {
        this.documentFeignClient = documentFeignClient;
    }

    @Override
    public Map<String, Integer> procesarDocumento(String file) {
        int lineasValidas = 0;
        int lineasInvalidas = 0;
        File archivo = new File(file);
        List<DocumentXLSX> registros = Poiji.fromExcel(archivo, DocumentXLSX.class);
        Map<String, Object> datos = new HashMap<>();
        for (DocumentXLSX registro: registros) {
            String row = registro.getInjuryLocation()+","+registro.getReportType();
            String[] arrayRow = row.split(",");
            datos.put("tipo",".xlsx");
            datos.put("datos",arrayRow);
            if (documentFeignClient.upload(datos)) {
                lineasValidas++;
            } else {
                lineasInvalidas++;
            }
            datos.clear();
        }

        return null;
    }
}
