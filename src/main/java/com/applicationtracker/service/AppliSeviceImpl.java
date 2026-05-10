package com.applicationtracker.service;

import com.applicationtracker.dto.AppliDto;
import com.applicationtracker.entity.Application;
import com.applicationtracker.exception.ResourceNotFoundException;
import com.applicationtracker.mapper.AppliMapper;
import com.applicationtracker.repository.AppliRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        appli.setCareerLink(appliDto.getCareerLink());

        return AppliMapper.mapToDto(appliRepo.save(appli));
    }

    @Override
    public void deleteAppli(Long id) {
        appliRepo.deleteById(id);
    }

    @Override
    public Map<String, Object> searchApplication(String name, int pageNo, int pageSize, String sortBy, String sortDir) {

        String sortField = "appId";
                if( sortBy == null || sortBy.trim().isEmpty()){
                    switch(sortBy.toLowerCase()){
                        case "name":
                            sortField = "companyName";
                            break;
                        case "role":
                            sortField = "role";
                            break;
                        case "appliDate":
                            sortField = "appliDate";
                            break;

                    }
                }



        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortField).descending()
                : Sort.by(sortField).ascending();

        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);

        Page<Application> result = appliRepo.searchSortPagination(name,pageable);

        List<AppliDto> applilist = result.getContent()
                .stream().map(AppliMapper::mapToDto)
                .collect(Collectors.toList());

        Map<String,Object> map = new HashMap<>();
        map.put("currentPage",result.getNumber());
        map.put("totalNumber",result.getTotalElements());
        map.put("isLast",result.isLast());
        map.put("totalItems",result.getTotalElements());

        Map<String,Object> map1 = new HashMap<>();

        map1.put("data",applilist);
        map1.put("paggination",map);

        return map1;
    }

    @Override
    public Map<String, Integer> countList() {
        int applyCount = appliRepo.countByStatus("Applied");
        int active = appliRepo.countByStatus("Active");
        int pending = appliRepo.countByStatus("Pending");
        int intervi = appliRepo.countByStatus("Interview");

        Map<String,Integer> result = new HashMap<>();
        result.put("applyCnt",applyCount);
        result.put("active",active);
        result.put("pending",pending);
        result.put("interv",intervi);

        return result;
    }


}
