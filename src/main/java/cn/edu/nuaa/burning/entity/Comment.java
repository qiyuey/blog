package cn.edu.nuaa.burning.entity;

import lombok.Data;

import java.util.Date;

/**
 * 评论类
 */
@Data
public class Comment {

    private String content;       // 评论内容
    private String fromUserId;    // 评论者
    private String toUserId;      // 被回复者
    private Date createTime;    // 创建时间
}
