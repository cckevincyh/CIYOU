package com.ciyou.edu.controller.teacher

import com.ciyou.edu.entity.PageInfo
import com.ciyou.edu.entity.Roster
import com.ciyou.edu.service.RosterService
import com.github.pagehelper.Page
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

/**
 * @Author C.
 * @Date 2018-03-06 11:36
 */
@Controller
class AnalyseClassesController {

    private static final Logger logger = LoggerFactory.getLogger(AnalyseClassesController.class)
    @Autowired
    private RosterService rosterService

    @RequestMapping("/teacher/analyseClasses")
    ModelAndView findAnalyseClassesByPage(Integer page){
        if(page == null){
            page = 1
        }
        ModelAndView mv = new ModelAndView("teacher/analyseClasses")
        logger.info("findAnalyseClassesByPage : 查询第${page}页")
        //不赋值pageSize，默认为10
        Page<Roster> videos = rosterService?.findByPage(page)
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Roster> pageInfo = new PageInfo<Roster>(videos)
        pageInfo?.setUrl("/teacher/analyseClasses?")
        mv?.addObject("pageInfo",pageInfo)
        return mv
    }


    @RequestMapping("/teacher/classesAnalyse")
    ModelAndView classesAnalyse(Integer rid){
        ModelAndView mv = new ModelAndView("teacher/classesAnalyse")
        Roster roster = rosterService?.getRosterById(rid)
        mv?.addObject("roster",roster)
        return mv
    }

}
