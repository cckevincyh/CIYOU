package com.ciyou.edu.controller.teacher

import com.ciyou.edu.entity.AvgScore
import com.ciyou.edu.entity.PageInfo
import com.ciyou.edu.entity.Roster
import com.ciyou.edu.service.RosterService
import com.ciyou.edu.service.ScoreService
import com.ciyou.edu.utils.JSONUtil
import com.ciyou.edu.utils.RGBAUtil
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
 * @Date 2018-03-06 11:36
 */
@Controller
class AnalyseClassesController {

    private static final Logger logger = LoggerFactory.getLogger(AnalyseClassesController.class)
    @Autowired
    private RosterService rosterService
    @Autowired
    private ScoreService scoreService

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


    @RequestMapping(value="/teacher/getSubjectAvgByClasses",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String getSubjectAvgByClasses(Integer classesId,Integer gradeId){
        logger.info("获取classesId=${classesId}的班级的各科小测平均分...")
        Map<String,Object> map = new HashMap<String, Object>()
        List<AvgScore> classesInfo = scoreService?.getSubjectAvgByClasses(classesId)
        map?.put("labels",classesInfo?.subjectName)
        List<Map<String,Object>> datasets = new ArrayList<Map<String,Object>>()
        datasets << getInfoMap(classesInfo,"该班级")
        List<AvgScore> gradeInfo = scoreService?.getSubjectAvgByGrade(gradeId)
        datasets << getInfoMap(gradeInfo,"全年级")
        map?.put("datasets",datasets)
        logger.info(JSONUtil.returnEntityReuslt(map))
        return JSONUtil.returnEntityReuslt(map)
    }


    private static Map<String, Object> getInfoMap(List<AvgScore> info,String label){
        Map<String,Object> infoMap = new HashMap<String,Object>()
        String rgba = RGBAUtil.randomColor()
        infoMap?.put("fillColor",rgba)
        infoMap?.put("strokeColor",rgba)
        infoMap?.put("pointColor",rgba)
        infoMap?.put("pointStrokeColor",rgba)
        infoMap?.put("pointHighlightFill",rgba)
        infoMap?.put("pointHighlightStroke",rgba)
        infoMap?.put("label",label)
        infoMap?.put("data",info?.avg)
        return infoMap
    }

}
