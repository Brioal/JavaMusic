package com.brioal.music.bean.tool;


import com.brioal.music.util.ReviewFileUtils;

import java.io.File;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioa on 2018/10/11.
 */
@Entity
@Getter
@Setter
public class FileBean extends EntityBean {

    // 文件原名称
    private String name;

    // 文件路径
    private String path;

    // 缩略图路径
    private String thumbPath;

    // 文件大小
    private String size;


    // 是否是临时文件
    private boolean temp = false;


    public File getLocalFile() {
        String path = ReviewFileUtils.getAbsolutePathForFileBean(this);
        System.out.println(path);
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        return file;
    }



}
