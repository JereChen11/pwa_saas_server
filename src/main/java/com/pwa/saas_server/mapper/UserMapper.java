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

    /**
     * 根据username进行查询
     * @param username UNIQUE属性，所以username是唯一的，用户表中不存在两个相同的username
     * @return UserBean
     */
    UserBean selectUsersByUsername(String username);

    UserBean selectUserById(int userId);

    UserBean selectUserByNameAndPwd(String username, String encPwd);

    void insertUser(UserBean userBean);

    void updateUser(UserBean userBean);

    void deleteUserByUserId(int userId);
}
