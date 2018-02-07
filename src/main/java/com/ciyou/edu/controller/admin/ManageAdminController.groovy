package com.ciyou.edu.controller.admin

import com.ciyou.edu.service.AdminService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * @Author C.
 * @Date 2018-02-07 16:19
 * 管理员管理Controller
 */
@Controller
class ManageAdminController {

    private static final Logger logger = LoggerFactory.getLogger(ManageAdminController.class)
    @Autowired
    private AdminService adminService

    @RequestMapping("/admin/manageAdmin")
    String findAdmin(){
        return "admin/manageAdmin"
    }

    @RequestMapping("/admin/findByPage")
    String findAdminByPage(){
        logger.info("接收到信息")
        return "success"
    }




}
