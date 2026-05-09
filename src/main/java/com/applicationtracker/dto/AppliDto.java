package com.applicationtracker.dto;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AppliDto {


    private Long appId;
    private String companyName;
    private String role;
    private LocalDate appliDate;
    private String status;
    private String location;
    private String careerLink;
}
