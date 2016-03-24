package cn.edu.nuaa.burning.model;

import java.util.Calendar;
import java.util.List;

import lombok.Data;

/**
 * 文章实体类
 */
@Data
public class Article {
    private String id;                  // id
    private String title;               // 标题
    private String userId;              // 用户id
    private Calendar createTime;        // 创建时间
    private Calendar modifyTime;        // 最后一次修改时间
    private String categoryId;          // 分类id
    private List<String> tags;          // 标签,id列表
    private List<Comment> comments;     // 评论,id列表
    private List<String> likes;         // 喜欢,id列表
}
