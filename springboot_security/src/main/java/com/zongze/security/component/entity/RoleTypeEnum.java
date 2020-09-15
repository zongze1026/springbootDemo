package com.zongze.security.component.entity;

/**
 * @Date 2020/9/11 9:31
 * @Created by xzz
 */
public enum RoleTypeEnum {
    ROLE_ROOT("1","超级管理员"),
    ROLE_GENERAL("2","普通角色")
    ;


    private String roleLevel;

    private String desc;

    RoleTypeEnum(String roleLevel, String desc) {
        this.roleLevel = roleLevel;
        this.desc = desc;
    }

    public String getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(String roleLevel) {
        this.roleLevel = roleLevel;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
