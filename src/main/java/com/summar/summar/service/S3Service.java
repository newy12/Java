package com.summar.summar.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.summar.summar.config.ApplicationProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3Service {
    private AmazonS3 amazonS3;
    private String s3Bucket;
    private String removeUploadedUrl;

    static final String FEED_IMAGE="/feed";

     public S3Service(ApplicationProperties applicationProperties) {
        ApplicationProperties.Aws aws = applicationProperties.getAws();
        String accessKey = aws.getAccessKey();
        String secretKey = aws.getSecretKey();
        s3Bucket = aws.getS3Bucket();
        removeUploadedUrl = "https://" + s3Bucket + "/";
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        this.amazonS3 = new AmazonS3Client(credentials);
    }

    public String upload(MultipartFile uploadFile, String dirName) {
        String filePath = dirName + "/" + convertFileName(uploadFile);
        String uploadImageUrl = putS3(uploadFile, filePath);
         return uploadImageUrl;
    }

    private String putS3(MultipartFile uploadFile, String filePath) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(uploadFile.getContentType());
        metadata.setContentLength(uploadFile.getSize());
        PutObjectRequest putObjectRequest;
        try {
            putObjectRequest = new PutObjectRequest(s3Bucket, filePath, uploadFile.getInputStream(), metadata);
        } catch (IOException e) {
            throw new RuntimeException("Fail to upload file");
        }
        putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
        amazonS3.putObject(putObjectRequest);
        return amazonS3.getUrl(s3Bucket, filePath).toString();
    }

    private String convertFileName(MultipartFile file) {
        String extension = getFileExtension(file.getOriginalFilename());
        String fileName = UUID.randomUUID() + extension;
        return fileName;
    }

    private String getFileExtension(String fileName) {
        int extensionPosition = fileName.lastIndexOf(".");
        String extension = fileName.substring(extensionPosition).toLowerCase();
        return extension;
    }
}
