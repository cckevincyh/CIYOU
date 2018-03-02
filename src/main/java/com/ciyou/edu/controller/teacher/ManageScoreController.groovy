package com.ciyou.edu.controller.teacher

import com.ciyou.edu.entity.PageInfo
import com.ciyou.edu.entity.ResultScore
import com.ciyou.edu.entity.Score
import com.ciyou.edu.entity.Teacher
import com.ciyou.edu.service.ScoreService
import com.github.pagehelper.Page
import org.apache.shiro.SecurityUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

/**
 * @Author C.
 * @Date 2018-03-02 13:58
 */
@Controller
class ManageScoreController {
    private static final Logger logger = LoggerFactory.getLogger(ManageScoreController.class)

    @Autowired
    private ScoreService scoreService

    @RequestMapping("/teacher/manageScore")
    ModelAndView manageScore(Integer page){
        if(page == null){
            page = 1
        }
        ModelAndView mv = new ModelAndView("teacher/manageScore")
        logger.info("manageScore : 查询第${page}页")
        //获取当前教师
        Teacher teacher = (Teacher)SecurityUtils.getSubject()?.getPrincipal()
        //不赋值pageSize，默认为10
        Page<Score> scores = scoreService?.findScoreByPage(teacher?.getTid(),page)
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Score> pageInfo = new PageInfo<Score>(scores)
        pageInfo?.setUrl("/teacher/manageScore?")
        mv?.addObject("pageInfo",pageInfo)
        return mv
    }



    @RequestMapping(value="/teacher/queryScore")
    ModelAndView queryScore(String searchContent,Integer page){
        if(page == null){
            page = 1
        }
        if(!searchContent || searchContent?.trim() == ""){
            ModelAndView mv = new ModelAndView("redirect:manageScore")
            return mv
        }else{
            ModelAndView mv = new ModelAndView("/teacher/manageScore")
            logger.info("studentQueryScore : 查询第${page}页，携带查询参数=${searchContent}")
            //获取当前教师
            Teacher teacher = (Teacher)SecurityUtils.getSubject()?.getPrincipal()
            //不赋值pageSize，默认为10
            Page<Score> scores = scoreService?.queryScoreByPage(teacher?.getTid(),searchContent?.trim(),page)
            // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
            PageInfo<Score> pageInfo = new PageInfo<Score>(scores)
            pageInfo?.setUrl("/teacher/queryScore?searchContent=${searchContent}&")
            mv?.addObject("pageInfo",pageInfo)
            return mv
        }

    }


    @RequestMapping("/teacher/getScore")
    ModelAndView teacherGetScore(Integer scoreId){
        ModelAndView mv = new ModelAndView("teacher/getScore")
        ResultScore resultScore = scoreService.getResultScore(scoreId)
        mv?.addObject("resultScore",resultScore)
        return mv
    }
}
