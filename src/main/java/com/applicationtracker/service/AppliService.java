package com.applicationtracker.service;

import com.applicationtracker.dto.AppliDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface AppliService {

    AppliDto createAppli(AppliDto appliDto);

    List<AppliDto> getAllAppli();

    AppliDto getById(Long id);

    AppliDto updateAppli(Long id , AppliDto appliDto);

    void deleteAppli(Long id);

    Map<String ,Object> searchApplication(String name,int pageNo,int pageSize,String sortBy,String sortDir);

    Map<String,Integer> countList();

}
