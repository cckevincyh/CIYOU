package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Permission
import com.ciyou.edu.mapper.PermissionMapper
import com.ciyou.edu.service.ShiroService
import org.apache.shiro.spring.web.ShiroFilterFactoryBean
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver
import org.apache.shiro.web.servlet.AbstractShiroFilter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @Author C.
 * @Date 2018-02-13 15:03
 */
@Service
class ShiroServiceImpl implements ShiroService{

    private static final Logger logger = LoggerFactory.getLogger(ShiroServiceImpl.class)
    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean
    @Autowired
    private PermissionMapper permissionMapper

    /**
     * 初始化权限
     */
    public Map<String, String> loadFilterChainDefinitions() {
        // 权限控制map.从数据库获取
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>()
        filterChainDefinitionMap.put("/logout", "logout")
        filterChainDefinitionMap.put("/favicon.ico", "anon")
        filterChainDefinitionMap.put("/adminLogin", "anon")
        //允许访问静态资源
        filterChainDefinitionMap.put("/static/**", "anon")
        List<Permission> list = permissionMapper?.findAllPermission()

        for (Permission permission : list) {
            filterChainDefinitionMap.put(permission?.getUrl(),"perms["+ permission?.getPermission() +"]")
        }
        //   过滤链定义，从上向下顺序执行，一般将 /**放在最为下边
        filterChainDefinitionMap.put("/**", "authc")
        return filterChainDefinitionMap
    }

    /**
     * 重新加载权限
     */
    @Override
    public void updatePermission() {

        synchronized (shiroFilterFactoryBean) {

            AbstractShiroFilter shiroFilter = null
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean?.getObject()
            } catch (Exception e) {
                logger.info("重新加载权限失败：" + e.getMessage())
                throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!")
            }
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter?.getFilterChainResolver()
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver?.getFilterChainManager()

            // 清空老的权限控制
            manager?.getFilterChains()?.clear()

            shiroFilterFactoryBean?.getFilterChainDefinitionMap()?.clear()
            shiroFilterFactoryBean?.setFilterChainDefinitionMap(loadFilterChainDefinitions())
            // 重新构建生成
            Map<String, String> chains = shiroFilterFactoryBean?.getFilterChainDefinitionMap()
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry?.getKey()
                String chainDefinition = entry?.getValue()?.trim()?.replace(" ", "")
                manager.createChain(url, chainDefinition)
            }

            logger.info("更新权限成功！！")
        }
    }
}
