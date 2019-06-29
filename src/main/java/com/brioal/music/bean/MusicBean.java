package com.brioal.music.bean;

import com.brioal.music.bean.tool.EntityBean;
import com.brioal.music.bean.tool.FileBean;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

/**
 * 项目音乐实体
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioa on 2019/6/29.
 */
@Getter
@Setter
@Entity
public class MusicBean extends EntityBean {
    // 歌曲文件
    @JoinColumn
    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private FileBean song;

    // 封面文件
    @JoinColumn
    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private FileBean cover;

    // 歌词
    @JoinColumn
    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private FileBean lrc;

    // 名称
    private String title;
    // 作者
    private String author;
    // 播放长度
    private String timeLength;
    // 专辑名称
    private String album;
    // 格式
    private String format;
    // 清晰度
    private String rate;

}
