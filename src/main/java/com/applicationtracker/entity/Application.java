package com.applicationtracker.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="apply_id")
    private Long appId;

    @Column(name="comp_name")
    private String companyName;

    @Column(nullable = false)
    private String role;

    @Column(name="appli_date")
    private LocalDate appliDate;


    private String status;

    private String location;

    private String careerLink;
}
