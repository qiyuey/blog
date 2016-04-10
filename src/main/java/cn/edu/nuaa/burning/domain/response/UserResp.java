package cn.edu.nuaa.burning.domain.response;

import cn.edu.nuaa.burning.entity.User;
import lombok.Data;

/**
 * qiyuey on 2016/4/8.
 */
@Data
public class UserResp {

    private String id;
    private String username;
    private String email;
    private String nickname;
    private String photo;
    private String signature;

    public UserResp(User user) {
        id = user.getId();
        username = user.getUsername();
        email = user.getEmail();
        nickname = user.getNickname();
        photo = user.getPhoto();
        signature = user.getSignature();
    }
}
