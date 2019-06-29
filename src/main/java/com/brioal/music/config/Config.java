package com.brioal.music.config;

import java.io.File;

/**
 * 参数配置i文件
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by Brioal on 2018/4/20.
 */

public class Config {

    /**
     * 项目的主目录
     */
    public static String PROJECT_DIR = "/Music";

    /**
     * 返回文件全路径
     *
     * @param path
     * @return
     */
    public static String getFullLocalPath(String path) {
        return PROJECT_DIR + File.separator + path;
    }

}
