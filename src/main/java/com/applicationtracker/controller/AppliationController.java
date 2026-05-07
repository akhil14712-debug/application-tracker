package com.applicationtracker.controller;


import com.applicationtracker.dto.AppliDto;
import com.applicationtracker.service.AppliService;
import lombok.AllArgsConstructor;

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

    @GetMapping("/{id}")
    public ResponseEntity<AppliDto> getById(@PathVariable Long id){
        AppliDto res = service.getById(id);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<AppliDto> updateAppli(@PathVariable Long id , @RequestBody AppliDto appliDto){
        AppliDto dtos = service.updateAppli(id,appliDto);
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAppli(@PathVariable Long id ){
        service.deleteAppli(id);
        return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
    }


}
