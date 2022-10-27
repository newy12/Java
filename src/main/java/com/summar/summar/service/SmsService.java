package com.summar.summar.service;

import com.summar.summar.dto.SmsRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsService {

    final DefaultMessageService defaultMessageService;

    public SmsService() {
        this.defaultMessageService = NurigoApp.INSTANCE.initialize("NCSDSJDNIALLOOF0", "2TOIAXY6SXFHDHF5637A4OCBZAJXNIPQ", "https://api.coolsms.co.kr");
    }
    public String send(SmsRequestDto smsRequestDto) {
        String accessCode = RandomStringUtils.randomNumeric(4);
        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom("01088311502");
        //message.setTo(smsRequestDto.getUserHpNo());
        message.setTo("01057212058");
        message.setText("써머(Summar) 인증번호는 ["+accessCode+"] 입니다.");

        SingleMessageSentResponse result = this.defaultMessageService.sendOne(new SingleMessageSendingRequest(message));

        log.info("result : {}",result);
        return accessCode;

    }
}
