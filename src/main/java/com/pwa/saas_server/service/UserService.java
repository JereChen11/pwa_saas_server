package com.pwa.saas_server.service;

import com.pwa.saas_server.data.base.Result;
import com.pwa.saas_server.data.bean.user.UserBean;

import java.util.List;

/**
 * @author jere
 */
public interface UserService {

    List<UserBean> getAllUsers();

    /**
     * 根据username进行查询
     * @param username UNIQUE属性，所以username是唯一的，用户表中不存在两个相同的username
     * @return UserBean
     */
    UserBean getUsersByUsername(String username);

    UserBean getUserById(int userId);

    UserBean getUserByNameAndPwd(String username, String encPwd);

    Result<String> insertUser(UserBean userBean);

    void updateUser(UserBean userBean);

    void deleteUserByUserId(int userId);

}
