package com.ciyou.edu.controller.admin

import com.ciyou.edu.entity.Admin
import com.ciyou.edu.entity.PageInfo
import com.ciyou.edu.service.AdminService
import com.github.pagehelper.Page
import org.apache.shiro.crypto.hash.Md5Hash
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView

import java.util.regex.Pattern

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
    ModelAndView findAdminByPage(Integer page){
        if(!page){
            page = 1
        }
        ModelAndView mv = new ModelAndView("admin/manageAdmin")
        logger.info("findAdminByPage : 查询第${page}页")
        //不赋值pageSize，默认为10
        Page<Admin> admins = adminService.findByPage(page)
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Admin> pageInfo = new PageInfo<Admin>(admins)
        pageInfo?.setUrl("/admin/manageAdmin?")
        mv?.addObject("pageInfo",pageInfo)
        return mv
    }

    @RequestMapping(value="/admin/addAdmin", method=RequestMethod.POST)
    @ResponseBody
    String addAdmin(Admin admin){
        logger.info("添加Admin...admin信息：" + admin)
        //校验提交的admin
        if(!admin?.getAdminName() || admin?.getAdminName()?.trim() == ""){
            return "用户名不能为空"
        }else if(!admin?.getName() || admin?.getName()?.trim() == ""){
            return "姓名不能为空"
        }else if(!admin?.getPhone() || admin?.getPhone()?.trim() == ""){
            return "电话号码不能为空"
        }else if(admin?.getAdminName()?.length() < 3 || admin?.getAdminName()?.length() > 16){
            return "用户名长度必须在3~15之间"
        }else if(!Pattern.compile("[\\u4E00-\\u9FFF]+")?.matcher(admin?.getName())?.matches()){
            return "姓名必须为中文"
        }else if(!Pattern.compile('^1[34578]\\d{9}$')?.matcher(admin?.getPhone())?.matches()){
            return "电话号码有误"
        }

        //按照账号查找管理员，查看用户名是否已经存在
        Admin addAdmin = adminService?.findByAdminName(admin?.adminName)
        if(addAdmin){
            //如果已经存在
            return "用户名已存在"
        }else{
            try{
                //密码默认:123456
                String passwordMd5= new Md5Hash("123456",admin.getAdminName(),2).toHex()
                admin.setPassword(passwordMd5)
                adminService?.addAdmin(admin)
            }catch (Exception e){
                logger.info("添加Admin错误：" + e.getMessage())
                return "添加失败，请重试"
            }
            return "添加成功"
        }

    }










}
