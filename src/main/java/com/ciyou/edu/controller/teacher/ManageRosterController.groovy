package com.ciyou.edu.controller.teacher

import com.ciyou.edu.entity.Classes
import com.ciyou.edu.entity.PageInfo
import com.ciyou.edu.entity.Roster
import com.ciyou.edu.service.ClassesService
import com.ciyou.edu.service.RosterService
import com.ciyou.edu.utils.JSONUtil
import com.github.pagehelper.Page
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
 * @Date 2018-02-23 21:36
 */
@Controller
class ManageRosterController {
    private static final Logger logger = LoggerFactory.getLogger(ManageRosterController.class)
    @Autowired
    private RosterService rosterService

    @Autowired
    private ClassesService classesService

    @RequestMapping("/teacher/manageRoster")
    ModelAndView findRosterByPage(Integer page){
        if(page == null){
            page = 1
        }
        ModelAndView mv = new ModelAndView("teacher/manageRoster")
        logger.info("findRosterByPage : 查询第${page}页")
        //不赋值pageSize，默认为10
        Page<Roster> videos = rosterService?.findByPage(page)
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Roster> pageInfo = new PageInfo<Roster>(videos)
        pageInfo?.setUrl("/teacher/manageRoster?")
        mv?.addObject("pageInfo",pageInfo)
        return mv
    }


    @RequestMapping(value="/teacher/getClassesByGrade",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String getClassesByGrade(Integer gradeId){
        if(gradeId == null){
            return JSONUtil.returnFailReuslt("请选择年级")
        }
        List<Classes> classess = classesService?.getClassesByGrade(gradeId)
        logger.info("获得指定的Classes列表：" + classess)
        //这里要转为json对象，前端ajax才解析的了
        return JSONUtil.returnEntityReuslt(classess)
    }


    @RequestMapping(value="/teacher/getRosterById",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String getRosterById(Integer rid){
        Roster roster = rosterService?.getRosterById(rid)
        logger.info("获得指定的Roster：" + roster)
        return JSONUtil.returnEntityReuslt(roster)
    }


    @RequestMapping(value="/teacher/addRoster",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String addRoster(Integer classesId){
        //校验数据
        if(classesId == null || classesId == 0){
            return JSONUtil.returnFailReuslt("请选择班级")
        }
        else{
            try{
                //一个班只能有一个科目的老师
                if(rosterService?.addRoster(classesId)){
                    return JSONUtil.returnSuccessResult("添加成功")
                }else{
                    return JSONUtil.returnFailReuslt("添加失败")
                }
            }catch (Exception e){
                logger.info("添加Roster错误：" + e.getMessage())
                if(e.getMessage()?.contains("Duplicate entry")){
                    return JSONUtil.returnFailReuslt("一个班只能有一个科目的老师")
                }else{
                    return JSONUtil.returnFailReuslt("添加失败，请重试")
                }
            }
        }
    }


    @RequestMapping(value="/teacher/updateRoster",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String updateRoster(Integer classesId){
        //校验数据
        if(classesId == null || classesId == 0){
            return JSONUtil.returnFailReuslt("请选择班级")
        }
        else{
            try{
                //一个班只能有一个科目的老师
                if(rosterService?.updateRoster(classesId)){
                    return JSONUtil.returnSuccessResult("修改成功")
                }else{
                    return JSONUtil.returnFailReuslt("修改失败")
                }
            }catch (Exception e){
                logger.info("修改Roster错误：" + e.getMessage())
                if(e.getMessage()?.contains("Duplicate entry")){
                    return JSONUtil.returnFailReuslt("一个班只能有一个科目的老师")
                }else{
                    return JSONUtil.returnFailReuslt("修改失败，请重试")
                }
            }
        }
    }


    @RequestMapping(value="/teacher/deleteRoster", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String deleteRoster(Integer rid){
        try{
            if(rosterService?.deleteRoster(rid)){
                return JSONUtil.returnSuccessResult("删除成功")
            }else{
                return JSONUtil.returnFailReuslt("删除失败")
            }
        }catch (Exception e){
            logger.info("删除Roster错误：" + e.getMessage())
            return JSONUtil.returnFailReuslt("删除失败，请重试")
        }
    }
}
