package com.ciyou.edu.controller.student

import com.ciyou.edu.entity.PageInfo
import com.ciyou.edu.entity.Quiz
import com.ciyou.edu.entity.Score
import com.ciyou.edu.entity.Student
import com.ciyou.edu.service.QuizService
import com.ciyou.edu.service.ScoreService
import com.ciyou.edu.service.StudentService
import com.ciyou.edu.utils.JSONUtil
import com.github.pagehelper.Page
import org.apache.shiro.SecurityUtils
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

    @Autowired
    private StudentService studentService

    @Autowired
    private ScoreService scoreService

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


    @RequestMapping(value="/student/getLockState",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String getLockState(Integer quizId,Integer sid){
        Student student = studentService?.getStudentById(sid?.toString())
        Quiz quiz = quizService?.getQuizById(quizId)
        Score score = scoreService?.getScore(sid, quizId)
        if(quiz?.getGrade()?.getGradeId() != student?.getClasses()?.getGrade()?.getGradeId()){
            return JSONUtil.returnFailReuslt("你不是" + quiz?.getGrade()?.getGradeName() + "的学生,不能进行该小测")
        }else if(score != null){
            //该小测练习已经做过了
            return JSONUtil.returnFailReuslt("该小测练习已经做过了")
        }else{
            //该小测练习没做过，或者正在做着小测练习
            //判断是否正在做小测
            //锁住的状态是否等于当前科目,除了当前科目可以继续考试，不能进行其他小测练习
            if(!(student?.getLockState() == quizId || student?.getLockState() == 0)){
                //正在考试
                return JSONUtil.returnFailReuslt("正在小测,请继续小测")
            }else{
                //允许进入做测练习
                //修改学生锁住状态,设置为当前小测练习科目
                studentService.updateStudentLockState(sid,quizId)
                return JSONUtil.returnSuccessResult("允许进入做小测练习")
            }
        }
    }

    @RequestMapping(value="/student/exam")
    @ResponseBody
    ModelAndView getQuestions(Integer quizId){
        Student stu = (Student)SecurityUtils?.getSubject()?.getPrincipal()
        Student student = studentService?.getStudentById(stu?.getSid()?.toString())
        Score score = scoreService.getScore(student?.getSid(), quizId)
        if(score != null){
            //该小测练习已经做过了
            return null
        }else{
            //该小测练习没做过，或者正在做着小测练习
            //判断是否正在做小测练习
            //锁住的状态是否等于当前科目,除了当前科目可以继续考试，不能进行其他小测练习
            if(!(student?.getLockState() == quizId || student?.getLockState() == 0)){
                //正在小测练习
                return null
            }else{
                Quiz quiz = quizService?.getQuizById(quizId)
                ModelAndView mv = new ModelAndView("student/exam")
                mv?.addObject("quiz",quiz)
                return mv
            }
        }
    }

    @RequestMapping(value="/student/quizExam",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String quizExam(Integer quizId,Integer sid,String answer){
        logger.info("学生ID：" + sid + ",小测ID：" + quizId + ",答案：" + answer)
        boolean b = quizService.quizExam(sid,quizId,answer)
        if(b){
            studentService?.updateStudentLockState(sid,0)
            return JSONUtil.returnSuccessResult("交卷成功")
        }else{
            return JSONUtil.returnFailReuslt("交卷失败")
        }
    }

    @RequestMapping(value="/student/queryQuiz")
    ModelAndView studentQueryQuiz(String searchContent,Integer page){
        if(page == null){
            page = 1
        }
        if(!searchContent || searchContent?.trim() == ""){
            ModelAndView mv = new ModelAndView("redirect:quiz")
            return mv
        }else{
            ModelAndView mv = new ModelAndView("/student/quiz")
            logger.info("queryQuiz : 查询第${page}页，携带查询参数=${searchContent}")
            //不赋值pageSize，默认为10
            Page<Quiz> quizs = quizService?.queryQuizByPage(searchContent?.trim(),page)
            // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
            PageInfo<Quiz> pageInfo = new PageInfo<Quiz>(quizs)
            pageInfo?.setUrl("/student/queryQuiz?searchContent=${searchContent}&")
            mv?.addObject("pageInfo",pageInfo)
            return mv
        }

    }
}
