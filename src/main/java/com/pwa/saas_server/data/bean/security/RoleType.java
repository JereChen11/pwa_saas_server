package com.pwa.saas_server.data.bean.security;

import lombok.Getter;

/**
 * 权限角色具体类型，如：Admin管理用户、普通用户等等。支持后续自定义扩展
 * @author jere
 */
@Getter
public enum RoleType {
    /**
     * 管理员用户
     */
    ADMIN("admin"),
    /**
     * 普通用户
     */
    USER("user");

    private final String roleName;

    RoleType(String roleName) {
        this.roleName = roleName;
    }

}
