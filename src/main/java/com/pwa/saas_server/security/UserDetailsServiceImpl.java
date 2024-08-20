package com.pwa.saas_server.security;

import com.pwa.saas_server.data.bean.security.Role;
import com.pwa.saas_server.data.bean.security.RoleType;
import com.pwa.saas_server.data.bean.security.User;
import com.pwa.saas_server.data.bean.user.UserBean;
import com.pwa.saas_server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jere
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("username = " + username);
        //这里根据username来查询，要是遇到数据库中同名的用户怎么办。如果要这么写，那username得是唯一值。思考一下！
        //解决方案：所以将用户表中的username字段设置成了UNIQUE属性，这样用户表中就不会存在两个相同的username。
        UserBean userBean = userService.getUsersByUsername(username);
        logger.info("userBean.toString = " + userBean.toString());

        //这里用来给用户分配权限，例如：管理员用户、普通用户等等。
        List<Role> roles = List.of(new Role(RoleType.USER));
        User user = new User(userBean.getUsername(), userBean.getEncPwd(), roles);
        return new UserDetailsImpl(user);
    }
}
