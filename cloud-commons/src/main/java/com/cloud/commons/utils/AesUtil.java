package com.cloud.commons.utils;

import com.cloud.commons.constant.SystemConstant;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @Description: AES对称加密
 * @Param:
 * @return:
 * @Author: wangliang
 * @Date: 2019/10/11
 */
@Slf4j
public class AesUtil {

    /**
     * 加密
     *
     * @param content
     * @param strKey
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(String content, String strKey) throws Exception {
        SecretKeySpec skeySpec = getKey(strKey);
        Cipher cipher = Cipher.getInstance(SystemConstant.AES_MODE);
        IvParameterSpec iv = new IvParameterSpec(SystemConstant.AES_RULE.getBytes());//AES规则，可自定义，例如A-Z排序
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        return cipher.doFinal(content.getBytes());
    }

    /**
     * 解密
     *
     * @param content
     * @param strKey
     * @return
     * @throws Exception
     */
    private static String decrypt(byte[] content, String strKey) throws Exception {
        SecretKeySpec skeySpec = getKey(strKey);
        Cipher cipher = Cipher.getInstance(SystemConstant.AES_MODE);
        IvParameterSpec iv = new IvParameterSpec(SystemConstant.AES_RULE.getBytes());//AES规则，可自定义，例如A-Z排序
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] original = cipher.doFinal(content);
        return new String(original);
    }

    /**
     * 公钥
     *
     * @param strKey
     * @return
     * @throws Exception
     */
    private static SecretKeySpec getKey(String strKey) throws Exception {
        byte[] arrBTmp = strKey.getBytes();
        byte[] arrB = new byte[16]; // 创建一个空的16位字节数组（默认值为0）
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        return new SecretKeySpec(arrB, SystemConstant.AES);
    }

    /**
     * AES加密
     *
     * @param content
     * @param encryptKey
     * @return
     * @throws Exception
     */
    public static String encoder(String content, String encryptKey) throws Exception {
        log.info("AES加密前的内容:{},key:{}", content, encryptKey);
        String encoderText = Base64.getEncoder().encodeToString(encrypt(content, encryptKey));
        log.info("AES加密后的文本:{}", encoderText);
        return encoderText;
    }

    /**
     * AES解密
     *
     * @param encryptStr
     * @param decryptKey
     * @return
     * @throws Exception
     */
    public static String decoder(String encryptStr, String decryptKey) throws Exception {
        log.info("AES解密前的内容:{},key:{}", encryptStr, decryptKey);
        String decoderText = decrypt(Base64.getDecoder().decode(encryptStr), decryptKey);
        log.info("AES解密后的文本:{}", decoderText);
        return decoderText;
    }
}
