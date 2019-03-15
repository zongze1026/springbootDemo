package com.zongze.entity;

import java.io.Serializable;

/**
 * Create By xzz on 2018/12/27
 */
public class Menu extends AbstractIntity implements Serializable {

    private Integer id;

    private String menuName;

    private Integer parentId;

    private String menuPerm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getMenuPerm() {
        return menuPerm;
    }

    public void setMenuPerm(String menuPerm) {
        this.menuPerm = menuPerm;
    }
}
