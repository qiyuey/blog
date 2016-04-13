package cn.edu.nuaa.burning.model;

import lombok.Data;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zxh on 2016/3/23.
 */

@Data
/**
 * 定义博文类
 */
public class Article {
    private String id;
    private String title;
    private String userId;
    private Date createTime;
    private Date modifyTime;
    private String category;
    private List<String> tag;
    private List<String> comment;
    private List<String> like;
}
