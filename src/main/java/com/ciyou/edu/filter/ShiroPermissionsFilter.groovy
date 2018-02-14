package com.ciyou.edu.filter

import com.ciyou.edu.utils.JSONUtil
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @Author C.
 * @Date 2018-02-11 23:54
 *
 * 权限验证失败的过滤器
 */
class ShiroPermissionsFilter extends PermissionsAuthorizationFilter {

    private static final Logger logger = LoggerFactory.getLogger(ShiroPermissionsFilter.class)

    /**
     * shiro认证perms资源失败后回调方法
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws IOException
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        logger.info("----------权限控制-------------")
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse
        String header = httpServletRequest.getHeader("X-Requested-With")
        boolean isAjax = "XMLHttpRequest" == header
        if (isAjax) {//如果是ajax返回指定格式数据
            logger.info("----------AJAX请求拒绝-------------")
            httpServletResponse.setCharacterEncoding("UTF-8")
            httpServletResponse.setContentType("application/json")
            //返回禁止访问json字符串
            httpServletResponse.getWriter().write(JSONUtil.returnForbiddenResult())
        } else {//如果是普通请求进行重定向
            logger.info("----------普通请求拒绝-------------")
            httpServletResponse.sendRedirect("/403")
        }
        return false
    }
}
