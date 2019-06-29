package com.brioal.music.bean.tool;


import org.springframework.data.domain.PageRequest;

import java.sql.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 用于疯转请求参数的bean,包含基本的常用的查询参数,如果需要自定义独特的参数,用继承
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioa on 2018/12/24.
 */
@Setter
@Getter
public class PostDataBean {
    // 当前页数
    private int page = 0;
    // 每页的大小
    private int size = 10;
    // 状态
    private int state;
    // 排序方式
    private String sort;
    // 是否是降序
    private boolean desc = true;
    // 关键字
    private String key;
    // 方便上传参数,定义的value1
    int intValue1;
    int intValue2;
    int intValue3;
    int intValue4;
    int intValue5;
    int intValue6;
    int intValue7;

    String strValue1;
    String strValue2;
    String strValue3;
    String strValue4;


    boolean boolValue1;
    boolean boolValue2;
    boolean boolValue3;

    double doubleValue1;

    List<Integer> intList;
    List<EntityBean> list1;
    List<EntityBean> list2;

    private Timestamp time1;
    private Timestamp time2;


    public PageRequest getRequest() {
        return PageRequest.of(page, size);
    }
}
