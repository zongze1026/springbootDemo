package com.zongze.entity;
import com.alibaba.fastjson.JSON;
import com.zongze.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.xml.bind.util.JAXBSource;
import java.util.Calendar;
import java.util.Date;

/**
 * Create By xzz on 2019/2/21
 */
@Setter
@Getter
public class RoleChild extends Role {

    private Date createTime;


    public String getCreateTime() {
        return DateUtil.format(this.createTime,DateUtil.DATE_TIME);
    }

    public void setCreateTime(Date createTime) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(createTime);
        instance.set(Calendar.DATE,instance.get(Calendar.DATE)+1);
        this.createTime = instance.getTime();
    }

    public static void main(String[] args) {

        Role role = new Role();
        role.setId(10);
        role.setRoleKey("lelv");
        role.setRoleName("admin");

        RoleChild roleChild = new RoleChild();
        BeanUtils.copyProperties(role,roleChild);
        roleChild.setCreateTime(new Date());
        System.out.println(JSON.toJSONString(roleChild));
        System.out.println(roleChild.getCreateTime());


    }


}
