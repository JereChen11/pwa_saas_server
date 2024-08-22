package com.pwa.saas_server.controller;

import cn.hutool.jwt.JWT;
import com.pwa.saas_server.data.base.Result;
import com.pwa.saas_server.data.base.ResultCode;
import com.pwa.saas_server.data.bean.user.UserBean;
import com.pwa.saas_server.service.UserService;
import com.pwa.saas_server.utils.MyConstant;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;
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

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public Result<String> register(@RequestParam("username") String username,
                                   @RequestParam("password") String password,
                                   @RequestParam("referer") String referer) {
        logger.info("register username: " + username + ", password: " + password + ", referer = " + referer);

        if (password.length() < 6) {
            return Result.error("Please enter a password with more than 6 digits");
        }

        //将原始密码进行加密处理得到加密后的密码，并存到数据库。
        String encPwd = new BCryptPasswordEncoder().encode(password);
        logger.info("register, password = " + password + ", encPwd = " + encPwd);
        UserBean userBean = new UserBean(username, encPwd, referer);
        return userService.insertUser(userBean);
    }


    @PostMapping("/login")
    public Result<String> login(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                HttpServletResponse response) {
        logger.info("login username = " + username + ", password = " + password);
        if (username.isBlank() || password.isBlank()) {
            return Result.error(ResultCode.PARAM_ERROR);
        }
        /*
        UserBean userBean = userService.getUserByNameAndPwd(username, password);
        if (userBean == null) {
            return Result.error("登入失败");
        }
        logger.info("login userBean = " + userBean);
         */


        //登入成功，则颁布认证token。
        String token = authenticate(username, password);
        logger.info("login authenticate get token = " + token);
        //将token 是设置在 HTTP 头部中，并作为响应的一部分发送给客户端。注意这里的空格不能省略！
        response.setHeader("Authorization", "Bearer " + token);
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

    private String authenticate(String username, String password) {
        logger.info("authenticate username = " + username + ", password = " + password);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        logger.info("authenticate authenticationToken = " + authenticationToken);
        //进行认证，如果认证失败会抛出异常。
        authenticationManager.authenticate(authenticationToken);

        logger.info("authenticate current Date = " + new Date());
        //设置Token失效时间为7天。
        Date expireDate = new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000);
        logger.info("authenticate expireDate = " + expireDate);

        //上一步没有抛出异常说明认证成功，我们向客户端颁发jwt令牌
        String token = JWT.create()
                .setPayload(MyConstant.JWT_PAYLOAD_KEY_USERNAME, username)
                .setKey(MyConstant.JWT_SIGN_KEY.getBytes(StandardCharsets.UTF_8))
                .setIssuedAt(new Date())
                .setExpiresAt(expireDate)
                .sign();
        logger.info("token = " + token);
        return token;
    }

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
