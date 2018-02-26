package com.ciyou.edu.controller.teacher

import com.ciyou.edu.entity.PageInfo
import com.ciyou.edu.entity.Quiz
import com.ciyou.edu.entity.Teacher
import com.ciyou.edu.service.QuizService
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
 * @Date 2018-02-24 23:20
 */
@Controller
class ManageQuizController {

    private static final Logger logger = LoggerFactory.getLogger(ManageQuizController.class)

    @Autowired
    private QuizService quizService

    @RequestMapping("/teacher/manageQuiz")
    ModelAndView findQuizByPage(Integer page){
        if(page == null){
            page = 1
        }
        ModelAndView mv = new ModelAndView("teacher/manageQuiz")
        logger.info("findQuizByPage : 查询第${page}页")
        //不赋值pageSize，默认为10
        Page<Quiz> quizs = quizService?.findByPage(page)
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Quiz> pageInfo = new PageInfo<Quiz>(quizs)
        pageInfo?.setUrl("/teacher/manageQuiz?")
        mv?.addObject("pageInfo",pageInfo)
        return mv
    }


    @RequestMapping(value="/teacher/addQuiz",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String addQuiz(Quiz quiz){
        logger.info("添加的小测Quiz：" + quiz)
        //校验数据
        if(!quiz?.getQuizName() || quiz?.getQuizName()?.trim() == ""){
            return JSONUtil.returnFailReuslt("小测名称不能为空")
        }else if(quiz?.getGrade() == null || quiz?.getGrade()?.getGradeId() == 0){
            return JSONUtil.returnFailReuslt("请选择年级")
        }else if(quiz?.getSubject() == null || quiz?.getSubject()?.getSubjectId() == 0){
            return JSONUtil.returnFailReuslt("请选择科目")
        }else if(quiz?.getQuizTime() == null){
            return JSONUtil.returnFailReuslt("请输入小测时间")
        }else if(quiz?.getChoiceScore() == null){
            return JSONUtil.returnFailReuslt("请输入选择题分数")
        }else if(quiz?.getJudgeScore() == null){
            return JSONUtil.returnFailReuslt("请输入判断题分数")
        }else{
            try{
                //获得当前教师
                Teacher teacher = (Teacher)SecurityUtils.getSubject()?.getPrincipal()
                quiz.setTeacher(teacher)
                if(quizService?.addQuiz(quiz)){
                    return JSONUtil.returnSuccessResult("添加成功")
                }else{
                    return JSONUtil.returnFailReuslt("添加失败")
                }
            }catch (Exception e){
                logger.info("添加Quiz错误：" + e.getMessage())
                return JSONUtil.returnFailReuslt("添加失败，请重试")
            }
        }
    }


    @RequestMapping(value="/teacher/getQuizById",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String getQuizById(Integer quizId){
        Quiz quiz = quizService?.getQuizById(quizId)
        logger.info("获得指定的Quiz：" + quiz)
        return JSONUtil.returnEntityReuslt(quiz)
    }

    @RequestMapping(value="/teacher/updateQuiz",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String updateQuiz(Quiz quiz){
        logger.info("修改的小测Quiz：" + quiz)
        //校验数据
        if(!quiz?.getQuizName() || quiz?.getQuizName()?.trim() == ""){
            return JSONUtil.returnFailReuslt("小测名称不能为空")
        }else if(quiz?.getGrade() == null || quiz?.getGrade()?.getGradeId() == 0){
            return JSONUtil.returnFailReuslt("请选择年级")
        }else if(quiz?.getSubject() == null || quiz?.getSubject()?.getSubjectId() == 0){
            return JSONUtil.returnFailReuslt("请选择科目")
        }else if(quiz?.getQuizTime() == null){
            return JSONUtil.returnFailReuslt("请输入小测时间")
        }else if(quiz?.getChoiceScore() == null){
            return JSONUtil.returnFailReuslt("请输入选择题分数")
        }else if(quiz?.getJudgeScore() == null){
            return JSONUtil.returnFailReuslt("请输入判断题分数")
        }else{
            try{
                if(quizService?.updateQuiz(quiz)){
                    return JSONUtil.returnSuccessResult("修改成功")
                }else{
                    return JSONUtil.returnFailReuslt("修改失败")
                }
            }catch (Exception e){
                logger.info("修改Quiz错误：" + e.getMessage())
                return JSONUtil.returnFailReuslt("修改失败，请重试")
            }
        }
    }


    @RequestMapping(value="/teacher/deleteQuiz", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String deleteQuiz(Integer quizId){
        try{
            if(quizService?.deleteQuiz(quizId)){
                return JSONUtil.returnSuccessResult("删除成功")
            }else{
                return JSONUtil.returnFailReuslt("删除失败")
            }
        }catch (Exception e){
            logger.info("删除Quiz错误：" + e.getMessage())
            return JSONUtil.returnFailReuslt("删除失败，请重试")
        }
    }


    @RequestMapping(value="/teacher/queryQuiz")
    ModelAndView queryQuiz(String searchContent,Integer page){
        if(page == null){
            page = 1
        }
        if(!searchContent || searchContent?.trim() == ""){
            ModelAndView mv = new ModelAndView("redirect:manageQuiz")
            return mv
        }else{
            ModelAndView mv = new ModelAndView("/teacher/manageQuiz")
            logger.info("queryQuiz : 查询第${page}页，携带查询参数=${searchContent}")
            //不赋值pageSize，默认为10
            Page<Quiz> quizs = quizService?.queryQuizByPage(searchContent?.trim(),page)
            // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
            PageInfo<Quiz> pageInfo = new PageInfo<Quiz>(quizs)
            pageInfo?.setUrl("/teacher/queryQuiz?searchContent=${searchContent}&")
            mv?.addObject("pageInfo",pageInfo)
            return mv
        }

    }
}
