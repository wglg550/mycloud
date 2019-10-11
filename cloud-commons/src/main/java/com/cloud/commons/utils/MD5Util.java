package com.cloud.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Optional;

/**
 * @Description: MD5加密工具类
 * @Param:
 * @return:
 * @Author: wangliang
 * @Date: 2019/10/11
 */
@Slf4j
public class MD5Util {

    /**
     * MD5加密
     *
     * @param text
     * @return
     * @throws Exception
     */
    public static String encoder(String text) {
        text = Optional.of(text).get();
        log.info("MD5加密前的文本:{}", text);
        String encodeStr = DigestUtils.md5Hex(text);
        log.info("MD5加密后的文本:{}", encodeStr);
        return encodeStr;
    }

    /**
     * MD5校验
     *
     * @param text
     * @param md5
     * @return
     * @throws Exception
     */
    public static boolean verify(String text, String md5) {
        text = Optional.of(text).get();
        md5 = Optional.of(md5).get();
        //根据传入的密钥进行验证
        String md5Text = encoder(text);
        if (md5Text.equalsIgnoreCase(md5)) {
            log.info("MD5验证通过");
            return true;
        }
        return false;
    }
}
