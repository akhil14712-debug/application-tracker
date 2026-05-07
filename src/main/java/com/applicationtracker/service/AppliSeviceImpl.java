package com.applicationtracker.service;

import com.applicationtracker.dto.AppliDto;
import com.applicationtracker.entity.Application;
import com.applicationtracker.exception.ResourceNotFoundException;
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

    @Override
    public AppliDto getById(Long id) {
        Application app = appliRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("The given id is not found"));

        return AppliMapper.mapToDto(app);
    }

    @Override
    public AppliDto updateAppli(Long id, AppliDto appliDto) {
        Application appli = appliRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The given id is not found"));
        appli.setCompanyName(appliDto.getCompanyName());
        appli.setRole(appliDto.getRole());
        appli.setAppliDate(appliDto.getAppliDate());
        appli.setStatus(appliDto.getStatus());
        appli.setLocation(appliDto.getLocation());

        return AppliMapper.mapToDto(appliRepo.save(appli));
    }

    @Override
    public void deleteAppli(Long id) {
        appliRepo.deleteById(id);
    }


}
