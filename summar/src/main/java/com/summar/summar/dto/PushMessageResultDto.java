package com.summar.summar.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PushMessageResultDto implements Serializable {



    private String multicast_id;
    private boolean success;
    private boolean failure;
    private String canonical_ids;
    private List<MessageInfo> results;


    public static class MessageInfo{

        String message_id;

        public String getMessage_id() {
            return message_id;
        }

        public void setMessage_id(String message_id) {
            this.message_id = message_id;
        }
    }


}
