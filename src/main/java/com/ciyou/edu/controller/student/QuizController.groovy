package com.ciyou.edu.controller.student

import com.ciyou.edu.entity.PageInfo
import com.ciyou.edu.entity.Quiz
import com.ciyou.edu.service.QuizService
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
 * @Date 2018-03-01 11:25
 */
@Controller
class QuizController {
    private static final Logger logger = LoggerFactory.getLogger(QuizController.class)

    @Autowired
    private QuizService quizService

    @RequestMapping("/student/quiz")
    ModelAndView findQuizByPage(Integer page){
        if(page == null){
            page = 1
        }
        ModelAndView mv = new ModelAndView("student/quiz")
        logger.info("findQuizByPage : 查询第${page}页")
        //不赋值pageSize，默认为10
        Page<Quiz> quizs = quizService?.findByPage(page)
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Quiz> pageInfo = new PageInfo<Quiz>(quizs)
        pageInfo?.setUrl("/student/quiz?")
        mv?.addObject("pageInfo",pageInfo)
        return mv
    }

    @RequestMapping(value="/student/getQuizById",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String getQuizById(Integer quizId){
        Quiz quiz = quizService?.getQuizById(quizId)
        logger.info("获得指定的Quiz：" + quiz)
        return JSONUtil.returnEntityReuslt(quiz)
    }
}
