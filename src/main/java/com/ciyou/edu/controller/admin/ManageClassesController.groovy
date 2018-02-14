package com.ciyou.edu.controller.admin

import com.ciyou.edu.entity.Classes
import com.ciyou.edu.entity.PageInfo
import com.ciyou.edu.service.ClassesService
import com.ciyou.edu.utils.JSONUtil
import com.github.pagehelper.Page
import net.sf.json.JSONObject
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
 * @Date 2018-02-10 9:24
 * 班级管理
 */
@Controller
class ManageClassesController {
    private static final Logger logger = LoggerFactory.getLogger(ManageClassesController.class)

    @Autowired
    private ClassesService classesService

    @RequestMapping("/admin/manageClasses")
    ModelAndView findClassesByPage(Integer page){
        if(page == null){
            page = 1
        }
        ModelAndView mv = new ModelAndView("admin/manageClasses")
        logger.info("findClassesByPage : 查询第${page}页")
        //不赋值pageSize，默认为10
        Page<Classes> classess = classesService?.findByPage(page)
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Classes> pageInfo = new PageInfo<Classes>(classess)
        pageInfo?.setUrl("/admin/manageClasses?")
        mv?.addObject("pageInfo",pageInfo)
        return mv
    }


    @RequestMapping(value="/admin/addClasses",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String addClasses(Integer gradeId,Integer classes){
        //校验数据
        if(gradeId == null || gradeId == 0){
            return JSONUtil.returnFailReuslt("请选择年级")
        }else if(classes == null){
            return JSONUtil.returnFailReuslt("请输入班级")
        }else if(!Pattern.compile( '^[1-9]+\\d*$')?.matcher(classes?.toString())?.matches()){
            return JSONUtil.returnFailReuslt("班级必须为正整数")
        }
        else{
            try{
                if(classesService?.addClasses(gradeId,classes)){
                    return JSONUtil.returnSuccessResult("添加成功")
                }else{
                    return JSONUtil.returnFailReuslt("添加失败")
                }
            }catch (Exception e){
                logger.info("添加Classes错误：" + e.getMessage())
                return JSONUtil.returnFailReuslt("添加失败，请重试")
            }
        }
    }


    @RequestMapping(value="/admin/getClasses",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String getClasses(Integer classesId){
        Classes classes = classesService?.getClasses(classesId)
        logger.info("获得指定的Classes：" + classes)
        //这里要转为json对象，前端ajax才解析的了
        return JSONUtil.returnEntityReuslt(JSONObject.fromObject(classes))
    }

    @RequestMapping(value="/admin/updateClasses",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String updateClasses(Integer classesId,Integer gradeId, Integer classes){
        //校验数据
        if(classesId == null){
            return JSONUtil.returnFailReuslt("ID不能为空")
        }else if(gradeId == null || gradeId == 0){
            return JSONUtil.returnFailReuslt("请选择年级")
        }else if(classes == null){
            return JSONUtil.returnFailReuslt("请输入班级")
        }else if(!Pattern.compile( '^[1-9]+\\d*$')?.matcher(classes?.toString())?.matches()){
            return JSONUtil.returnFailReuslt("班级必须为正整数")
        }
        else{
            try{
                if(classesService?.updateClasses(classesId,gradeId,classes)){
                    return JSONUtil.returnSuccessResult("修改成功")
                }else{
                    return JSONUtil.returnFailReuslt("修改失败")
                }
            }catch (Exception e){
                logger.info("添加Classes错误：" + e.getMessage())
                return JSONUtil.returnFailReuslt("修改失败，请重试")
            }
        }
    }


    @RequestMapping(value="/admin/deleteClasses",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String deleteClasses(Integer classesId){
        try{
            if(classesService?.deleteClasses(classesId)){
                return JSONUtil.returnSuccessResult("删除成功")
            }else{
                return JSONUtil.returnFailReuslt("删除失败")
            }
        }catch (Exception e){
            logger.info("删除Classes错误：" + e.getMessage())
            return JSONUtil.returnFailReuslt("删除失败，请重试")
        }
    }

    @RequestMapping("/admin/queryClasses")
    ModelAndView queryClasses(String searchContent,Integer page){
        if(page == null){
            page = 1
        }
        if(!searchContent || searchContent?.trim() == ""){
            //重定向到findAdminByPage Controller
            ModelAndView mv = new ModelAndView("redirect:manageClasses")
            return mv
        }else{
            ModelAndView mv = new ModelAndView("/admin/manageClasses")
            logger.info("queryClasses : 查询第${page}页，携带查询参数=${searchContent}")
            //不赋值pageSize，默认为10
            Page<Classes> classess = classesService?.queryClassesByPage(searchContent?.trim(),page)
            // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
            PageInfo<Classes> pageInfo = new PageInfo<Classes>(classess)
            pageInfo?.setUrl("/admin/queryClasses?searchContent=${searchContent}&")
            mv?.addObject("pageInfo",pageInfo)
            return mv
        }
    }

}
