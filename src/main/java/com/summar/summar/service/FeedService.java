package com.summar.summar.service;

import com.summar.summar.domain.Feed;
import com.summar.summar.domain.FeedImage;
import com.summar.summar.dto.FeedDto;
import com.summar.summar.dto.FeedRegisterDto;
import com.summar.summar.repository.FeedImageRepository;
import com.summar.summar.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    private final UserRepository userRepository;

    private final S3Service s3Service;

    @Transactional
    public FeedDto saveFeed(FeedRegisterDto feedRegisterDto) {
        User user = userRepository.findById(feedRegisterDto.getUserSeq()).get();
        Feed feed = new Feed(feedRegisterDto,user);
        Long feedSeq = feedRepository.save(feed).getFeedSeq();
        feedRegisterDto.getImages().forEach(
                image -> {
                    String feedImg = s3Service.upload(image,S3Service.FEED_IMAGE);
                    FeedImage feedImage = new FeedImage(feedSeq, feedImg.replace("https","http"),feedRegisterDto.getImages().indexOf(image), feed);
                    feedImageRepository.save(feedImage);
                });
        SimpleUserVO simpleUserVO = new SimpleUserVO(userRepository.findById(feedRegisterDto.getUserSeq()).get());
        return FeedDto.builder()
                .feedSeq(feedSeq)
                .feedImages(feedImageRepository.findByFeedSeq(feedSeq))
                .user(simpleUserVO)
                .contents(feedRegisterDto.getContents())
                .commentYn(feedRegisterDto.isCommentYn())
                .tempSaveYn(feedRegisterDto.isTempSaveYn())
                .secretYn(feedRegisterDto.isSecretYn())
                .build();
    }

    @Transactional(readOnly = true)
    public Page<FeedDto> getFeed(Pageable page) {
        Page<Feed> feeds = feedRepository.findAllByActivatedIsTrueAndSecretYnIsFalseAndTempSaveYnIsFalse(page);
        List<FeedDto> feedDtos = new ArrayList<>();
        feeds.forEach(
                feed -> feedDtos.add(FeedDto.builder()
                        .feedSeq(feed.getFeedSeq())
                        .feedImages(feedImageRepository.findByFeedSeq(feed.getFeedSeq()))
                        .user(new SimpleUserVO(feed.getUser()))
                        .contents(feed.getContents())
                        .build()));
        return new PageImpl<>(feedDtos,page,feeds.getTotalElements());
    }

    @Transactional(readOnly = true)
    public Page<FeedDto> getFeedByUserSeq(Long userSeq,Pageable page) {
        Page<Feed> feeds = feedRepository.findAllByActivatedIsTrueAndSecretYnIsFalseAndTempSaveYnIsFalseAndUserUserSeq(userSeq,page);
        if (userSeq.equals(JwtUtil.getCurrentUserSeq().get())) {
            feeds = feedRepository.findAllByActivatedIsTrueAndTempSaveYnIsFalseAndUserSeq(userSeq, page);
        } else {
            feeds = feedRepository.findAllByActivatedIsTrueAndSecretYnIsFalseAndTempSaveYnIsFalseAndUserSeq(userSeq, page);
        }
        List<FeedDto> feedDtos = new ArrayList<>();
        feeds.forEach(
                feed -> feedDtos.add(FeedDto.builder()
                        .feedSeq(feed.getFeedSeq())
                        .feedImages(feedImageRepository.findByFeedSeq(feed.getFeedSeq()))
                        .user(new SimpleUserVO(feed.getUser()))
                        .contents(feed.getContents())
                        .build()));
        return new PageImpl<>(feedDtos,page,feeds.getTotalElements());
    }
}
