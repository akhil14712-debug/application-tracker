package com.applicationtracker.controller;


import com.applicationtracker.dto.AppliDto;
import com.applicationtracker.service.AppliService;
import com.applicationtracker.service.CustomUserDetails;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5174")

@RestController
@RequestMapping("/api/appli")
@AllArgsConstructor

public class AppliationController {

    private AppliService service;


    private Long getCurrentUserId(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((CustomUserDetails) userDetails).getId();
    }

    @PostMapping
    public ResponseEntity<AppliDto> createAppli(@RequestBody AppliDto appliDto){
        AppliDto dto =  service.createAppli(appliDto,getCurrentUserId());
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AppliDto>> getAll(){
        List<AppliDto> re = service.getAllAppli(getCurrentUserId());
        return new ResponseEntity<>(re, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppliDto> getById(@PathVariable Long id){
        AppliDto res = service.getById(id,getCurrentUserId());
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<AppliDto> updateAppli(@PathVariable Long id , @RequestBody AppliDto appliDto){
        AppliDto dtos = service.updateAppli(id,appliDto,getCurrentUserId());
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAppli(@PathVariable Long id ){
        service.deleteAppli(id,getCurrentUserId());
        return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity< Map<String ,Object> > filtering(@RequestParam(defaultValue = "") String name ,
                                                    @RequestParam(defaultValue = "0") int pageNo ,
                                                    @RequestParam(defaultValue = "10") int pageSize ,
                                                    @RequestParam(defaultValue = "appId") String sortBy ,
                                                    @RequestParam(defaultValue = "desc") String sortDir){
        Map<String ,Object> result = service.searchApplication(name,pageNo,pageSize,sortBy,sortDir,getCurrentUserId());
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("count")
    public ResponseEntity<Map<String,Integer>> totalCount(){
        Map<String,Integer> ans = service.countList(getCurrentUserId());
        return new ResponseEntity<>(ans,HttpStatus.OK);
    }


}
