package com.summar.summar.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.summar.summar.domain.Feed;
import com.summar.summar.dto.FeedDto;
import com.summar.summar.dto.FeedRegisterDto;
import com.summar.summar.results.ListResult;
import com.summar.summar.results.ObjectResult;
import com.summar.summar.service.FeedService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = {"피드 API controller"})
@RequestMapping(value = "/api/v1/feed")
public class FeedController {
    private final FeedService feedService;
    private String S3Bucket = "bucket-test-yj";
    private final AmazonS3Client amazonS3Client;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFeed(MultipartFile[] multipartFileList) throws Exception{
        List<String> imagePathList = new ArrayList<>();

        for(MultipartFile multipartFile: multipartFileList) {
            String originalName = multipartFile.getOriginalFilename(); // 파일 이름
            long size = multipartFile.getSize(); // 파일 크기

            ObjectMetadata objectMetaData = new ObjectMetadata();
            objectMetaData.setContentType(multipartFile.getContentType());
            objectMetaData.setContentLength(size);

            // S3에 업로드
            amazonS3Client.putObject(
                    new PutObjectRequest(S3Bucket, originalName, multipartFile.getInputStream(), objectMetaData)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
            );

            String imagePath = amazonS3Client.getUrl(S3Bucket, originalName).toString(); // 접근가능한 URL 가져오기
            imagePathList.add(imagePath);
        }

        return new ResponseEntity<Object>(imagePathList, HttpStatus.OK);
    }





    @Operation(summary = "피드 등록")
    @PostMapping(value = "", consumes = {"multipart/form-data"})
    public ResponseEntity<?> registFeed(@ModelAttribute FeedRegisterDto feedRegisterDto) {
        return ObjectResult.build("result", feedService.saveFeed(feedRegisterDto));
    }

    @Operation(summary = "피드 조회")
    @GetMapping(value = "")
    public ResponseEntity<?> getFeed() {
        return ListResult.build("result", feedService.getFeed());
    }

}
