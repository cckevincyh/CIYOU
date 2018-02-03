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
import org.springframework.web.bind.annotation.ResponseBody

/**
 * @Author C.
 * @Date 2018-02-02 20:46
 */
@Controller
class AdminController {

    private static final String USER_LOGIN_TYPE = LoginType.ADMIN.toString()

    @Autowired
    private AdminService adminService

    @RequestMapping("/addAdmin")
    @ResponseBody
    public String addAdmin(){
        return "success"
    }


    @RequestMapping("/login")
    @ResponseBody
    public String loginAdmin(){
        Subject currentAdmin = SecurityUtils.getSubject()
        if (!currentAdmin?.isAuthenticated()) {
            UserToken userToken = new UserToken("1", "1", USER_LOGIN_TYPE)
            userToken.setRememberMe(false)
            try {
                currentAdmin?.login(userToken)
                return "success"
            } catch (AuthenticationException ae) {
                println(ae.getMessage())
            }
        }else{
            return "isAuthenticated"
        }
        //  ModelAndView mav = new ModelAndView()
        return "fail"
    }
}
