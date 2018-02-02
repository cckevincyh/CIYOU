package com.ciyou.edu.controller

import com.ciyou.edu.entity.Admin
import com.ciyou.edu.entity.Permission
import com.ciyou.edu.entity.Role
import com.ciyou.edu.service.AdminService
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

    @Autowired
    private AdminService adminService

    @RequestMapping("/addAdmin")
    @ResponseBody
    public String addAdmin(){
        return "success"
    }
}
