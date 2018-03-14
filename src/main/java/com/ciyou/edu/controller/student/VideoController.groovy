package com.ciyou.edu.controller.student

import com.ciyou.edu.entity.PageInfo
import com.ciyou.edu.entity.Video
import com.ciyou.edu.service.GradeService
import com.ciyou.edu.service.SubjectService
import com.ciyou.edu.service.VideoService
import com.github.pagehelper.Page
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

/**
 * @Author C.
 * @Date 2018-02-27 20:55
 */
@Controller
class VideoController {

    private static final Logger logger = LoggerFactory.getLogger(VideoController.class)
    @Autowired
    private VideoService videoService

    @Autowired
    private GradeService gradeService

    @Autowired
    private SubjectService subjectService

    @RequestMapping("/student/index")
    ModelAndView findVideoByPage(Integer grade,Integer subject,Integer page){
        logger.info("grade = " + grade + ", subject = " + subject)
        if(page == null){
            page = 1
        }
        ModelAndView mv = new ModelAndView("student/index")
        logger.info("findVideoByPage : 查询第${page}页")
        //不赋值pageSize，默认为10
        Page<Video> videos = null
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Video> pageInfo = null
        if((grade == null || grade == 0) && (subject == null || subject == 0)){
            videos = videoService?.findVideoByPage(page,8)
            pageInfo = new PageInfo<Video>(videos)
            pageInfo?.setUrl("/student/index?")
        }else if(subject == null || subject == 0){
            videos = videoService?.findByGradeAndPage(grade,page,8)
            pageInfo = new PageInfo<Video>(videos)
            pageInfo?.setUrl("/student/index?grade=${grade}?")
        }else if(grade == null || grade == 0){
            videos = videoService?.findBySubjectAndPage(subject,page,8)
            pageInfo = new PageInfo<Video>(videos)
            pageInfo?.setUrl("/student/index?subject=${subject}?")
        }else{
            videos = videoService?.findBySubjectGradePage(grade,subject,page,8)
            pageInfo = new PageInfo<Video>(videos)
            pageInfo?.setUrl("/student/index?grade=${grade}&subject=${subject}?")
        }
        mv?.addObject("pageInfo",pageInfo)
        mv?.addObject("grades",gradeService?.findAllGrade())
        mv?.addObject("subjects",subjectService?.findAllSubject())
        return mv
    }

}
