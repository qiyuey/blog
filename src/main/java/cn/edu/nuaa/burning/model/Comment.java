package cn.edu.nuaa.burning.model;

import lombok.Data;

/**
 * 评论类
 */
@Data
public class Comment {
    private String content;     // 评论内容
    private String fromUser;    // 评论者
    private String toUser;      // 被回复者
}
