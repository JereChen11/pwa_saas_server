package com.pwa.saas_server.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @author jere
 */
public class AesTool {
    private static final String SECRET_KEY = "NQ14J4+FwKEMJxH5zYSnrw==";

    public static void main(String[] args) throws Exception {
        String originalText = "Hello, world!";

        // 加密
        String encryptedText = encrypt(originalText);
        System.out.println("加密后的文本：" + encryptedText);

        // 解密
        String decryptedText = decrypt("bJXv4LD35ojS4qjYhEyKkMHVjukw2iCIwB4MlsYr7fdlewcBRTKNXwaagLI8RYW3vNIBg1s3R0vqLcx8gLVrFFC6IfRc/RvnmHLAR7+Z4YaId4AdxH3+Oz7xvW79Yo2+J+bueC9Rkcxac4t6MnVsT8qrbEVSnlmsinCnRmRi0asbdoURx5H/0rR87VgsaeFyAuBG27AsfgjWEuKLhMfMksPe1EfKBPWDJjKTJhf9HjHIwt8aiAtZJSLSksT+nGCUeyTFSgsxmS6EAWjy8OGfrxsNP0HSm6X9FevKRhQ9pBTAYiiKrRdBA+1uzDZVMqiR+Sr2SogsOdtSRoX1UtyJVBa2UPFs0fhVbO+Nn35x2wIUfhr2P6zOAbKg9TohSVGAPY8Cv23G6zLukISygPz2iUIQy1cZzmbQe9bxaG7amj10diqvmldWXmrEmHA1AuIymu5GzJXN1ewf5Aq9BLgG4N9y8fOaDnhQsjZq5naGpQ3XsjcjXK1u4bThvafROLLYwgi86dUsJh/BuCdMOnC4b+aErYoumgE6meCNg0WaFISqZwXrweOfO6e3b0API/ubv1mbSOEcxOSQHTirXJSqcd3Ow0QaDlS9smhbJI6uMHzjWPVdbVSRc5RUkHB/VSOwDIvmmc7PtcYreQflynB2P1W//iuzx34W8E9Za2FMjuhtarZyBWCo4C10gIzz0nYDcawpbKm4MryLPtJuz/2vW04hHcwUZN5XLjcY4G28Tnpt+XvVewTdibc+7YENJWsT7P52wnrHlwjIf5yEMqmNHrS1k/U+JaRhRNFEdB5vmW0=");
        System.out.println("解密后的文本：" + decryptedText);
    }

    // 加密方法
    public static String encrypt(String strToEncrypt) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = cipher.doFinal(strToEncrypt.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // 解密方法
    public static String decrypt(String strToDecrypt) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(strToDecrypt));
        return new String(decryptedBytes);
    }
}
