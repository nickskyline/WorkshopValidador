package com.workshop.validador.service;

import com.workshop.validador.model.ValidadorRegistro;
import org.springframework.stereotype.Component;

@Component
public class ValidadorXLSX implements ValidadorRegistro {
    @Override
    public boolean validarRegistros(String[] file) {
        return isValidInjuryLocation(file[0]) && isValidReportType(file[1]);
    }

    private boolean isValidInjuryLocation(String injuryLocation) {
        return !"N/A".equals(injuryLocation);
    }

    private boolean isValidReportType(String reportType) {
        return "Near Miss".equals(reportType) || "Lost Time".equals(reportType) || "First Aid".equals(reportType);
    }
}
