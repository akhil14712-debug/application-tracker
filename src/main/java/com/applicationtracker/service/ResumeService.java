package com.applicationtracker.service;

import com.applicationtracker.entity.Resume;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ResumeService {

    Resume uploadResume(MultipartFile file,Long userId) throws IOException;
    List<Resume> getResumeByUser(Long userId);
    void deleteResume(Long resumeId) throws IOException;
}
