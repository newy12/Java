package com.summar.summar.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.summar.summar.common.SummarCommonException;
import com.summar.summar.common.SummarErrorCode;
import com.summar.summar.domain.User;
import com.summar.summar.dto.Notification;
import com.summar.summar.dto.PushNotificationDto;
import com.summar.summar.dto.PushRequestDto;
import com.summar.summar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;


@Service
@Slf4j
@RequiredArgsConstructor
public class PushService {

    private final UserRepository userRepository;
    @Value("${push.server-key}")
    private String serverKey;

    public void pushNotification(PushNotificationDto pushNotificationDto) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Notification notification = Notification.builder()
                    .title(pushNotificationDto.getTitle())
                    .body(pushNotificationDto.getBody())
                    .build();

            //device token 정보 찾기
            User user = userRepository.findByUserNickname(pushNotificationDto.getUserNickname()).orElseThrow(() -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));

            PushRequestDto pushRequestDto = PushRequestDto.builder()
                    .to(user.getDeviceToken())  //해당기기 디바이스 토큰
                    .notification(notification) //알림 - 제목,내용
                    .build();
            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
            factory.setConnectTimeout(5000); //타임아웃 설정 5초
            factory.setReadTimeout(5000);//타임아웃 설정 5초

            RestTemplate restTemplate = new RestTemplate(factory);
            HttpHeaders header = new HttpHeaders();
            header.add("Content-Type", "application/json");
            header.add("Authorization", serverKey);

            String url = "https://fcm.googleapis.com/fcm/send"; //외부 api

            String pushRequedtDtoJson = mapper.writeValueAsString(pushRequestDto);

            HttpEntity<String> request = new HttpEntity<>(pushRequedtDtoJson, header);

            String result = restTemplate.postForObject(new URL(url).toURI(), request, String.class);
            log.info("push-Notification result : {}", result);
        } catch (MalformedURLException | URISyntaxException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }

}
