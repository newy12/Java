package com.summar.summar.service;

import com.summar.summar.domain.Feed;
import com.summar.summar.domain.FeedImage;
import com.summar.summar.dto.FeedDto;
import com.summar.summar.dto.FeedRegisterDto;
import com.summar.summar.repository.FeedImageRepository;
import com.summar.summar.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class FeedService {

    private final FeedRepository feedRepository;
    private final FeedImageRepository feedImageRepository;

    private final S3Service s3Service;

    @Transactional
    public FeedDto saveFeed(FeedRegisterDto feedRegisterDto) {
        Feed feed = new Feed(feedRegisterDto);
        Long feedSeq = feedRepository.save(feed).getFeedSeq();
        feedRegisterDto.getImages().forEach(
                image -> {
                    FeedImage feedImage = new FeedImage(feedSeq, s3Service.upload(image,S3Service.FEED_IMAGE),feedRegisterDto.getImages().indexOf(image), feed);
                    feedImageRepository.save(feedImage);
                });
        return FeedDto.builder()
                .feedSeq(feedSeq)
                .feedImages(feedImageRepository.findByFeedSeq(feedSeq))
                .userSeq(feedRegisterDto.getUserSeq())
                .contents(feedRegisterDto.getContents())
                .build();
    }

    @Transactional
    public List<FeedDto> getFeed() {
        List<Feed> feeds = feedRepository.findAll();
        List<FeedDto> feedDtos = new ArrayList<>();
        feeds.forEach(
                feed -> feedDtos.add(FeedDto.builder()
                        .feedSeq(feed.getFeedSeq())
                        .feedImages(feedImageRepository.findByFeedSeq(feed.getFeedSeq()))
                        .userSeq(feed.getUserSeq())
                        .contents(feed.getContents())
                        .build()));
        return feedDtos;
    }
}
