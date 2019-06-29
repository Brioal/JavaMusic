package com.brioal.music.util;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.text.DecimalFormat;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

/**
 * 一些常用的转换工具类
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioa on 2018/10/23.
 */

public class ConvertUtil {



    /**
     * 格式化小数
     *
     * @param d
     * @return
     */
    public static String formatDouble4(double d) {
        if (d == 0) {
            return "0";
        }
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(d);
    }

    // 转换需要的参数到HashMap
    public static HashMap<String, Object> returnNeedValue(Object object, String[] methods) {
        if (object == null) {
            return new HashMap<>();
        }
        HashMap<String, Object> map = null;
        try {
            Class<?> userClass = Class.forName(object.getClass().getName());
            map = new HashMap<>();
            // 循环参数
            for (int i = 0; i < methods.length; i++) {
                String methodName = methods[i];
                String getName = methodName;
                // 首字母大写
                getName = (new StringBuilder()).append(Character.toUpperCase(getName.charAt(0))).append(getName.substring(1)).toString();
                // 获取get方法
                try {
                    Method method = userClass.getMethod("get" + getName);//得到方法对象
                    Object value = method.invoke(object);
                    map.put(methodName, value);
                } catch (Exception e) {
                }
                // 获取is 方法
                try {
                    Method method = userClass.getMethod("is" + getName);//得到方法对象
                    Object value = method.invoke(object);
                    map.put(methodName, value);
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 返回一个查看所有页面的分页参数
     *
     * @return
     */
    public static Pageable getFullPageRequest() {
        return PageRequest.of(0, Integer.MAX_VALUE);
    }

    /**
     * 返回ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            // 尝试获取ip字段
            ipAddress = request.getHeader("ip");
            if (TextUtil.isStringAvailableAddNotNull(ipAddress)) {
                return ipAddress;
            }
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) {
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        ipAddress = request.getRemoteAddr();
        return ipAddress;
    }

    /**
     * 返回用户角色的审核状态
     *
     * @param state
     * @return
     */
    public static String getUserAllowStateStr(int state) {
        switch (state) {
            case 0:
                return "待审核";
            case 1:
                return "正在审核";
            case 2:
                return "通过";
            case 3:
                return "未通过";
        }
        return "";
    }
}
