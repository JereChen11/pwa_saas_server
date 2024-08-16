package com.pwa.saas_server.controller;

import com.pwa.saas_server.data.base.Result;
import com.pwa.saas_server.data.base.ResultCode;
import com.pwa.saas_server.data.bean.user.UserBean;
import com.pwa.saas_server.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jere
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<String> register(@RequestParam("username") String username,
                                   @RequestParam("password") String password,
                                   @RequestParam("referer") String referer) {
        logger.info("register username: " + username + ", password: " + password + ", referer = " + referer);

        if (password.length() < 6) {
            return Result.error("Please enter a password with more than 6 digits");
        }

        UserBean userBean = new UserBean(username, password, referer);
        userService.insertUser(userBean);

        return Result.success("register success.");
    }


    @GetMapping("/login")
    public Result<String> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        logger.info("login username = " + username + ", password = " + password);
        if (username.isBlank() || password.isBlank()) {
            return Result.error(ResultCode.PARAM_ERROR);
        }
        UserBean userBean = userService.getUserByNameAndPwd(username, password);
        if (userBean == null) {
            return Result.error("登入失败");
        }
        return Result.success("登入成功");
    }

//    @PostMapping("/logout")
//    public Result<String> logout(@TokenToUser UserBean userBean) {
//        String resultMsg = "logout success.";
//        if (!userService.logout(userBean.getUserId())) {
//            resultMsg = "logout failed.";
//        }
//        return Result.success(resultMsg);
//    }

    @GetMapping("/getAllUsers")
    public Result<List<UserBean>> getAllUsers() {
        List<UserBean> userBeanList = userService.getAllUsers();
        return Result.success(userBeanList);
    }

    @GetMapping("/getUserById")
    public Result<UserBean> getUserById(@RequestParam("userId") int userId) {
        UserBean userBean = userService.getUserById(userId);
        return Result.success(userBean);
    }

//    @PostMapping("/updateUserInfo")
//    public Result<String> updateUserInfo(String username,
//                                         String password,
//                                         String introduce,
//                                         @TokenToUser UserBean userBean) {
//        logger.info("username = " + username + ", password = " + password + ", introduce = " + introduce);
//        logger.info("userBean = " + userBean.toString());
//
//        //todo 疑惑？controller与Service的职责分配问题。一些业务逻辑，我是写在Controller中呢？还是写在Service中呢？
//        UserBean originUserBean = userService.getUserById(userBean.getUserId());
//        logger.info("the User found by userId is = " + originUserBean.toString());
//
//        if (username != null && !username.isBlank()) {
//            originUserBean.setUsername(username);
//        }
//        if (password != null && !password.isBlank()) {
//            originUserBean.setPassword(password);
//        }
//        if (introduce != null && !introduce.isBlank()) {
//            originUserBean.setIntroduce(introduce);
//        }
//
//        String resultMsg = "update successful!";
//        int updateResultReturnBySql = userService.updateUserInfo(originUserBean);
//        if (updateResultReturnBySql == SqlOperateResult.FAILED) {
//            resultMsg = "update failed!";
//        }
//        logger.info("updateResultReturnBySql = " + updateResultReturnBySql);
//        return Result.success(resultMsg);
//    }


}
