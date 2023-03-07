package com.summar.summar.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Slf4j
@Configuration
public class SHA256Util {

    public static String encrypt(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes());

        return bytesToHex(md.digest());
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
