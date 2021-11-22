package com.springboot.book.springbook.controllers;

import javax.servlet.Servlet;

import com.springboot.book.springbook.helper.FileUploadHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FileUploadController {

    @Autowired
    private FileUploadHelper fileUploadHelper;

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request is empty");
            }
            if (!file.getContentType().equals("image/png")) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only png is allowed");
            }
            boolean bool = fileUploadHelper.uploadFile(file);
            if (bool) {
                return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image")
                        .path(file.getOriginalFilename()).toUriString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
    }

}
