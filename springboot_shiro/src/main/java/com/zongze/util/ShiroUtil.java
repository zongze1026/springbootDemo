package com.zongze.util;
import com.zongze.config.shiro.UserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * Create By xzz on 2018/12/29
 */
public class ShiroUtil {


    //获取realName
    public static String realmName() {
        return SecurityUtils.getSubject().getPrincipals().getRealmNames().iterator().next();
    }

    public static Subject subject(){
        return SecurityUtils.getSubject();
    }

    //获得自定义的realm对象
    public static UserRealm userRealm() {
        RealmSecurityManager rm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        return (UserRealm) rm.getRealms().iterator().next();
    }

    public  static void clearCache(){
        UserRealm realm = userRealm();
        //这个对象就是缓存权限时的key
        SimplePrincipalCollection simplePrincipalCollection = new SimplePrincipalCollection(subject().getPrincipal(),realmName());
        realm.getAuthorizationCache().remove(simplePrincipalCollection);
        subject().runAs(simplePrincipalCollection);
    }





}
