package com.ciyou.edu.controller

import org.apache.shiro.authz.annotation.RequiresPermissions
import org.apache.shiro.authz.annotation.RequiresRoles
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * @Author C.
 * @Date 2018-02-01 23:30
 */
@Controller
class IndexController {

    @RequestMapping("/hello")
    @RequiresRoles("Admin")
    @ResponseBody
    public String hello(){
        def str = "hello world!!哈哈"
        return str
    }

}
