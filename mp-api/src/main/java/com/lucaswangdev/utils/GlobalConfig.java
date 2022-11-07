package com.lucaswangdev.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GlobalConfig {
    // 微信小程序 appid
    public static String appid;

    // 微信小程序 secret
    public static String secret;

    public static String getAppid() {
        return appid;
    }

    // 一定是在非静态方法setAppid前使用@Value注解
    // 原文链接：https://blog.csdn.net/dadaoke/article/details/79460677
    @Value("${wechat.appid}")
    public void setAppid(String appid) {
        GlobalConfig.appid = appid;
    }

    public static String getSecret() {
        return secret;
    }

    // 一定是在非静态方法setAppid前使用@Value注解
    @Value("${wechat.secret}")
    public void setSecret(String secret) {
        GlobalConfig.secret = secret;
    }
}