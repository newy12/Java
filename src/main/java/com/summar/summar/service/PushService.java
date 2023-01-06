package com.summar.summar.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.summar.summar.dto.Notification;
import com.summar.summar.dto.PushRequestDto;
import jdk.nashorn.internal.parser.JSONParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;


@Service
@Slf4j
public class PushService {

    @Value("${push.server-key}")
    private String serverKey;

    public void pushNotification(){
        try{
            ObjectMapper mapper = new ObjectMapper();
            PushRequestDto pushRequestDto = new PushRequestDto();
            Notification notification = new Notification();
            notification.setBody("body");
            notification.setTitle("title");
            pushRequestDto.setTo("e8JYHSQQyUabgu1g55CCNK:APA91bEh752UcgpOy8NYF41LC6aQ0g3PSvKqrlONWfS_S6Z9tIBmKbxAY5oQNOOMK4pZqxvs-Rl6yNFgdDS8R3w6t2wE-PXmvqZ0H8Fc-3SdliQvHUoHLpwRcfXf9rJ57aueioviqSJA");
            pushRequestDto.setPriority("high");
            pushRequestDto.setNotification(notification);
            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
            factory.setConnectTimeout(5000); //타임아웃 설정 5초
            factory.setReadTimeout(5000);//타임아웃 설정 5초

            RestTemplate restTemplate = new RestTemplate(factory);
            HttpHeaders header = new HttpHeaders();
            header.add("Content-Type","application/json");
            header.add("Authorization",serverKey);

            String url = "https://fcm.googleapis.com/fcm/send"; //외부 api

            String pushRequedtDtoJson = mapper.writeValueAsString(pushRequestDto);

            /*MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            jsonObject.get(pushRequestDto.getTo()).getAsJsonObject();
            jsonObject.get(pushRequestDto.getPriority()).getAsJsonObject();
            mapper.writeValueAsString(pushRequestDto.getNotification());
            body.add("to",pushRequestDto.getTo());
            body.add("priority",pushRequestDto.getPriority());
            body.add("notification",pushRequestDto.getNotification());*/

            HttpEntity<String> request = new HttpEntity<>(pushRequedtDtoJson, header);

            String result = restTemplate.postForObject(new URL(url).toURI(),request,String.class);
            log.info("result>>>>>>>>>>> : {}",result);
        } catch (MalformedURLException | URISyntaxException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }

}
