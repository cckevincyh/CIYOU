package com.ciyou.edu.controller.teacher

import com.ciyou.edu.entity.IncorrectAnswer
import com.ciyou.edu.entity.PageInfo
import com.ciyou.edu.entity.Quiz
import com.ciyou.edu.service.AnswerService
import com.ciyou.edu.service.ChoiceService
import com.ciyou.edu.service.JudgeService
import com.ciyou.edu.service.QuizService
import com.ciyou.edu.utils.JSONUtil
import com.ciyou.edu.utils.RGBAUtil
import com.ciyou.edu.utils.Utils
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
 * @Date 2018-03-06 11:37
 */
@Controller
class AnalyseQuizController {

    private static final Logger logger = LoggerFactory.getLogger(AnalyseQuizController.class)

    @Autowired
    private QuizService quizService

    @Autowired
    private AnswerService answerService

    @Autowired
    private ChoiceService choiceService

    @Autowired
    private JudgeService judgeService

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


    @RequestMapping("/teacher/quizAnalyse")
    ModelAndView quizAnalyse(Integer quizId){
        ModelAndView mv = new ModelAndView("teacher/quizAnalyse")
        Quiz quiz = quizService?.getQuizById(quizId)
        mv?.addObject("quiz",quiz)
        return mv
    }


    @RequestMapping(value="/teacher/queryAnalyseQuiz")
    ModelAndView queryAnalyseQuiz(String searchContent,Integer page){
        if(page == null){
            page = 1
        }
        if(!searchContent || searchContent?.trim() == ""){
            ModelAndView mv = new ModelAndView("redirect:analyseQuiz")
            return mv
        }else{
            ModelAndView mv = new ModelAndView("/teacher/analyseQuiz")
            logger.info("queryAnalyseQuiz : 查询第${page}页，携带查询参数=${searchContent}")
            //不赋值pageSize，默认为10
            Page<Quiz> quizs = quizService?.queryQuizByPage(searchContent?.trim(),page)
            // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
            PageInfo<Quiz> pageInfo = new PageInfo<Quiz>(quizs)
            pageInfo?.setUrl("/teacher/queryAnalyseQuiz?searchContent=${searchContent}&")
            mv?.addObject("pageInfo",pageInfo)
            return mv
        }

    }


    @RequestMapping(value="/teacher/getAnalyseByQuiz",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String getAnalyseByQuiz(Integer quizId){
        List<IncorrectAnswer> incorrectAnswerList = answerService?.getIncorrectAnswers(quizId)
        logger?.info("错题集合:" + incorrectAnswerList)
        Map<String,Object> map = new HashMap<String, Object>()
        List<String> incorrectLabels = new ArrayList<String>()
        List<Map<String,Object>> datasets = new ArrayList<Map<String,Object>>()
        for(IncorrectAnswer incorrectAnswer : incorrectAnswerList){
            if(incorrectAnswer?.getQuestionType() == 1){
                //选择题
                incorrectLabels << choiceService?.getChoice(incorrectAnswer?.getQuestion())?.getQuestion() + "(选择题)"
            }else if(incorrectAnswer?.getQuestionType() == 2){
                incorrectLabels << judgeService?.getJudge(incorrectAnswer?.getQuestion())?.getQuestion() + "(判断题)"
            }
        }
        map?.put("labels",incorrectLabels)
        datasets << Utils.getIncorrectMap(incorrectAnswerList)
        map?.put("datasets",datasets)
        logger.info(JSONUtil.returnEntityReuslt(map))
        return JSONUtil.returnEntityReuslt(map)

    }



    @RequestMapping(value="/teacher/getDonutChartByQuiz",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String getDonutChartByQuiz(Integer quizId){
        List<IncorrectAnswer> incorrectAnswerList = answerService?.getIncorrectAnswers(quizId)
        logger?.info("错题集合:" + incorrectAnswerList)
        List<Map<String,String>> incorrectList = new ArrayList<Map<String,String>>()
        for(IncorrectAnswer incorrectAnswer : incorrectAnswerList){
            Map<String,String> data = new HashMap<String,String>()
            String rgba = RGBAUtil.randomColor()
            if(incorrectAnswer?.getQuestionType() == 1){
                //选择题
                data?.put("label",choiceService?.getChoice(incorrectAnswer?.getQuestion())?.getQuestion() + "(选择题)")
            }else if(incorrectAnswer?.getQuestionType() == 2){
                data?.put("label",judgeService?.getJudge(incorrectAnswer?.getQuestion())?.getQuestion() + "(判断题)")
            }
            data?.put("value",incorrectAnswer?.getIncorrectNum()?.toString())
            data?.put("color",rgba)
            data?.put("highlight",rgba)
            incorrectList << data
        }
        logger.info(JSONUtil.returnEntityReuslt(incorrectList))
        return JSONUtil.returnEntityReuslt(incorrectList)
    }




}
