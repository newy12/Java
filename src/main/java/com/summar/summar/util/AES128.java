package com.summar.summar.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.context.annotation.Configuration;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;


@Slf4j
@Configuration
public class AES128 {
    private static String ips;
    private static Key keySpec;

    public AES128(String key) {
        try {
            byte[] keyBytes = new byte[16];
            byte[] b = key.getBytes("UTF-8");
            System.arraycopy(b, 0, keyBytes, 0, keyBytes.length);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            this.ips = key.substring(0, 16);
            this.keySpec = keySpec;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String str) {
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec,
                    new IvParameterSpec(ips.getBytes()));

            byte[] encrypted = cipher.doFinal(str.getBytes("UTF-8"));
            String Str = new String(Base64.encodeBase64(encrypted));

            return Str;
        } catch (Exception e) {
            return null;
        }
    }

    public static String decrypt(String str) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec,
                    new IvParameterSpec(ips.getBytes("UTF-8")));

            byte[] byteStr = Base64.decodeBase64(str.getBytes());
            String Str = new String(cipher.doFinal(byteStr), "UTF-8");

            return Str;
        } catch (Exception e) {
            return null;
        }
    }
}
