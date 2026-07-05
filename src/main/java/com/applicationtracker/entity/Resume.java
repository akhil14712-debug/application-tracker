package com.applicationtracker.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="resumes")
@Setter
@Getter
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;

    private String fileName;
    private String fileUrl;

    private String fileType;
    private Long fileSize;
    private String publicId;
    private LocalDateTime uploadedAt = LocalDateTime.now();
}
