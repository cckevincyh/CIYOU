package com.ciyou.edu.config.shiro.student

import com.ciyou.edu.config.shiro.common.UserToken
import com.ciyou.edu.entity.Student
import com.ciyou.edu.service.StudentService
import org.apache.shiro.authc.*
import org.apache.shiro.authz.AuthorizationException
import org.apache.shiro.authz.AuthorizationInfo
import org.apache.shiro.authz.SimpleAuthorizationInfo
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection
import org.apache.shiro.util.ByteSource
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

/**
 * @Author C.
 * @Date 2018-02-02 14:58
 */
class StudentShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(getClass())

     @Autowired
     private StudentService studentService



    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        logger.info("开始Student身份认证..")
        UserToken userToken = (UserToken)token
        String studentId =  userToken?.getUsername() //获取用户名，默认和login.html中的adminName对应。
        Student student = studentService?.findByStudentId(studentId)

        if (student == null) {
            //没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
            throw new UnknownAccountException("用户不存在！")
        }

        //验证通过返回一个封装了用户信息的AuthenticationInfo实例即可。
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                student, //用户信息
                student?.getPassword(), //密码
                getName() //realm name
        )
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(student?.getStudentId())) //设置盐

        return authenticationInfo
    }

//当访问到页面的时候，链接配置了相应的权限或者shiro标签才会执行此方法否则不会执行
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("开始Student权限授权")
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.")
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo()
        authorizationInfo?.addRole("Student")
        return authorizationInfo
    }
}
