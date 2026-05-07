package com.applicationtracker.service;

import com.applicationtracker.dto.AppliDto;
import com.applicationtracker.entity.Application;
import com.applicationtracker.mapper.AppliMapper;
import com.applicationtracker.repository.AppliRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class AppliSeviceImpl implements AppliService{

    private AppliRepo appliRepo;
    @Override
    public AppliDto createAppli(AppliDto appliDto) {
        Application app = AppliMapper.mapToAppli(appliDto);
        return AppliMapper.mapToDto(appliRepo.save(app));
    }

    @Override
    public List<AppliDto> getAllAppli() {
        List<Application> list = appliRepo.findAll();
        return list.stream()
                .map(AppliMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
