package com.summar.summar.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3Service {

   /* public S3Service(ApplicationProperties applicationProperties) {
        ApplicationProperties.Aws aws = applicationProperties.getAws();
        String accessKey = aws.getAccessKey();
        String secretKey = aws.getSecretKey();
        imageBucket = aws.getS3ImageBucket();
        removeUploadedUrl = "https://" + imageBucket + "/";
        log.debug("==> Image bucket: {}", imageBucket);
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        this.amazonS3 = new AmazonS3Client(credentials);
    }

    public String upload(MultipartFile uploadFile, String dirName) {
        String filePath = dirName + "/" + convertFileName(uploadFile);
        String uploadImageUrl = putS3(uploadFile, filePath);
        uploadImageUrl = uploadImageUrl.replace(S3_REMOVE_UPLOAD_URL, "");
        log.debug("===>> uploadImageUrl: {}", uploadImageUrl);
        return uploadImageUrl;
    }
    private String putS3(MultipartFile uploadFile, String filePath) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(uploadFile.getContentType());
        metadata.setContentLength(uploadFile.getSize());
        PutObjectRequest putObjectRequest;
        try {
            putObjectRequest = new PutObjectRequest(imageBucket, filePath, uploadFile.getInputStream(), metadata);
        } catch (IOException e) {
            throw new RuntimeException("Fail to upload file");
        }
        putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
        amazonS3.putObject(putObjectRequest);
        return amazonS3.getUrl(imageBucket, filePath).toString();
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
    }*/
}
