package com.workshop.procesador.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DocumentoXlsxDto {

    private String url;
    private LocalDate date;
    private String injury;
    private String location;
    private String gender;
    private String ageGroup;
    private String incidentType;
    private Integer daysLost;
    private String plant;
    private String reportType;
    private String shift;
    private String department;
    private String incidentCost;
    private String wkDay;
    private String month;
    private String year;


}
