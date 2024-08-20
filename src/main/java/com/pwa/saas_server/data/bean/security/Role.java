package com.pwa.saas_server.data.bean.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jere
 *
 * 权限角色
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private RoleType roleType;

}

