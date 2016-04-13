package cn.edu.nuaa.burning.entity;

import lombok.Data;

import java.util.HashSet;
import java.util.List;

/**
 * 用户实体类
 */
@Data
public class User {

    private String id;                    // id
    private String password;            // 密码
    private String email;               // 电子邮件
    private Integer age;                // 年龄
    private String nickname;            // 昵称,可修改
    private String photo;               // 图片id
    private String signature;           // 个性签名
    private HashSet<String> categories;      // 分类id
    private HashSet<String> collections;     // 收藏,文章id
}
