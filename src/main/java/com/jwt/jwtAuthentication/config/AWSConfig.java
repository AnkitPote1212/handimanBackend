package com.jwt.jwtAuthentication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
@EnableAsync
public class AWSConfig {
	
	 @Value("${cloud.aws.credentials.access-key}")
	 private String accessKey;
	 
	 @Value("${cloud.aws.credentials.secret-key}")
	 private String secretKey;
	 
	 @Value("${cloud.aws.region.static}")
	 private String region;
	 
	 @Value("${application.bucket.name}")
	 private String bucketName;
	
	 @Bean
	 public AmazonS3 getAmazonS3Client() {
		 
		 ClientConfiguration clientConfig = new ClientConfiguration();

	        // Set the connection timeout in milliseconds (e.g., 30 seconds).
	        int connectionTimeoutMillis = 30000; // 30 seconds
	        clientConfig.setConnectionTimeout(connectionTimeoutMillis);

		 
	        final BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
	        return AmazonS3ClientBuilder
	                .standard()
	                .withCredentials(new AWSStaticCredentialsProvider(credentials))
	                .withRegion(region)
	                .withClientConfiguration(clientConfig)
	                .build();
	    }
//	 @Bean
//	    public AmazonS3 amazonS3Client() {
//	        return AmazonS3ClientBuilder.standard()
//	                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
//	                .build();
//	    }
}
