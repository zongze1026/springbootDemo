package com.zongze.security.component.entity;

import java.util.List;

/**
 * @Date 2020/9/10 16:47
 * @Created by xzz
 */
public class UserLoginResVo {

    private String realName;
    private Integer orgId;
    private String phone;
    private String password;
    private String belongSys; //1总部后台 2机构后台
    private String state;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private List<ManagerMenu> menus;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBelongSys() {
        return belongSys;
    }

    public void setBelongSys(String belongSys) {
        this.belongSys = belongSys;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ManagerMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<ManagerMenu> menus) {
        this.menus = menus;
    }
}
