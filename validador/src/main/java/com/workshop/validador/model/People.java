package com.workshop.validador.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class People {
    private int index;
    private String userId;
    private String firstName;
    private String lastName;
    private String sex;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private String jobTitle;
}
