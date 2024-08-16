package com.pwa.saas_server.mapper;

import com.pwa.saas_server.data.bean.user.UserBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author jere
 */
@Mapper
public interface UserMapper {
    List<UserBean> selectAllUsers();

    List<UserBean> selectUsersByUsername(String username);

    UserBean selectUserById(int userId);

    UserBean selectUserByNameAndPwd(String username, String encPwd);

    void insertUser(UserBean userBean);

    void updateUser(UserBean userBean);

    void deleteUserByUserId(int userId);
}
