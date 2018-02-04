package com.ciyou.edu.controller

import com.ciyou.edu.config.shiro.common.LoginType
import com.ciyou.edu.config.shiro.common.UserToken
import com.ciyou.edu.service.AdminService
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.subject.Subject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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
//@RequestMapping("/admin")
class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class)
    private static final String ADMIN_LOGIN_TYPE = LoginType.ADMIN.toString()

    @Autowired
    private AdminService adminService

    @RequestMapping("/addAdmin")
    @ResponseBody
    public String addAdmin(){

        return "success"
    }

    @RequestMapping("/findAdmin")
    @ResponseBody
    public String findAdmin(){
        return adminService?.findAdminById(1)
    }


    @RequestMapping(value="/login",method=RequestMethod.GET)
    @ResponseBody
    public String loginAdmin(@RequestParam("adminName") String username,@RequestParam("password")String password){
        //获取Subject实例对象
        //在shiro里面所有的用户的会话信息都会由Shiro来进行控制，那么也就是说只要是与用户有关的一切的处理信息操作都可以通过Shiro取得，
        // 实际上可以取得的信息可以有用户名、主机名称等等，这所有的信息都可以通过Subject接口取得
        Subject currentAdmin = SecurityUtils.getSubject()
        if (!currentAdmin?.isAuthenticated()) {
            //将用户名和密码封装到继承了UsernamePasswordToken的userToken
            UserToken userToken = new UserToken(username, password, ADMIN_LOGIN_TYPE)
            userToken.setRememberMe(false)
            try {
                //认证
                // 传到ModularRealmAuthenticator类中，然后根据ADMIN_LOGIN_TYPE传到AdminShiroRealm的方法进行认证
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
