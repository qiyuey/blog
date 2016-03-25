package cn.edu.nuaa.burning.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 文章实体类
 */
@Data
public class Article {

    private Long id;                    // id
    private String title;               // 标题
    private Long userId;                // 用户id
    private Date createTime;            // 创建时间
    private Date modifyTime;            // 最后一次修改时间
    private Long categoryId;            // 分类id
    private List<Long> tags;            // 标签,id列表
    private List<Comment> comments;     // 评论,评论id列表
    private List<Long> likes;           // 喜欢,用户id列表
}
