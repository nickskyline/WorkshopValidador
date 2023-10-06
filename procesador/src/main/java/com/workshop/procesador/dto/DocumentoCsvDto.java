package com.workshop.procesador.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DocumentoCsvDto  {

    private String url;
    private Integer index;
    private Integer userId;
    private String firstName;
    private String lastName;
    private String sex;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String jobTitle;
}
