package com.cloud.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Optional;

/**
 * @Description: SHA1加密工具
 * @Param:
 * @return:
 * @Author: wangliang
 * @Date: 2019/10/11
 */
@Slf4j
public class Sha1Util {

    public static String encoder(String text) {
        text = Optional.of(text).get();
        log.info("SHA1加密前的文本:{}", text);
        String encodeStr = DigestUtils.sha1Hex(text);
        log.info("SHA1加密后的文本:{}", encodeStr);
        return encodeStr;
    }

    /**
     * MD5校验
     *
     * @param text
     * @param sha1
     * @return
     * @throws Exception
     */
    public static boolean verify(String text, String sha1) {
        text = Optional.of(text).get();
        sha1 = Optional.of(sha1).get();
        //根据传入的密钥进行验证
        String sha1Text = encoder(text);
        if (sha1Text.equalsIgnoreCase(sha1)) {
            log.info("SHA1验证通过");
            return true;
        }
        return false;
    }
}
