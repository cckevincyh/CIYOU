package com.ciyou.edu.controller.teacher

import com.ciyou.edu.entity.Choice
import com.ciyou.edu.entity.Judge
import com.ciyou.edu.entity.Quiz
import com.ciyou.edu.service.ChoiceService
import com.ciyou.edu.service.JudgeService
import com.ciyou.edu.service.QuizService
import com.ciyou.edu.utils.JSONUtil
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
 * @Date 2018-02-26 16:06
 */
@Controller
class ManageExamController {
    private static final Logger logger = LoggerFactory.getLogger(ManageExamController.class)

    @Autowired
    private QuizService quizService

    @Autowired
    private ChoiceService choiceService

    @Autowired
    private JudgeService judgeService

    @RequestMapping("/teacher/manageExam")
    ModelAndView manageExam(Integer quizId){
        ModelAndView mv = new ModelAndView("teacher/manageExam")
        Quiz quiz = quizService?.getQuizById(quizId)
        mv?.addObject("quiz",quiz)
        return mv
    }



    @RequestMapping(value="/teacher/addChoice",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String addChoice(Choice choice){
        logger.info("添加的选择题：" + choice)
        //校验数据
        if(!choice?.getQuestion() || choice?.getQuestion()?.trim() == "") {
            return JSONUtil.returnFailReuslt("请输入选择题问题")
        }else if(!choice?.getOptionA() || choice?.getOptionA()?.trim() == "") {
            return JSONUtil.returnFailReuslt("请输入选项A")
        }else if(!choice?.getOptionB() || choice?.getOptionB()?.trim() == ""){
            return JSONUtil.returnFailReuslt("请输入选项B")
        }else if(!choice?.getOptionC() || choice?.getOptionC()?.trim() == ""){
            return JSONUtil.returnFailReuslt("请输入选项C")
        }else if(!choice?.getOptionD() || choice?.getOptionD()?.trim() == ""){
            return JSONUtil.returnFailReuslt("请输入选项D")
        }else if(!choice?.getAnswer() || choice?.getAnswer()?.trim() == "0"){
            return JSONUtil.returnFailReuslt("请输入答案")
        }else{
            try{
                if(choiceService?.addChoice(choice)){
                    return JSONUtil.returnSuccessResult("添加成功")
                }else{
                    return JSONUtil.returnFailReuslt("添加失败")
                }
            }catch (Exception e){
                logger.info("添加Choice错误：" + e.getMessage())
                return JSONUtil.returnFailReuslt("添加失败，请重试")
            }
        }
    }

    @RequestMapping(value="/teacher/addJudge",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String addJudge(Judge judge){
        logger.info("添加的判断题：" + judge)
        //校验数据
        if(!judge?.getQuestion() || judge?.getQuestion()?.trim() == ""){
            return JSONUtil.returnFailReuslt("请输入判断题问题")
        }else if(!judge?.getAnswer() || judge?.getAnswer()?.trim() == ""){
            return JSONUtil.returnFailReuslt("请输入判断题答案")
        }else{
            try{
                if(judgeService?.addJudge(judge)){
                    return JSONUtil.returnSuccessResult("添加成功")
                }else{
                    return JSONUtil.returnFailReuslt("添加失败")
                }
            }catch (Exception e){
                logger.info("添加Judge错误：" + e.getMessage())
                return JSONUtil.returnFailReuslt("添加失败，请重试")
            }
        }
    }


    @RequestMapping(value="/teacher/getChoice",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String getChoice(Integer choiceId){
        Choice choice = choiceService?.getChoice(choiceId)
        logger.info("获得指定的Choice：" + choice)
        return JSONUtil.returnEntityReuslt(choice)
    }

    @RequestMapping(value="/teacher/getJudge",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String getJudge(Integer judgeId){
        Judge judge = judgeService?.getJudge(judgeId)
        logger.info("获得指定的Judge：" + judge)
        return JSONUtil.returnEntityReuslt(judge)
    }
}
