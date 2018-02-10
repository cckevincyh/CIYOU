package com.ciyou.edu.controller.admin

import com.ciyou.edu.entity.Classes
import com.ciyou.edu.entity.PageInfo
import com.ciyou.edu.service.ClassesService
import com.github.pagehelper.Page
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


    @RequestMapping(value="/admin/addClasses",method=RequestMethod.POST)
    @ResponseBody
    String addClasses(Integer gradeId,Integer classes){
        //校验数据
        if(gradeId == null || gradeId == 0){
            return "请选择年级"
        }else if(classes == null){
            return "请输入班级"
        }else if(!Pattern.compile( '^[1-9]+\\d*$')?.matcher(classes?.toString())?.matches()){
            return "班级必须为正整数"
        }
        else{
            try{
                if(classesService?.addClasses(gradeId,classes)){
                    return "添加成功"
                }else{
                    return "添加失败"
                }
            }catch (Exception e){
                logger.info("添加Classes错误：" + e.getMessage())
                return "添加失败，请重试"
            }
        }
    }
}
