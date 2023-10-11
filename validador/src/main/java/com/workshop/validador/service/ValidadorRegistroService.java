package com.workshop.validador.service;

import com.workshop.validador.factory.ValidadorDocumentoStrategyFactory;
import com.workshop.validador.model.DocumentRequest;
import com.workshop.validador.model.ValidadorRegistro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ValidadorRegistroService {

    private final ValidadorDocumentoStrategyFactory validadorDocumentoStrategyFactory;

    public boolean validarRegistros(DocumentRequest datos) {

        String fileType = datos.getTipo();
        ValidadorRegistro strategy = validadorDocumentoStrategyFactory.getStrategy(fileType);

        String[] row = datos.getRegistros();
        return strategy.validarRegistros(row);
    }

}
