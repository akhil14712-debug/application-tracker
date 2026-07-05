package com.applicationtracker.service;

import ch.qos.logback.core.util.StringUtil;
import com.applicationtracker.entity.Resume;
import com.applicationtracker.entity.User;
import com.applicationtracker.repository.ResumeRepo;
import com.applicationtracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ResumeService {

    @Value("${resume.upload.dir:uploads/resumes}")
    private String uploadDir;

    @Autowired
    private ResumeRepo resumeRepo;

    @Autowired
    private UserRepository userRepository;

    public Resume upload(MultipartFile file,Long userId) throws IOException {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("The given userId is not found"));

        String original = StringUtils.cleanPath(file.getOriginalFilename());
        String ext = original.substring(original.lastIndexOf("."));
        String storedName = UUID.randomUUID() + ext;

        Path uploadPath = Paths.get(uploadDir);

        if(!Files.exists(uploadPath)) Files.createDirectories(uploadPath);
        Path  target = uploadPath.resolve(storedName);

        Files.copy(file.getInputStream(),target, StandardCopyOption.REPLACE_EXISTING);


        Resume resume
                = resumeRepo.findByUserId(userId).orElse(new Resume());

        resume.setUser(user);
        resume.setOriginalFileName(original);
        resume.setStoredFilName(storedName);
        resume.setFilePath(target.toString());
        resume.setFileType(file.getContentType());
        resume.setUploadedAt(LocalDateTime.now());

        return resumeRepo.save(resume);

    }

    public Resource view(Long userId) throws IOException {
        Resume resume = resumeRepo.findByUserId(userId)
                .orElseThrow( () -> new RuntimeException("No Resume Found"));
        return new UrlResource(Paths.get(resume.getFilePath()).toUri());
    }

    public Resume getResumeInfo(Long userId){
        return resumeRepo.findByUserId(userId)
                .orElseThrow(()->new RuntimeException("No resume found"));
    }
}
