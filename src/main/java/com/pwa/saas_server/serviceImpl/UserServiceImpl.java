package com.pwa.saas_server.serviceImpl;

import com.pwa.saas_server.data.base.Result;
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
    public UserBean getUsersByUsername(String username) {
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
    public Result<String> insertUser(UserBean userBean) {
        try {
            userMapper.insertUser(userBean);
        } catch(org.springframework.dao.DuplicateKeyException e) {
            System.out.println("UserServiceImple insertUser get the Exception!!!");
            System.out.println(e.getClass().getName());
            return Result.error("注册失败，该用户名已存在，请换一个新的用户名！");
        }
        return Result.success("register " + userBean.getUsername() + " successful!");
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
