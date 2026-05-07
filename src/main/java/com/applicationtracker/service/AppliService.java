package com.applicationtracker.service;

import com.applicationtracker.dto.AppliDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppliService {

    AppliDto createAppli(AppliDto appliDto);

    List<AppliDto> getAllAppli();

    AppliDto getById(Long id);

    AppliDto updateAppli(Long id , AppliDto appliDto);

    void deleteAppli(Long id);
}
