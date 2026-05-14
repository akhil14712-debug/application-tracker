package com.applicationtracker.service;

import com.applicationtracker.dto.AppliDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface AppliService {

    AppliDto createAppli(AppliDto appliDto,Long userId);

    List<AppliDto> getAllAppli(Long userId);

    AppliDto getById(Long id , Long userId);

    AppliDto updateAppli(Long id , AppliDto appliDto ,Long userId);

    void deleteAppli(Long id,Long userId);

    Map<String ,Object> searchApplication(String name,int pageNo,int pageSize,String sortBy,String sortDir,Long userId);

    Map<String,Integer> countList(Long userId);

}
