package com.pwa.saas_server.data.bean.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author jere
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserBean {
    private int userId;
    private String username;
    private String encPwd;
    private String referer;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

    public UserBean(String username, String encPwd, String referer) {
        this.username = username;
        this.encPwd = encPwd;
        this.referer = referer;
    }
}
