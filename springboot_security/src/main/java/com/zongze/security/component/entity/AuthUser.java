package com.zongze.security.component.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthUser implements UserDetails {
    private String realName;
    private Integer orgId;
    private String phone;
    private String password;
    private String belongSys; //1总部后台 2机构后台
    private String state;
    private List<ManagerRole> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> power = new ArrayList<>();
        if(!CollectionUtils.isEmpty(roles)){
            for(ManagerRole managerRole:this.roles){
                power.add(new SimpleGrantedAuthority(managerRole.getRoleCode()));
            }
        }
        return power;
    }

    public AuthUser(ManagerUser managerUser, List<ManagerRole> roles) {
        this.realName=managerUser.getUname();
        this.orgId=managerUser.getOrgId();
        this.phone=managerUser.getPhone();
        this.password=managerUser.getPwd();
        this.belongSys=managerUser.getBelongSys();
        this.state=managerUser.getState();
        this.roles = roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.phone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return "0".equalsIgnoreCase(state);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


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

    public List<ManagerRole> getRoles() {
        return roles;
    }

    public void setRoles(List<ManagerRole> roles) {
        this.roles = roles;
    }
}
