package com.ciyou.edu.controller.admin

import com.ciyou.edu.entity.Admin
import com.ciyou.edu.entity.PageInfo
import com.ciyou.edu.service.AdminService
import com.github.pagehelper.Page
import net.sf.json.JSONObject
import org.apache.shiro.SecurityUtils
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
        Admin addAdmin = adminService?.findByAdminName(admin?.getAdminName())
        if(addAdmin){
            //如果已经存在
            return "用户名已存在"
        }else{
            try{
                //密码默认:123456
                String passwordMd5= new Md5Hash("123456",admin?.getAdminName(),2).toHex()
                admin.setPassword(passwordMd5)
                if(adminService?.addAdmin(admin)){
                    return "添加成功"
                }else{
                    return "添加失败"
                }
            }catch (Exception e){
                logger.info("添加Admin错误：" + e.getMessage())
                return "添加失败，请重试"
            }
        }

    }

    @RequestMapping(value="/admin/updateAdmin", method=RequestMethod.POST)
    @ResponseBody
    String updateAdmin(Admin admin){
        return adminUpdate(admin)
    }

    @RequestMapping(value="/admin/updateInfo", method=RequestMethod.POST)
    @ResponseBody
    String updateInfo(Admin admin){
        String message = adminUpdate(admin)
        if(message == "修改成功"){
            //重新存入session
            SecurityUtils.getSubject()?.getSession()?.setAttribute("admin",admin)
            //更新AuthenticationInfo
            Admin authenticationInfo = (Admin)SecurityUtils?.getSubject()?.getPrincipal()
            authenticationInfo?.setName(admin?.getName())
            authenticationInfo?.setPhone(admin?.getPhone())
        }
        logger.info("修改个人信息：" + (Admin)SecurityUtils?.getSubject()?.getPrincipal())
        return message
    }

    /**
     * 修改个人信息和修改管理员信息的通用方法
     * @param admin
     * @return
     */
    String adminUpdate(Admin admin){
        logger.info("修改Admin...admin信息：" + admin)
        //校验提交的admin
        if(!admin?.getName() || admin?.getName()?.trim() == ""){
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

        try{
            if(adminService?.updateAdmin(admin)){
                return "修改成功"
            }else{
                return "修改失败"
            }
        }catch (Exception e){
            logger.info("修改Admin错误：" + e.getMessage())
            return "修改失败，请重试"
        }
    }

    /**
     * 得到指定的管理员
     * Ajax请求该方法
     * 向浏览器返回该管理员的json对象
     *
     * Response的Body部分确实是正确的JSON格式字符串,但是收到的响应消息类型是text/plain
     * 在@RequestMapping增加一个produces参数项 produces="application/json;charset=UTF-8"
     * @return
     */
    @RequestMapping(value="/admin/getAdmin", method=RequestMethod.POST , produces="application/json;charset=UTF-8")
    @ResponseBody
    String getAdmin(Integer adminId){
        Admin admin = adminService?.findAdminById(adminId)
        logger.info("获得指定的Admin：" + admin)
        //这里要转为json对象，前端ajax才解析的了
        JSONObject jsonObject = JSONObject.fromObject(admin)
        return jsonObject.toString()
    }



    @RequestMapping(value="/admin/deleteAdmin", method=RequestMethod.POST)
    @ResponseBody
    String deleteAdmin(Integer adminId){
        try{
            if(adminService?.deleteAdmin(adminId)){
                return "删除成功"
            }else{
                return "删除失败"
            }
        }catch (Exception e){
            logger.info("删除Admin错误：" + e.getMessage())
            return "删除失败，请重试"
        }
    }

    @RequestMapping(value="/admin/queryAdmin")
    ModelAndView queryAdmin(String searchContent,Integer page){
        if(!page){
            page = 1
        }
        if(!searchContent || searchContent?.trim() == ""){
            //重定向到findAdminByPage Controller
            ModelAndView mv = new ModelAndView("redirect:manageAdmin")
            return mv
        }else{
            ModelAndView mv = new ModelAndView("/admin/manageAdmin")
            logger.info("queryAdmin : 查询第${page}页，携带查询参数=${searchContent}")
            //不赋值pageSize，默认为10
            Page<Admin> admins = adminService?.queryAdminByPage(searchContent,page)
            // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
            PageInfo<Admin> pageInfo = new PageInfo<Admin>(admins)
            pageInfo?.setUrl("/admin/queryAdmin?searchContent=${searchContent}&")
            mv?.addObject("pageInfo",pageInfo)
            return mv
        }

    }

    @RequestMapping(value="/admin/updatePassword", method=RequestMethod.POST )
    @ResponseBody
    String updatePassword(String oldPwd, String newPwd, String confirmPwd){
        //校验数据
        if(!oldPwd || oldPwd?.trim() == ""){
            return "原密码不能为空"
        }else if(!newPwd || newPwd?.trim() == ""){
            return "新密码不能为空"
        }else if(!confirmPwd || confirmPwd?.trim() == ""){
            return "确认密码不能为空"
        }else if(oldPwd?.length() < 3 || oldPwd?.length() > 15){
            return "原密码长度必须在3~15之间"
        }else if(newPwd?.length() < 3 || newPwd?.length() > 15){
            return "新密码长度必须在3~15之间"
        }else if(newPwd != confirmPwd){
            return "确认密码不一致"
        }
        //获取当前Admin
        Admin admin = (Admin)SecurityUtils.getSubject()?.getPrincipal()
        //旧密码加密
        String oldPasswordMd5= new Md5Hash(oldPwd,admin?.getAdminName(),2).toHex()
        //比对原密码是否正确
        if(oldPasswordMd5 != admin?.getPassword()){
            return "原密码错误"
        }else{
            //新密码加密
            String passwordMd5 = new Md5Hash(newPwd,admin?.getAdminName(),2).toHex()
            try{
                if(adminService?.updatePassword(admin?.getAdminId(),passwordMd5)){
                    //登出
                    SecurityUtils.getSubject()?.logout()
                    return "修改密码成功"
                }else{
                    return "修改密码失败"
                }
            }catch (Exception e){
                logger.info("修改Admin密码错误：" + e.getMessage())
                return "修改密码失败，请重试"
            }

        }


    }
}
