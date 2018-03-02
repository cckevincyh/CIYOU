package com.ciyou.edu.controller.student

import com.ciyou.edu.entity.PageInfo
import com.ciyou.edu.entity.ResultScore
import com.ciyou.edu.entity.Score
import com.ciyou.edu.entity.Student
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
 * @Date 2018-03-01 20:16
 */
@Controller
class ScoreController {
    private static final Logger logger = LoggerFactory.getLogger(ScoreController.class)

    @Autowired
    private ScoreService scoreService

    @RequestMapping("/student/score")
    ModelAndView findScoreByPage(Integer page){
        if(page == null){
            page = 1
        }
        ModelAndView mv = new ModelAndView("student/score")
        logger.info("findScoreByPage : 查询第${page}页")
        //获取当前学生
        Student student = (Student)SecurityUtils.getSubject()?.getPrincipal()
        //不赋值pageSize，默认为10
        Page<Score> scores = scoreService?.findMyScoreByPage(student?.getSid(),page)
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Score> pageInfo = new PageInfo<Score>(scores)
        pageInfo?.setUrl("/student/score?")
        mv?.addObject("pageInfo",pageInfo)
        return mv
    }


    @RequestMapping("/student/getScore")
    ModelAndView getScore(Integer scoreId){
        ModelAndView mv = new ModelAndView("student/getScore")
        ResultScore resultScore = scoreService.getResultScore(scoreId)
        mv?.addObject("resultScore",resultScore)
        return mv
    }

    @RequestMapping(value="/student/queryScore")
    ModelAndView studentQueryScore(String searchContent,Integer page){
        if(page == null){
            page = 1
        }
        if(!searchContent || searchContent?.trim() == ""){
            ModelAndView mv = new ModelAndView("redirect:score")
            return mv
        }else{
            ModelAndView mv = new ModelAndView("/student/score")
            logger.info("studentQueryScore : 查询第${page}页，携带查询参数=${searchContent}")
            //获取当前学生
            Student student = (Student)SecurityUtils.getSubject()?.getPrincipal()
            //不赋值pageSize，默认为10
            Page<Score> scores = scoreService?.queryMyScoreByPage(student?.getSid(),searchContent?.trim(),page)
            // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
            PageInfo<Score> pageInfo = new PageInfo<Score>(scores)
            pageInfo?.setUrl("/student/queryScore?searchContent=${searchContent}&")
            mv?.addObject("pageInfo",pageInfo)
            return mv
        }

    }
}
