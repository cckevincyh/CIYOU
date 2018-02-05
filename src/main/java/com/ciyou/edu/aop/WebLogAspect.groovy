package com.ciyou.edu.aop

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.stereotype.Component
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import javax.servlet.http.HttpServletRequest;
/**
 * @Author C.
 * @Date 2018-02-02 12:27
 */
@Aspect
@Component
class WebLogAspect {
    private Logger logger = LoggerFactory.getLogger(WebLogAspect.class)

    // 匹配com.ciyou.edu.controller包下所有类的、
    // 所有方法的执行作为切入点
    @Pointcut("execution(public * com.ciyou.edu.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes?.getRequest()
        // 记录下请求内容
        logger.info("URL : " + request?.getRequestURL()?.toString())
        logger.info("HTTP_METHOD : " + request?.getMethod())
        logger.info("IP : " + request?.getRemoteAddr())
        Enumeration<String> enu = request?.getParameterNames()
        while (enu?.hasMoreElements()) {
            String name = (String) enu?.nextElement()
            logger.info("name:{},value:{}", name, request?.getParameter(name))
        }
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret)
    }
}