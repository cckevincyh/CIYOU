package com.ciyou.edu.config.shiro.common

import com.ciyou.edu.config.shiro.admin.AdminShiroRealm
import com.ciyou.edu.entity.Permission
import com.ciyou.edu.service.PermissionService
import org.apache.shiro.authc.credential.HashedCredentialsMatcher
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy
import org.apache.shiro.authc.pam.ModularRealmAuthenticator
import org.apache.shiro.realm.Realm
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
import org.apache.shiro.spring.web.ShiroFilterFactoryBean
import org.apache.shiro.web.mgt.DefaultWebSecurityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.apache.shiro.mgt.SecurityManager;
/**
 * @Author C.
 * @Date 2018-02-03 9:54
 * Shiro 配置类
 */
@Configuration
class ShiroConfiguration {

    @Autowired(required = false)
    private PermissionService permissionService

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
     *
     * 部分过滤器可指定参数，如perms，roles
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean()
        shiroFilterFactoryBean.setSecurityManager(securityManager)

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login")
        //登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index")
        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403")

        // 权限控制map.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>()
        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout")
        filterChainDefinitionMap.put("/favicon.ico", "anon")
        // 从数据库获取所有的权限
        List<Permission> permissionList = permissionService?.findAllPermission()
        permissionList?.each {current_Permission ->
            //规则："roles[admin,user]", "perms[file:edit]"
            filterChainDefinitionMap?.put(current_Permission?.getUrl(),"perms["+current_Permission?.getPermission()+"]")
        }

        //   过滤链定义，从上向下顺序执行，一般将 /**放在最为下边
        filterChainDefinitionMap.put("/**", "authc")
        //authc表示需要验证身份才能访问，还有一些比如anon表示不需要验证身份就能访问等。


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap)
        return shiroFilterFactoryBean
    }


    //SecurityManager 是 Shiro 架构的核心，通过它来链接Realm和用户(文档中称之为Subject.)
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager()
        //设置realm.
        securityManager.setAuthenticator(modularRealmAuthenticator())
        List<Realm> realms=new ArrayList<>()
        //添加多个Realm
        realms.add(adminShiroRealm())
        securityManager.setRealms(realms)
        return securityManager
    }

    /**
     * 系统自带的Realm管理，主要针对多realm
     * */
    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator(){
        //自己重写的ModularRealmAuthenticator
        UserModularRealmAuthenticator modularRealmAuthenticator = new UserModularRealmAuthenticator()
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy())
        return modularRealmAuthenticator
    }

    @Bean
    public AdminShiroRealm adminShiroRealm() {
        AdminShiroRealm adminShiroRealm = new AdminShiroRealm()
        adminShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher())//设置解密规则
        return adminShiroRealm
    }

    //因为我们的密码是加过密的，所以，如果要Shiro验证用户身份的话，需要告诉它我们用的是md5加密的，并且是加密了两次。同时我们在自己的Realm中也通过SimpleAuthenticationInfo返回了加密时使用的盐。这样Shiro就能顺利的解密密码并验证用户名和密码是否正确了。
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher()
        hashedCredentialsMatcher.setHashAlgorithmName("md5")//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2)//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     *  开启 权限注解
     *  Controller才能使用@RequiresPermissions
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor()
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager)
        return authorizationAttributeSourceAdvisor
    }

}

