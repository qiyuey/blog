package cn.edu.nuaa.burning.domain.response;

import cn.edu.nuaa.burning.entity.User;
import lombok.Data;

/**
 * qiyuey on 2016/4/8.
 */
@Data
public class UserResp {

    private String id;
    private String email;
    private String nickname;
    private Integer age;
    private String photo;
    private String signature;

    public UserResp(User user) {
        id = user.getId();
        email = user.getEmail();
        nickname = user.getNickname();
        age = user.getAge();
        photo = user.getPhoto();
        signature = user.getSignature();
    }
}
