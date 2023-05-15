package com.thuanthanh.StudentProject.Entity;

import org.springframework.cglib.core.internal.LoadingCache;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TOTP {
    private static final Integer PASSWORD_LENGTH = 6;
    private static final int TIME_STEP = 30;
    public static String generateOTP() {
//        byte[] bytes = Base64.getEncoder().encode(secret.getBytes(StandardCharsets.UTF_8));
//        SecretKeySpec keySpec = new SecretKeySpec(bytes,"HmacSHA1");
//        Mac mac = Mac.getInstance("HmacSHA1");
//        long time = Instant.now().getEpochSecond()/TIME_SET;
//        byte[] data = ByteBuffer.allocate(8).putLong(time).array();
//        mac.init(keySpec);
//        byte[] hash = mac.doFinal(data);
//        int offset = hash[hash.length-1] & 0xF;
//        int binary =
//                ((hash[offset] & 0x7f) << 24) |
//                        ((hash[offset + 1] & 0xff) << 16) |
//                        ((hash[offset + 2] & 0xff) << 8) |
//                        (hash[offset + 3] & 0xff);
//        int otp = (int) (binary % Math.pow(10,PASSWORD_LENGTH));
//        return String.format("%06d",otp);
        StringBuilder genarateOTP = new StringBuilder();
        SecureRandom secureRandom =  new SecureRandom();
        try {
            secureRandom = SecureRandom.getInstance(secureRandom.getAlgorithm());
            for (int i =0; i<PASSWORD_LENGTH;i++){
                genarateOTP.append(secureRandom.nextInt(10)); // random so tu 0 den 9
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return genarateOTP.toString();
    }
    public long getOtpExpiredAt(){
        long currentTime = new Date().getTime()+ TimeUnit.MINUTES.toMillis(TIME_STEP);
        return currentTime;
    }
}
