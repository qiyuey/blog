package cn.edu.nuaa.burning.model;

import lombok.Data;

import java.util.List;

/**
 * 用户实体类
 */
@Data
public class User {
    private String id;                  // id
    private String username;            // 用户名
    private String password;            // 密码
    private String email;               // 电子邮件
    private Integer age;                // 年龄
    private String nickname;            // 昵称,可修改
    private String photo;               // 图片id
    private String signature;           // 个性签名
    private List<String> categories;    // 分类id
    private List<String> collections;   // 收藏,文章id
}
