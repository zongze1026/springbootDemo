package com.zongze.security.component.entity;


import java.util.List;

/**
 * @Date 2020/9/11 17:21
 * @Created by xzz
 */
public class AddManagerRoleBo {

    private String roleName;

    private List<Long> menus;

    private Long roleId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Long> getMenus() {
        return menus;
    }

    public void setMenus(List<Long> menus) {
        this.menus = menus;
    }
}
