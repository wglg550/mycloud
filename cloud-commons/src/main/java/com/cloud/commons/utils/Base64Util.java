package com.cloud.commons.utils;

import com.cloud.commons.constant.SystemConstant;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Optional;

/**
 * @Description: Base64工具类
 * @Param:
 * @return:
 * @Author: wangliang
 * @Date: 2019/10/11
 */
@Slf4j
public class Base64Util {

    /**
     * Base64加密
     *
     * @param text
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encoder(String text) throws UnsupportedEncodingException {
        Optional<String> optional = Optional.of(text);
        text = optional.get();
        Base64.Encoder encoder = Base64.getEncoder();
        log.info("Base64加密前的文本：{}", text);
        byte[] textByte = text.getBytes(SystemConstant.ENCODING);
        String encodedText = encoder.encodeToString(textByte);
        log.info("Base64加密后的文本：{}", encodedText);
        return encodedText;
    }

    /**
     * Base64解密
     *
     * @param text
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decoder(String text) throws UnsupportedEncodingException {
        Optional<String> optional = Optional.of(text);
        text = optional.get();
        Base64.Decoder decoder = Base64.getDecoder();
        String decoderText = new String(decoder.decode(text), SystemConstant.ENCODING);
        log.info("Base64解密后的文本：{}", decoderText);
        return decoderText;
    }
}
