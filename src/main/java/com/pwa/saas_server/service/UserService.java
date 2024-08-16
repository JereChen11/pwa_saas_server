package com.pwa.saas_server.service;

import com.pwa.saas_server.data.bean.user.UserBean;

import java.util.List;

/**
 * @author jere
 */
public interface UserService {

    List<UserBean> getAllUsers();

    List<UserBean> getUsersByUsername(String username);

    UserBean getUserById(int userId);

    UserBean getUserByNameAndPwd(String username, String encPwd);

    void insertUser(UserBean userBean);

    void updateUser(UserBean userBean);

    void deleteUserByUserId(int userId);

}
