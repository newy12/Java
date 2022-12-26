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
                    FeedImage feedImage = new FeedImage();
                    feedImage.setFeedSeq(feedSeq);
                    feedImage.setFeed(feed);
                    feedImage.setImageUrl(s3Service.upload(image,S3Service.FEED_IMAGE));
                    feedImage.setOrderNo(feedRegisterDto.getImages().indexOf(image));
                    feedImageRepository.save(feedImage);
                });
        return FeedDto.builder()
                .feedSeq(feedSeq)
                .imageUrls(feedImageRepository.findByFeedSeq(feedSeq))
                .userSeq(feedRegisterDto.getUserSeq())
                .build();
    }
}
