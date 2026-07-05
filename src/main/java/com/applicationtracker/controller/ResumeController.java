package com.applicationtracker.controller;


import com.applicationtracker.entity.Resume;
import com.applicationtracker.service.ResumeService;
import org.hibernate.boot.jaxb.Origin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.lang.model.util.Elements;
import java.io.IOException;
import java.util.List;
import java.util.TimerTask;

@RestController
@RequestMapping("/api/resume")
@CrossOrigin(origins = "http://localhost:3000")

public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadResume(@RequestParam("file")MultipartFile file,
                                          @RequestParam("userId") Long userId){
        try{
            Resume resume = resumeService.uploadResume(file,userId);
            return ResponseEntity.ok(resume);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
        }
    }
    @GetMapping("/user/{userId}")

    public ResponseEntity<List<Resume>> getResumes(@PathVariable Long userId) {
        return ResponseEntity.ok(resumeService.getResumeByUser(userId));
    }

    @DeleteMapping("/{resumeId}")
    public ResponseEntity<?> deleteResume(@PathVariable Long resumeId) {
        try {
            resumeService.deleteResume(resumeId);
            return ResponseEntity.ok("Deleted successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Delete failed: " + e.getMessage());
        }
    }

}

