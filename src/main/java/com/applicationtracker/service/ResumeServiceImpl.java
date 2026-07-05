package com.applicationtracker.service;

import com.applicationtracker.entity.Resume;
import com.applicationtracker.entity.User;
import com.applicationtracker.repository.ResumeRepo;
import com.applicationtracker.repository.UserRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ResumeServiceImpl implements ResumeService{

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ResumeRepo repo;

    @Autowired
    private UserRepository userRepository;



    @Override
    public Resume uploadResume(MultipartFile file, Long userId) throws IOException {
        String contentType = file.getContentType();
        if (contentType == null ||
                !(contentType.equals("application/pdf") ||
                        contentType.equals("application/msword") ||
                        contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))) {
            throw new IllegalArgumentException("Only PDF or DOC/DOCX files are allowed");

        }
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type","raw","folders","resumes"));


        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User doesnt found"));

        Resume resume = new Resume();
        resume.setUser(user);
        resume.setFileName(file.getOriginalFilename());
        resume.setFileUrl(uploadResult.get("secure_url").toString());
        resume.setFileType(contentType);
        resume.setFileSize(file.getSize());
        resume.setPublicId(uploadResult.get("public_id").toString());

        return repo.save(resume);


    }

    @Override
    public List<Resume> getResumeByUser(Long userId) {
        return repo.findByUserId(userId);
    }

    @Override
    public void deleteResume(Long resumeId) throws IOException {

        Resume resume = repo.findById(resumeId).orElseThrow(()-> new RuntimeException("Resume not found"));

        cloudinary.uploader().destroy(resume.getPublicId(),
                ObjectUtils.asMap("resource_type", "raw"));
        repo.delete(resume);


    }
}
