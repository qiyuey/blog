package cn.edu.nuaa.burning.domain.request;

import lombok.Data;

/**
 * qiyuey on 2016/4/10.
 */
@Data
public class RegisterReq {

    private String username;
    private String password;
    private String email;
}
