package cn.edu.nuaa.burning.model;

import lombok.Data;

import java.util.Calendar;
import java.util.List;

/**
 * Created by zxh on 2016/3/23.
 */

@Data
public class Article {
    private String id;
    private String title;
    private String userId;
    private Calendar createTime;
    private Calendar modifyTime;
    private String category;
    private List<String> tag;
    private List<String> comment;
    private List<String> like;
}
