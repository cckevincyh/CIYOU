package com.ciyou.edu.config.shiro

import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.AuthenticationInfo
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.SimpleAuthenticationInfo
import org.apache.shiro.authz.AuthorizationInfo
import org.apache.shiro.authz.SimpleAuthorizationInfo
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection
import org.apache.shiro.util.ByteSource

/**
 * @Author C.
 * @Date 2018-02-02 14:58
 */
class MyShiroRealm extends AuthorizingRealm {

//    @Resource
//    private UserInfoService userInfoService;



    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("开始身份验证");
        String username = (String) token.getPrincipal(); //获取用户名，默认和login.html中的username对应。
       // UserInfo userInfo = userInfoService.findByUsername(username);

//        if (userInfo == null) {
//            //没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
//            return null;
//        }
        if(1){
            return null
        }

        //验证通过返回一个封装了用户信息的AuthenticationInfo实例即可。
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                "1111", //用户信息
                "1111", //密码
                getName() //realm name
        );
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("111")) //设置盐

        return authenticationInfo;
    }

//当访问到页面的时候，链接配置了相应的权限或者shiro标签才会执行此方法否则不会执行
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        System.out.println("开始权限配置");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Object userInfo = (Object) principals.getPrimaryPrincipal();


        authorizationInfo.addStringPermission(1);

        return authorizationInfo;
    }
}
