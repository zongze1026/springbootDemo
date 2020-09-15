package com.zongze.security.component.mapper;


import com.zongze.security.component.entity.ManagerUser;
import com.zongze.security.component.entity.ManagerUserExample;


import java.util.List;
import java.util.Map;

public interface ManagerUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manager_user
     *
     * @mbggenerated Mon Nov 26 15:43:55 CST 2018
     */
    int countByExample(ManagerUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manager_user
     *
     * @mbggenerated Mon Nov 26 15:43:55 CST 2018
     */
    int deleteByExample(ManagerUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manager_user
     *
     * @mbggenerated Mon Nov 26 15:43:55 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manager_user
     *
     * @mbggenerated Mon Nov 26 15:43:55 CST 2018
     */
    int insert(ManagerUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manager_user
     *
     * @mbggenerated Mon Nov 26 15:43:55 CST 2018
     */
    int insertSelective(ManagerUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manager_user
     *
     * @mbggenerated Mon Nov 26 15:43:55 CST 2018
     */
    List<ManagerUser> selectByExample(ManagerUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table manager_user
     *
     * @mbggenerated Mon Nov 26 15:43:55 CST 2018
     */
    ManagerUser selectByPrimaryKey(Integer id);


    /**
     * 根据用户名查询用户id
     * @param uname
     * @return
     */
    List<ManagerUser> selectByUserName(String uname);



    List<ManagerUser> selectByOrgId(Integer orgId);

    /**查询机构负责人*/
    ManagerUser selectByPhoneAndName(Map<String, Object> param);

    ManagerUser selectByOrgIdAndLiable(Integer orgId);

    ManagerUser selectEffManageUserByPhone(String phone);
}