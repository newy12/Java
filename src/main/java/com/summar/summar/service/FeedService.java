package com.summar.summar.service;

import com.summar.summar.domain.Feed;
import com.summar.summar.domain.FeedImage;
import com.summar.summar.domain.FeedLike;
import com.summar.summar.domain.User;
import com.summar.summar.dto.FeedDto;
import com.summar.summar.dto.FeedLikeDto;
import com.summar.summar.dto.FeedRegisterDto;
import com.summar.summar.dto.SimpleUserVO;
import com.summar.summar.repository.FeedImageRepository;
import com.summar.summar.repository.FeedLikeRepository;
import com.summar.summar.repository.FeedRepository;
import com.summar.summar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class FeedService {

    private final FeedRepository feedRepository;
    private final FeedImageRepository feedImageRepository;
    private final FeedLikeRepository feedLikeRepository;
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
    public Optional<FeedDto> getFeedByFeedSeq(Long feedSeq) {
        Optional<Feed> feed = feedRepository.findById(feedSeq);
        return Optional.ofNullable(FeedDto.builder()
                .feedSeq(feedSeq)
                .feedImages(feedImageRepository.findByFeedSeq(feedSeq))
                .user(new SimpleUserVO(feed.get().getUser()))
                .contents(feed.get().getContents())
                .commentYn(feed.get().isCommentYn())
                .tempSaveYn(feed.get().isTempSaveYn())
                .secretYn(feed.get().isSecretYn())
                .activated(feed.get().isActivated())
                .build());
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
                        .activated(feed.isActivated())
                        .build()));
        return new PageImpl<>(feedDtos,page,feeds.getTotalElements());
    }

    @Transactional(readOnly = true)
    public Page<FeedDto> getFeedByUserSeq(Long userSeq,Pageable page) {
        Page<Feed> feeds = feedRepository.findAllByActivatedIsTrueAndSecretYnIsFalseAndTempSaveYnIsFalseAndUserUserSeq(userSeq,page);
        List<FeedDto> feedDtos = new ArrayList<>();
        feeds.forEach(
                feed -> feedDtos.add(FeedDto.builder()
                        .feedSeq(feed.getFeedSeq())
                        .feedImages(feedImageRepository.findByFeedSeq(feed.getFeedSeq()))
                        .user(new SimpleUserVO(feed.getUser()))
                        .contents(feed.getContents())
                        .activated(feed.isActivated())
                        .build()));
        return new PageImpl<>(feedDtos,page,feeds.getTotalElements());
    }

    @Transactional
    public FeedDto updateFeedInActivated(Long feedSeq) {
        Feed feed = feedRepository.findOneByFeedSeq(feedSeq);
        feed.setActivated(false);
        return FeedDto.builder()
                .feedSeq(feedSeq)
                .feedImages(feedImageRepository.findByFeedSeq(feedSeq))
                .user(new SimpleUserVO(feed.getUser()))
                .contents(feed.getContents())
                .commentYn(feed.isCommentYn())
                .tempSaveYn(feed.isTempSaveYn())
                .secretYn(feed.isSecretYn())
                .activated(feed.isActivated())
                .build();
    }

    @Transactional
    public Boolean setFeedLike(Long feedSeq, FeedLikeDto feedLikeDto){
        Optional<FeedLike> feedLike =feedLikeRepository.findByFeedFeedSeqAndUserUserSeq(feedSeq, feedLikeDto.getUserSeq());
        feedLike.ifPresentOrElse(
                findFeed -> {
                        if(findFeed.isActivated()){
                            findFeed.setActivated(false);
                        }else{
                            findFeed.setActivated(true);
                        }
                },
                ()-> {
                    Feed feed = feedRepository.findOneByFeedSeq(feedSeq);
                    User user = userRepository.findByUserSeqAndLeaveYn(feedLikeDto.getUserSeq(),false).get();
                    FeedLike newLike = new FeedLike(feed,user,true);
                    feedLikeRepository.save(newLike);
                });
        return true;
    }
}
