package com.jwt.jwtAuthentication.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jwt.jwtAuthentication.service.FileService;

@RestController
@RequestMapping("/awsUpload")
public class FileController {

    @Autowired
    private FileService fileService;


    @PostMapping("/pushImg")
    public ResponseEntity<Map<String,String>> uploadFile(@RequestParam("file") MultipartFile file) {
        String publicUrl=fileService.uploadFile(file);
        Map<String,String> response=new HashMap<>();
        response.put("publicUrl", publicUrl);
        return new ResponseEntity<Map<String,String>>(response,HttpStatus.CREATED);
    }

}
