package com.ciyou.edu.controller.teacher

import com.ciyou.edu.entity.Subject
import com.ciyou.edu.entity.Teacher
import com.ciyou.edu.service.SubjectService
import com.ciyou.edu.service.TeacherService
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
class profileController {

    private static final Logger logger = LoggerFactory.getLogger(profileController.class)
    @Autowired
    private SubjectService subjectService

    @Autowired
    private TeacherService teacherService

    @RequestMapping("/teacher/profile")
    String profile(){
        return "/teacher/profile"
    }

    @RequestMapping(value="/teacher/getAllSubject",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String getAllSubject(){
        List<Subject> subjectList = subjectService?.getAllSubject()
        return JSONUtil.returnEntityReuslt(subjectList)
    }

    @RequestMapping(value="/teacher/getTeacherByTid", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String getTeacherByTid(Integer tid){
        return JSONUtil.returnEntityReuslt(teacherService?.getTeacherByTid(tid))
    }


    @RequestMapping(value="/teacher/updateProfile", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String updateProfile(Teacher teacher, Integer subjectId){
        logger.info("修改Teacher信息：" + teacher + "科目ID：" + subjectId)
        //校验提交的Teacher
        if(!teacher?.getName() || teacher?.getName()?.trim() == ""){
            return JSONUtil.returnFailReuslt("教师姓名不能为空")
        }else if(!Pattern.compile("[\\u4E00-\\u9FFF]+")?.matcher(teacher?.getName())?.matches()){
            return JSONUtil.returnFailReuslt("教师姓名必须为中文")
        }else if(subjectId == null || subjectId == 0){
            return JSONUtil.returnFailReuslt("科目不能为空")
        }else if(teacher?.getMobile() && !Pattern.compile('^1[34578]\\d{9}$')?.matcher(teacher?.getMobile())?.matches()){
            return JSONUtil.returnFailReuslt("电话号码有误")
        }else if(teacher?.getEmail() && !Pattern.compile('^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+')?.matcher(teacher?.getEmail())?.matches()){
            return JSONUtil.returnFailReuslt("邮箱有误")
        }
        Subject subject = new Subject()
        subject.setSubjectId(subjectId)
        teacher.setSubject(subject)

        try{
            if(teacherService?.updateProfile(teacher)){
                //重新存入session
                SecurityUtils.getSubject()?.getSession()?.setAttribute("teacher",teacher)
                //更新AuthenticationInfo
                Teacher authenticationInfo = (Teacher)SecurityUtils?.getSubject()?.getPrincipal()
                authenticationInfo?.setName(teacher?.getName())
                authenticationInfo?.setSubject(teacher?.getSubject())
                authenticationInfo?.setSex(teacher?.getSex())
                authenticationInfo?.setMobile(teacher?.getMobile())
                authenticationInfo?.setEmail(teacher?.getEmail())
                return JSONUtil.returnSuccessResult("修改成功")
            }else{
                return JSONUtil.returnFailReuslt("修改失败")
            }
        }catch (Exception e){
            logger.info("修改Teacher错误：" + e.getMessage())
            return JSONUtil.returnFailReuslt("修改失败，请重试")
        }
    }


    @RequestMapping(value="/teacher/updatePassword", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String updatePassword(String oldPwd, String newPwd, String confirmPwd){
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
        Teacher teacher = (Teacher)SecurityUtils.getSubject()?.getPrincipal()
        //旧密码加密
        String oldPasswordMd5= new Md5Hash(oldPwd,teacher?.getTeacherId(),2).toHex()
        //比对原密码是否正确
        if(oldPasswordMd5 != teacher?.getPassword()){
            return JSONUtil.returnFailReuslt("原密码错误")
        }else{
            //新密码加密
            String passwordMd5 = new Md5Hash(newPwd,teacher?.getTeacherId(),2).toHex()
            try{
                if(teacherService?.updatePassword(teacher?.getTid(),passwordMd5)){
                    //登出
                    SecurityUtils.getSubject()?.logout()
                    return JSONUtil.returnSuccessResult("修改密码成功")
                }else{
                    return JSONUtil.returnFailReuslt("修改密码失败")
                }
            }catch (Exception e){
                logger.info("修改Teacher密码错误：" + e.getMessage())
                return JSONUtil.returnFailReuslt("修改密码失败，请重试")
            }
        }
    }

    @RequestMapping(value="/teacher/ImgFileUpload", method=RequestMethod.POST)
    @ResponseBody
    String ImgFileUpload(@RequestParam MultipartFile file, HttpServletRequest request){
        logger.info("头像上传....")
        //获取文件名
        String originalFilename = file.getOriginalFilename()
        logger.info("上传文件名：" + originalFilename)
        String realPath = request.getServletContext().getRealPath("/public/teacher/")
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
            Teacher teacher = (Teacher)SecurityUtils.getSubject()?.getSession()?.getAttribute("teacher")
            teacher?.setPicImg("/public/teacher/" + uploadFileName)
            //成功->修改教师头像
            if(teacherService?.updatePicImg(teacher?.getTid(),teacher?.getPicImg())){
                //重新存入session
                SecurityUtils.getSubject()?.getSession()?.setAttribute("teacher",teacher)
                //更新AuthenticationInfo
                Teacher authenticationInfo = (Teacher)SecurityUtils?.getSubject()?.getPrincipal()
                authenticationInfo?.setPicImg(teacher?.getPicImg())
            }
            return "success"
        }else{
            return "fail"
        }

    }

}
