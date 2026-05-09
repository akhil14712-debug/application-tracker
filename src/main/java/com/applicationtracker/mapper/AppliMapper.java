package com.applicationtracker.mapper;

import com.applicationtracker.dto.AppliDto;
import com.applicationtracker.entity.Application;

public class AppliMapper {

    public static Application mapToAppli(AppliDto appliDto){
        Application app = new Application();
        app.setAppId(appliDto.getAppId());
        app.setCompanyName(appliDto.getCompanyName());
        app.setRole(appliDto.getRole());
        app.setAppliDate(appliDto.getAppliDate());
        app.setStatus(appliDto.getStatus());
        app.setLocation(appliDto.getLocation());
        app.setCareerLink(appliDto.getCareerLink());

        return app;
    }

    public static AppliDto mapToDto(Application app){
        AppliDto dto = new AppliDto();
        dto.setAppId(app.getAppId());
        dto.setCompanyName(app.getCompanyName());
        dto.setRole(app.getRole());
        dto.setAppliDate(app.getAppliDate());
        dto.setStatus(app.getStatus());
        dto.setLocation(app.getLocation());
        dto.setCareerLink(app.getCareerLink());
        return dto;
    }
}
