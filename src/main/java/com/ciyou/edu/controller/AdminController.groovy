package com.ciyou.edu.controller

import com.ciyou.edu.config.shiro.common.LoginType
import com.ciyou.edu.config.shiro.common.UserToken
import com.ciyou.edu.service.AdminService
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.subject.Subject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

/**
 * @Author C.
 * @Date 2018-02-02 20:46
 */
@Controller
@RequestMapping("/admin")
class AdminController {

    private static final String USER_LOGIN_TYPE = LoginType.ADMIN.toString()

    @Autowired
    private AdminService adminService

    @RequestMapping("/addAdmin")
    @ResponseBody
    public String addAdmin(){

        return "success"
    }


    @RequestMapping(value="/login",method=RequestMethod.POST)
    @ResponseBody
    public String loginAdmin(@RequestParam("adminName") String username,@RequestParam("password")String password){
        //获取Subject实例对象
        Subject currentAdmin = SecurityUtils.getSubject()
        if (!currentAdmin?.isAuthenticated()) {
            //将用户名和密码封装到继承了UsernamePasswordToken的userToken
            UserToken userToken = new UserToken(username, password, USER_LOGIN_TYPE)
            userToken.setRememberMe(false)
            try {
                //认证
                // 传到ModularRealmAuthenticator类中，然后根据USER_LOGIN_TYPE传到AdminShiroRealm的方法进行认证
                currentAdmin?.login(userToken)
                return "success"
            } catch (AuthenticationException ae) {
                //认证失败就会抛出AuthenticationException这个异常，就对异常进行相应的操作，这里的处理是抛出一个自定义异常ResultException
                //到时候我们抛出自定义异常ResultException，用户名或者密码错误
                println(ae.getMessage())
            }
        }else{
            return "isAuthenticated"
        }
        //  ModelAndView mav = new ModelAndView()
        return "fail"
    }
}
