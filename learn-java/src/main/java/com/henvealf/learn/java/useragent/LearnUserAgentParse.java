package com.henvealf.learn.java.useragent;

import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;
import cz.mallat.uasparser.UserAgentInfo;

import java.io.IOException;

/**
 * @author hongliang.yin/Henvealf
 * @date 2019-08-07
 */
public class LearnUserAgentParse {

    public static void main(String[] args)
    {
        String str = "python-requests/2.22.0";
//        String str = "Mozilla/5.0 (iPhone; CPU iPhone OS 7_0 like Mac OS X; en-us) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53";
//        String str = "Mozilla/5.0 (Linux; U; Android 4.4.2; zh-cn; PE-TL20 Build/HuaweiPE-TL20) AppleWebKit/537.36 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.3 Mobile Safari/537.36";


        System.out.println(str);
        try {
//
            UASparser uasParser = new UASparser(OnlineUpdater.getVendoredInputStream());
            UserAgentInfo userAgentInfo = uasParser.parse(str);
            System.out.println("操作系统名称："+userAgentInfo.getOsFamily());//
            System.out.println("操作系统："+userAgentInfo.getOsName());//
            System.out.println("浏览器名称："+userAgentInfo.getUaFamily());//
            System.out.println("浏览器版本："+userAgentInfo.getBrowserVersionInfo());//
            System.out.println("设备类型："+userAgentInfo.getDeviceType());
            System.out.println("浏览器:"+userAgentInfo.getUaName());
            System.out.println("类型："+userAgentInfo.getType());
            System.out.println("------");
            System.out.println(userAgentInfo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // type
    }
}
