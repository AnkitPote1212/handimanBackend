package com.jwt.jwtAuthentication.service;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class FileService {

    @Autowired
    private AmazonS3  amazonS3;

    @Value("${application.bucket.name}")
    private String bucketName;

    public String uploadFile(MultipartFile file) {
    	String extention=StringUtils.getFilenameExtension(file.getOriginalFilename());
    	String key=UUID.randomUUID().toString()+"."+extention;
    	
    	ObjectMetadata metaData=new ObjectMetadata();
    	metaData.setContentLength(file.getSize());
    	metaData.setContentType(file.getContentType());
    	try {
    		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file.getInputStream(), metaData);
            putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3.putObject(putObjectRequest);
            //amazonS3Client.putObject(bucketName, key, file.getInputStream(),metaData);
    	}catch(IOException e) {
    		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error while uploading the File");
    	}
    	//amazonS3Client.setObjectAcl(bucketName,key,CannedAccessControlList.PublicRead);
    	
    	//return amazonS3.getResourceUrl(bucketName, key);
    	return amazonS3.getUrl(bucketName, key).toString();
    }
  


}
