package com.pwa.saas_server.serviceImpl;

import com.pwa.saas_server.data.bean.user.UserBean;
import com.pwa.saas_server.mapper.UserMapper;
import com.pwa.saas_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jere
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserBean> getAllUsers() {
        return userMapper.selectAllUsers();
    }

    @Override
    public List<UserBean> getUsersByUsername(String username) {
        return userMapper.selectUsersByUsername(username);
    }

    @Override
    public UserBean getUserById(int userId) {
        return userMapper.selectUserById(userId);
    }

    @Override
    public UserBean getUserByNameAndPwd(String username, String encPwd) {
        return userMapper.selectUserByNameAndPwd(username, encPwd);
    }

    @Override
    public void insertUser(UserBean userBean) {
        userMapper.insertUser(userBean);
    }

    @Override
    public void updateUser(UserBean userBean) {
        userMapper.updateUser(userBean);
    }

    @Override
    public void deleteUserByUserId(int userId) {
        userMapper.deleteUserByUserId(userId);
    }
}
