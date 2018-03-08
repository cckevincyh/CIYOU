package com.ciyou.edu.controller.teacher

import com.ciyou.edu.entity.AvgScore
import com.ciyou.edu.entity.PageInfo
import com.ciyou.edu.entity.Student
import com.ciyou.edu.entity.Teacher
import com.ciyou.edu.service.ScoreService
import com.ciyou.edu.service.StudentService
import com.ciyou.edu.utils.JSONUtil
import com.ciyou.edu.utils.Utils
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
 * @Date 2018-03-07 19:58
 */
@Controller
class AnalyseStudentController {
    private static final Logger logger = LoggerFactory.getLogger(AnalyseStudentController.class)

    @Autowired
    private StudentService studentService
    @Autowired
    private ScoreService scoreService

    @RequestMapping("/teacher/analyseStudent")
    ModelAndView analyseStudent(Integer page){
        if(page == null){
            page = 1
        }
        ModelAndView mv = new ModelAndView("teacher/analyseStudent")
        logger.info("analyseStudent : 查询第${page}页")
        //获得当前老师
        Teacher teacher = (Teacher)SecurityUtils?.getSubject()?.getPrincipal()
        //不赋值pageSize，默认为10
        Page<Student> students = studentService?.findByTeacherAndPage(teacher?.getTid(),page)
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Student> pageInfo = new PageInfo<Student>(students)
        logger.info("查询结果：" + pageInfo )
        pageInfo?.setUrl("/teacher/analyseStudent?")
        mv?.addObject("pageInfo",pageInfo)
        return mv
    }


    @RequestMapping(value="/teacher/queryAnalyseStudent")
    ModelAndView queryAnalyseStudent(String searchContent,Integer page){
        if(page == null){
            page = 1
        }
        if(!searchContent || searchContent?.trim() == ""){
            ModelAndView mv = new ModelAndView("redirect:analyseStudent")
            return mv
        }else{
            ModelAndView mv = new ModelAndView("/teacher/analyseStudent")
            logger.info("queryAnalyseStudent : 查询第${page}页，携带查询参数=${searchContent}")
            //获得当前老师
            Teacher teacher = (Teacher)SecurityUtils?.getSubject()?.getPrincipal()
            //不赋值pageSize，默认为10
            Page<Student> students = studentService?.queryStudentByTeacherAndPage(teacher?.getTid(),searchContent?.trim(),page)
            // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
            PageInfo<Student> pageInfo = new PageInfo<Student>(students)
            pageInfo?.setUrl("/teacher/queryAnalyseStudent?searchContent=${searchContent}&")
            mv?.addObject("pageInfo",pageInfo)
            return mv
        }

    }

    @RequestMapping("/teacher/studentAnalyse")
    ModelAndView studentAnalyse(String sid){
        ModelAndView mv = new ModelAndView("teacher/studentAnalyse")
        Student student = studentService?.getStudentById(sid)
        mv?.addObject("student",student)
        return mv
    }



    @RequestMapping(value="/teacher/getAnalyseByStudent",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String getAnalyseByStudent(Integer sid){
        Student student = studentService?.getStudentById(sid?.toString())
        Map<String,Object> map = new HashMap<String, Object>()
        List<AvgScore> classesInfo = scoreService?.getSubjectAvgByClasses(student?.getClasses()?.getClassesId())
        map?.put("labels",classesInfo?.subjectName)
        List<AvgScore> info = scoreService?.getSubjectAvgByStudent(sid)
        //防止有的学生没有小测，造成数据不对应
        List<AvgScore> studentInfo = new ArrayList<AvgScore>()
        classesInfo?.subjectName?.each {currentSubject ->
            AvgScore avgScore = new AvgScore()
            avgScore?.setSubjectName(currentSubject)
            //获取与当前课程名称相同的学生对应成绩
            AvgScore studentAvgScore = info?.find {it?.getSubjectName() == currentSubject}
            if(studentAvgScore){
                //存在就设置
                avgScore?.setAvg(studentAvgScore?.getAvg())
            }else{
                //不存在设置为0
                avgScore?.setAvg("0")
            }
            studentInfo << avgScore
        }
        List<Map<String,Object>> datasets = new ArrayList<Map<String,Object>>()
        datasets << Utils.getInfoMap(studentInfo,"该学生")
        datasets << Utils.getInfoMap(classesInfo,"该班级")
        List<AvgScore> gradeInfo = scoreService?.getSubjectAvgByGrade(student?.getClasses()?.getGrade()?.getGradeId())
        datasets << Utils.getInfoMap(gradeInfo,"全年级")
        map?.put("datasets",datasets)
        logger.info(JSONUtil.returnEntityReuslt(map))
        return JSONUtil.returnEntityReuslt(map)
    }

}
