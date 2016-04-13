package cn.edu.nuaa.burning.domain.request;

import lombok.Data;

/**
 * qiyuey on 2016/4/10.
 */
@Data
public class UserReq {

    private String password;
    private String email;
    private Integer age;
    private String nickname;
    private String photo;
    private String signature;
}
