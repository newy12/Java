package com.summar.summar.service;

import com.summar.summar.domain.*;
import com.summar.summar.dto.*;
import com.summar.summar.repository.*;
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

    private final FeedCommentRepository feedCommentRepository;
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
        List<FeedComment> feedComments = feedCommentRepository.findAllByFeedFeedSeq(feedSeq);
        List<FeedCommentDto> feedCommentDtos = new ArrayList<>();
        feedComments.forEach(
                feedComment -> feedCommentDtos.add(new FeedCommentDto(feedComment.getFeedCommentSeq()
                        ,feedSeq ,new SimpleUserVO(feedComment.getUser()),feedComment.isActivated()
                        ,feedComment.getParentCommentSeq(),feedComment.getModifiedDate()
                        ,feedComment.getCreatedDate(),feedComment.getComment()))
        );
        return Optional.ofNullable(FeedDto.builder()
                .feedSeq(feedSeq)
                .feedImages(feedImageRepository.findByFeedSeq(feedSeq))
                .user(new SimpleUserVO(feed.get().getUser()))
                .contents(feed.get().getContents())
                .commentYn(feed.get().isCommentYn())
                .tempSaveYn(feed.get().isTempSaveYn())
                .secretYn(feed.get().isSecretYn())
                .activated(feed.get().isActivated())
                .lastModifiedDate(feed.get().getModifiedDate())
                .createdDate(feed.get().getCreatedDate())
                .feedComments(feedCommentDtos)
                .build());
    }

    @Transactional(readOnly = true)
    public Page<FeedDto> getFeed(Pageable page) {
        Page<Feed> feeds = feedRepository.findAllByActivatedIsTrueAndSecretYnIsFalseAndTempSaveYnIsFalseAndUserLeaveYnIsFalse(page);
        List<FeedDto> feedDtos = new ArrayList<>();
        feeds.forEach(
                feed -> {
                    List<FeedComment> feedComments = feedCommentRepository.findTop3ByFeedFeedSeq(feed.getFeedSeq());
                    List<FeedCommentDto> feedCommentDtos = new ArrayList<>();
                    feedComments.forEach(
                            feedComment -> feedCommentDtos.add(new FeedCommentDto(feedComment.getFeedCommentSeq()
                                    ,feed.getFeedSeq() ,new SimpleUserVO(feedComment.getUser()),feedComment.isActivated()
                                    ,feedComment.getParentCommentSeq(),feedComment.getModifiedDate()
                                    ,feedComment.getCreatedDate(),feedComment.getComment()))
                    );
                    feedDtos.add(FeedDto.builder()
                        .feedSeq(feed.getFeedSeq())
                        .feedImages(feedImageRepository.findByFeedSeq(feed.getFeedSeq()))
                        .user(new SimpleUserVO(feed.getUser()))
                        .contents(feed.getContents())
                        .activated(feed.isActivated())
                        .feedComments(feedCommentDtos)
                        .lastModifiedDate(feed.getModifiedDate())
                        .createdDate(feed.getCreatedDate())
                        .build());
                });
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
                        .lastModifiedDate(feed.getModifiedDate())
                        .createdDate(feed.getCreatedDate())
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
                .lastModifiedDate(feed.getModifiedDate())
                .createdDate(feed.getCreatedDate())
                .build();
    }

    @Transactional
    public Boolean setFeedLike(Long feedSeq, FeedLikeDto feedLikeDto){
        Optional<FeedLike> feedLike =feedLikeRepository.findByFeedFeedSeqAndUserUserSeq(feedSeq, feedLikeDto.getUserSeq());
        feedLike.ifPresentOrElse(
                findFeed -> findFeed.setActivated(!findFeed.isActivated()),
                ()-> {
                    Feed feed = feedRepository.findOneByFeedSeq(feedSeq);
                    User user = userRepository.findByUserSeqAndLeaveYn(feedLikeDto.getUserSeq(),false).get();
                    FeedLike newLike = new FeedLike(feed,user,true);
                    feedLikeRepository.save(newLike);
                });
        return true;
    }

    @Transactional(readOnly = true)
    public Page<FeedCommentDto> getFeedCommentsByFeedSeq(Pageable page, Long feedSeq) {
        Page<FeedComment> feedComments = feedCommentRepository.findAllByFeedFeedSeq(feedSeq,page);
        List<FeedCommentDto> feedCommentDtos = new ArrayList<>();
        feedComments.forEach(
                feedComment -> feedCommentDtos.add(FeedCommentDto.builder()
                        .feedSeq(feedSeq)
                        .user(new SimpleUserVO(feedComment.getUser()))
                        .comment(feedComment.getComment())
                        .activated(feedComment.isActivated())
                        .parentCommentSeq(feedComment.getFeedCommentSeq())
                        .feedCommentSeq(feedComment.getFeedCommentSeq())
                        .lastModifiedDate(feedComment.getModifiedDate())
                        .createdDate(feedComment.getCreatedDate())
                        .build()));
        return new PageImpl<>(feedCommentDtos,page,feedComments.getTotalElements());
    }

    @Transactional
    public FeedCommentDto saveFeedComment(FeedCommentRegisterDto feedCommentRegisterDto) {
        User user = userRepository.findById(feedCommentRegisterDto.getUserSeq()).get();
        Feed feed = feedRepository.findOneByFeedSeq(feedCommentRegisterDto.getFeedSeq());
        FeedComment feedComment = new FeedComment(feedCommentRegisterDto,feed,user);
        Long feedCommentSeq = feedCommentRepository.save(feedComment).getFeedCommentSeq();
        SimpleUserVO simpleUserVO = new SimpleUserVO(userRepository.findById(feedCommentRegisterDto.getUserSeq()).get());
        return FeedCommentDto.builder()
                .feedCommentSeq(feedCommentSeq)
                .feedSeq(feedCommentRegisterDto.getFeedSeq())
                .user(simpleUserVO)
                .comment(feedComment.getComment())
                .activated(feedComment.isActivated())
                .parentCommentSeq(feedComment.getParentCommentSeq())
                .lastModifiedDate(feedComment.getModifiedDate())
                .createdDate(feedComment.getCreatedDate())
                .build();
    }

    @Transactional
    public FeedCommentDto updateFeedCommentInActivated(Long feedCommentSeq) {
        FeedComment feedComment = feedCommentRepository.findOneByFeedCommentSeq(feedCommentSeq);
        feedComment.setActivated(false);
        return FeedCommentDto.builder()
                .feedCommentSeq(feedCommentSeq)
                .feedSeq(feedComment.getFeed().getFeedSeq())
                .comment(feedComment.getComment())
                .activated(feedComment.isActivated())
                .parentCommentSeq(feedComment.getParentCommentSeq())
                .lastModifiedDate(feedComment.getModifiedDate())
                .createdDate(feedComment.getCreatedDate())
                .build();
    }

    @Transactional
    public FeedCommentDto updateFeedComment(FeedCommentUpdateDto feedCommentUpdateDto) {
        FeedComment feedComment = feedCommentRepository.findOneByFeedCommentSeq(feedCommentUpdateDto.getFeedCommentSeq());
        feedComment.setComment(feedCommentUpdateDto.getComment());
        return FeedCommentDto.builder()
                .feedCommentSeq(feedCommentUpdateDto.getFeedCommentSeq())
                .feedSeq(feedComment.getFeed().getFeedSeq())
                .comment(feedComment.getComment())
                .activated(feedComment.isActivated())
                .parentCommentSeq(feedComment.getParentCommentSeq())
                .lastModifiedDate(feedComment.getModifiedDate())
                .createdDate(feedComment.getCreatedDate())
                .build();
    }
}
