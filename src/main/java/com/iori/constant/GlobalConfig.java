package com.iori.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GlobalConfig {

    public GlobalConfig() {
    }

    public static  String APPID = "";
    public static  String APPSECRET = ""; // appsecret
    public static  String MCH_ID = ""; // 商业号
    public static  String KEY = ""; // key
    public static  String URL = "";
    public static  String TRADE_TYPE = "";

    @Value("${wx.APPID}")
    public void setAPPID(String APPID) {
        GlobalConfig.APPID = APPID;
    }

    @Value("${wx.APPSECRET}")
    public void setAPPSECRET(String APPSECRET) {
        GlobalConfig.APPSECRET = APPSECRET;
    }

    @Value("${wx.MCH_ID}")
    public void setMchId(String mchId) {
        GlobalConfig.MCH_ID = mchId;
    }

    @Value("${wx.KEY}")
    public void setKEY(String KEY) {
        GlobalConfig.KEY = KEY;
    }

    @Value("${wx.URL}")
    public void setURL(String URL) {
        GlobalConfig.URL = URL;
    }

    @Value("${wx.TRADE_TYPE}")
    public void setTradeType(String tradeType) {
        GlobalConfig.TRADE_TYPE = tradeType;
    }
}
