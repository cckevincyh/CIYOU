package com.ciyou.edu.controller.admin

import com.ciyou.edu.entity.Subject
import com.ciyou.edu.service.SubjectService
import com.ciyou.edu.utils.JSONUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView

/**
 * @Author C.
 * @Date 2018-02-09 22:57
 */
@Controller
class ManageSubjectController {
    private static final Logger logger = LoggerFactory.getLogger(ManageSubjectController.class)

    @Autowired
    private SubjectService subjectService

    @RequestMapping("/admin/manageSubject")
    ModelAndView manageSubject(){
        ModelAndView mv = new ModelAndView("admin/manageSubject")
        List<Subject> subjectList = subjectService?.findAllSubject()
        mv?.addObject("subjects",subjectList)
        return mv
    }

    @RequestMapping(value="/admin/addSubject",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String addSubject(Subject subject){
        //校验数据
        if(!subject?.getSubjectName()|| subject?.getSubjectName()?.trim() == ""){
            return JSONUtil.returnFailReuslt("科目不能为空")
        }else{
            try{
                if(subjectService?.addSubject(subject)){
                    return JSONUtil.returnSuccessResult("添加成功")
                }else{
                    return JSONUtil.returnFailReuslt("添加失败")
                }
            }catch (Exception e){
                logger.info("添加Subject错误：" + e.getMessage())
                return JSONUtil.returnFailReuslt("添加失败，请重试")
            }
        }
    }


    @RequestMapping(value="/admin/updateSubject",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String updateSubject(Subject subject){
        //校验数据
        if(!subject?.getSubjectName() || subject?.getSubjectName()?.trim() == ""){
            return JSONUtil.returnFailReuslt("科目不能为空")
        }else{
            try{
                if(subjectService?.updateSubject(subject)){
                    return JSONUtil.returnSuccessResult("修改成功")
                }else{
                    return JSONUtil.returnFailReuslt("修改失败")
                }
            }catch (Exception e){
                logger.info("修改Subject错误：" + e.getMessage())
                return JSONUtil.returnFailReuslt("修改失败，请重试")
            }
        }
    }


    @RequestMapping(value="/admin/getSubject",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String getSubject(Integer subjectId){
        Subject subject = subjectService?.getSubject(subjectId)
        logger.info("获得指定的Grade：" + subject)
        //这里要转为json对象，前端ajax才解析的了
        return JSONUtil.returnEntityReuslt(subject)
    }


    @RequestMapping(value="/admin/deleteSubject",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String deleteSubject(Integer subjectId){
        try{
            if(subjectService?.deleteSubject(subjectId)){
                return JSONUtil.returnSuccessResult("删除成功")
            }else{
                return JSONUtil.returnFailReuslt("删除失败")
            }
        }catch (Exception e){
            logger.info("删除Subject错误：" + e.getMessage())
            return JSONUtil.returnFailReuslt("删除失败，请重试")
        }
    }

}
