package com.applicationtracker.controller;


import com.applicationtracker.dto.AppliDto;
import com.applicationtracker.service.AppliService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appli")
@AllArgsConstructor

public class AppliationController {

    private AppliService service;


    @PostMapping
    public ResponseEntity<AppliDto> createAppli(@RequestBody AppliDto appliDto){
        AppliDto dto =  service.createAppli(appliDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AppliDto>> getAll(){
        List<AppliDto> re = service.getAllAppli();
        return new ResponseEntity<>(re, HttpStatus.OK);
    }
}
