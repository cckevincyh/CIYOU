package com.ciyou.edu.controller.student

import com.ciyou.edu.entity.Student
import com.ciyou.edu.service.StudentService
import com.ciyou.edu.utils.JSONUtil
import org.apache.shiro.SecurityUtils
import org.apache.shiro.crypto.hash.Md5Hash
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile

import javax.servlet.http.HttpServletRequest
import java.util.regex.Pattern

/**
 * @Author C.
 * @Date 2018-02-18 14:52
 */
@Controller
class StudentProfileController {

    private static final Logger logger = LoggerFactory.getLogger(StudentProfileController.class)

    @Autowired
    private StudentService studentService

    @RequestMapping("/student/profile")
    String profile(){
        return "/student/profile"
    }


    @RequestMapping(value="/student/StudentImgFileUpload", method=RequestMethod.POST)
    @ResponseBody
    String studentImgFileUpload(@RequestParam MultipartFile file, HttpServletRequest request){
        logger.info("学生头像上传....")
        //获取文件名
        String originalFilename = file.getOriginalFilename()
        logger.info("上传文件名：" + originalFilename)
        String realPath = request.getServletContext().getRealPath("/public/student/")
        File _dirFile = new File(realPath)
        if (!_dirFile?.exists() && !_dirFile?.isDirectory()){
            _dirFile?.mkdir()
        }

        String uploadFileName = System.currentTimeMillis()+"_"+ originalFilename
        logger.info("获取上传路径：" + realPath + ", 上传的真实文件名：" + uploadFileName)
        boolean flag = true

        //合并文件
        RandomAccessFile raFile = null
        BufferedInputStream inputStream = null
        try{
            File dirFile = new File(realPath, uploadFileName)
            //以读写的方式打开目标文件
            raFile = new RandomAccessFile(dirFile, "rw")
            raFile.seek(raFile.length())
            inputStream = new BufferedInputStream(file.getInputStream())
            byte[] buf = new byte[1024]
            int length = 0
            while ((length = inputStream.read(buf)) != -1) {
                raFile.write(buf, 0, length)
            }
        }catch(Exception e){
            flag = false
            logger.info("上传出错:" + e.getMessage())
            throw new IOException(e.getMessage())
        }finally{
            try {
                if (inputStream != null) {
                    inputStream.close()
                }
                if (raFile != null) {
                    raFile.close()
                }
            }catch(Exception e){
                flag = false
                logger.info("上传出错:" + e.getMessage())
                throw new IOException(e.getMessage())
            }
        }
        if(flag){
            Student student = (Student)SecurityUtils.getSubject()?.getSession()?.getAttribute("student")
            student?.setPicImg("/public/student/" + uploadFileName)
            //成功->修改学生头像
            if(studentService?.updatePicImg(student?.getSid(),student?.getPicImg())){
                //重新存入session
                SecurityUtils.getSubject()?.getSession()?.setAttribute("student",student)
                //更新AuthenticationInfo
                Student authenticationInfo = (Student)SecurityUtils?.getSubject()?.getPrincipal()
                authenticationInfo?.setPicImg(student?.getPicImg())
            }
            return "success"
        }else{
            return "fail"
        }

    }


    @RequestMapping(value="/student/updatePassword", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String studentUpdatePassword(String oldPwd, String newPwd, String confirmPwd){
        //校验数据
        if(!oldPwd || oldPwd?.trim() == ""){
            return JSONUtil.returnFailReuslt("原密码不能为空")
        }else if(!newPwd || newPwd?.trim() == ""){
            return JSONUtil.returnFailReuslt("新密码不能为空")
        }else if(!confirmPwd || confirmPwd?.trim() == ""){
            return JSONUtil.returnFailReuslt("确认密码不能为空")
        }else if(oldPwd?.trim()?.length() < 3 || oldPwd?.trim()?.length() > 15){
            return JSONUtil.returnFailReuslt("原密码长度必须在3~15之间")
        }else if(newPwd?.trim()?.length() < 3 || newPwd?.trim()?.length() > 15){
            return JSONUtil.returnFailReuslt("新密码长度必须在3~15之间")
        }else if(newPwd != confirmPwd){
            return JSONUtil.returnFailReuslt("确认密码不一致")
        }
        Student student = (Student)SecurityUtils.getSubject()?.getPrincipal()
        //旧密码加密
        String oldPasswordMd5= new Md5Hash(oldPwd,student?.getStudentId(),2).toHex()
        //比对原密码是否正确
        if(oldPasswordMd5 != student?.getPassword()){
            return JSONUtil.returnFailReuslt("原密码错误")
        }else{
            //新密码加密
            String passwordMd5 = new Md5Hash(newPwd,student?.getStudentId(),2).toHex()
            try{
                if(studentService?.updatePassword(student?.getSid(),passwordMd5)){
                    //登出
                    SecurityUtils.getSubject()?.logout()
                    return JSONUtil.returnSuccessResult("修改密码成功")
                }else{
                    return JSONUtil.returnFailReuslt("修改密码失败")
                }
            }catch (Exception e){
                logger.info("修改学生密码错误：" + e.getMessage())
                return JSONUtil.returnFailReuslt("修改密码失败，请重试")
            }
        }
    }


    @RequestMapping(value="/student/getStudentBySid", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String getStudentBySid(String sid){
        return JSONUtil.returnEntityReuslt(studentService?.getStudentById(sid))
    }


    @RequestMapping(value="/student/updateProfile", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String updateStudentProfile(Student student){
        logger.info("修改Student信息：" + student)
        //校验提交的student
        if(!student?.getName() || student?.getName()?.trim() == ""){
            return JSONUtil.returnFailReuslt("学生姓名不能为空")
        }else if(!Pattern.compile("[\\u4E00-\\u9FFF]+")?.matcher(student?.getName())?.matches()){
            return JSONUtil.returnFailReuslt("学生姓名必须为中文")
        }else if(student?.getAge() != null && !Pattern.compile( '^[1-9]+\\d*$')?.matcher(student?.getAge()?.toString())?.matches()){
            return JSONUtil.returnFailReuslt("年龄必须为正整数")
        }else if(student?.getMobile() && !Pattern.compile('^1[34578]\\d{9}$')?.matcher(student?.getMobile())?.matches()){
            return JSONUtil.returnFailReuslt("电话号码有误")
        }else if(student?.getEmail() && !Pattern.compile('^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+')?.matcher(student?.getEmail())?.matches()){
            return JSONUtil.returnFailReuslt("邮箱有误")
        }else if(student?.getParentMobile() && !Pattern.compile('^1[34578]\\d{9}$')?.matcher(student?.getParentMobile())?.matches()){
            return JSONUtil.returnFailReuslt("家长电话号码有误")
        }else if(student?.getParentEmail() && !Pattern.compile('^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+')?.matcher(student?.getParentEmail())?.matches()){
            return JSONUtil.returnFailReuslt("家长邮箱有误")
        }

        try{
            if(studentService?.updateProfile(student)){
                //更新AuthenticationInfo
                Student authenticationInfo = (Student)SecurityUtils?.getSubject()?.getPrincipal()
                authenticationInfo?.setName(student?.getName())
                authenticationInfo?.setSex(student?.getSex())
                authenticationInfo?.setAge(student?.getAge())
                authenticationInfo?.setMobile(student?.getMobile())
                authenticationInfo?.setEmail(student?.getEmail())
                authenticationInfo?.setParentMobile(student?.getParentMobile())
                authenticationInfo?.setParentEmail(student?.getParentEmail())
                //重新存入session
                SecurityUtils.getSubject()?.getSession()?.setAttribute("student",authenticationInfo)
                return JSONUtil.returnSuccessResult("修改成功")
            }else{
                return JSONUtil.returnFailReuslt("修改失败")
            }
        }catch (Exception e){
            logger.info("修改Student错误：" + e.getMessage())
            return JSONUtil.returnFailReuslt("修改失败，请重试")
        }
    }
}
