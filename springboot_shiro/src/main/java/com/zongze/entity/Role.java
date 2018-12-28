package com.zongze.entity;

import java.io.Serializable;

/**
 * Create By xzz on 2018/12/26
 */
public class Role implements Serializable {

    private Integer id;

    private String roleName;

    private String roleKey;

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
