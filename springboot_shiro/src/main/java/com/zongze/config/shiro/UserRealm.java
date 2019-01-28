package com.zongze.config.shiro;

import com.zongze.entity.User;
import com.zongze.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Create By xzz on 2018/12/24
 */
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisSessionDAO redisSessionDAO;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(userService.getRole(user.getUserName()));
        simpleAuthorizationInfo.addStringPermissions(userService.getPerm(user.getUserName()));
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User userInfo = userService.userInfo(token.getUsername());

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userInfo, userInfo.getPassWord(), getName());

        // 当验证都通过后，把用户信息放在session里N
//        Session session = SecurityUtils.getSubject().getSession();
//        session.setAttribute("userSession", userInfo);
//        session.setAttribute("userSessionId", userInfo.getId());

        return simpleAuthenticationInfo;
    }


    /**
     * 根据userId 清除当前session存在的用户的权限缓存
     *
     * @param userIds 已经修改了权限的userId
     *                List<Integer> userIds
     */
    public void clearUserAuthByUserId() {
//        if (null == userIds || userIds.size() == 0) return;
        //获取所有session
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        //定义返回
        List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();
        for (Session session : sessions) {
            //获取session登录信息。
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (null != obj && obj instanceof SimplePrincipalCollection) {
                //强转
                SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
                //判断用户，匹配用户ID。
                obj = spc.getPrimaryPrincipal();
                if (null != obj && obj instanceof User) {
                    User user = (User) obj;
                    System.out.println("user:" + user);
//                    //比较用户ID，符合即加入集合
//                    if (null != user && userIds.contains(user.getId())) {
//                        list.add(spc);
//                    }
                }
            }
        }
        RealmSecurityManager securityManager =
                (RealmSecurityManager) SecurityUtils.getSecurityManager();
        UserRealm realm = (UserRealm) securityManager.getRealms().iterator().next();
        for (SimplePrincipalCollection simplePrincipalCollection : list) {
            realm.clearCachedAuthorizationInfo(simplePrincipalCollection);
        }
    }

    public void clearCache(PrincipalCollection principals){
        this.clearCachedAuthenticationInfo(principals);
    }



}
