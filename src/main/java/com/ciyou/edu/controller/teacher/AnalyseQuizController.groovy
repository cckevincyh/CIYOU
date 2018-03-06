package com.ciyou.edu.controller.teacher

import com.ciyou.edu.entity.PageInfo
import com.ciyou.edu.entity.Quiz
import com.ciyou.edu.service.QuizService
import com.github.pagehelper.Page
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

/**
 * @Author C.
 * @Date 2018-03-06 11:37
 */
@Controller
class AnalyseQuizController {

    private static final Logger logger = LoggerFactory.getLogger(AnalyseQuizController.class)

    @Autowired
    private QuizService quizService

    @RequestMapping("/teacher/analyseQuiz")
    ModelAndView findAnalyseQuizByPage(Integer page){
        if(page == null){
            page = 1
        }
        ModelAndView mv = new ModelAndView("teacher/analyseQuiz")
        logger.info("findAnalyseQuizByPage : 查询第${page}页")
        //不赋值pageSize，默认为10
        Page<Quiz> quizs = quizService?.findByPage(page)
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Quiz> pageInfo = new PageInfo<Quiz>(quizs)
        pageInfo?.setUrl("/teacher/analyseQuiz?")
        mv?.addObject("pageInfo",pageInfo)
        return mv
    }
}
