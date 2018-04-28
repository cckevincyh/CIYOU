package com.ciyou.edu.controller.teacher

import com.ciyou.edu.entity.Grade
import com.ciyou.edu.entity.PageInfo
import com.ciyou.edu.entity.Teacher
import com.ciyou.edu.entity.Video
import com.ciyou.edu.service.GradeService
import com.ciyou.edu.service.VideoService
import com.ciyou.edu.utils.JSONUtil
import com.github.pagehelper.Page
import org.apache.shiro.SecurityUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.ModelAndView

import javax.servlet.http.HttpServletRequest

/**
 * @Author C.
 * @Date 2018-02-20 18:29
 */
@Controller
class ManageVideoController {
    private static final Logger logger = LoggerFactory.getLogger(ManageVideoController.class)
    @Autowired
    private VideoService videoService

    @Autowired
    private GradeService gradeService

    @RequestMapping("/teacher/manageVideo")
    ModelAndView findVideoByPage(Integer page){
        if(page == null){
            page = 1
        }
        ModelAndView mv = new ModelAndView("teacher/manageVideo")
        logger.info("findVideoByPage : 查询第${page}页")
        //不赋值pageSize，默认为10
        Page<Video> videos = videoService?.findByPage(page,3)
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Video> pageInfo = new PageInfo<Video>(videos)
        pageInfo?.setUrl("/teacher/manageVideo?")
        mv?.addObject("pageInfo",pageInfo)
        return mv
    }


    @RequestMapping(value="/teacher/VideoImgFileUpload", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String videoImgFileUpload(@RequestParam MultipartFile file, HttpServletRequest request){
        logger.info("视频封面上传....")
        //获取文件名
        String originalFilename = file.getOriginalFilename()
        logger.info("上传文件名：" + originalFilename)
        String realPath = request.getServletContext().getRealPath("/public/videoImg/")
        File _dirFile = new File(realPath)
        if (!_dirFile?.exists() && !_dirFile?.isDirectory()){
            _dirFile?.mkdir()
        }
        String uploadFileName = System.currentTimeMillis()+"_"+ originalFilename
        logger.info("获取上传路径：" + realPath + ", 上传的真实文件名：" + uploadFileName)
        boolean flag = true

        //合并文件
        RandomAccessFile raFile = null
        BufferedInputStream inputStream = null
        try{
            File dirFile = new File(realPath, uploadFileName)
            //以读写的方式打开目标文件
            raFile = new RandomAccessFile(dirFile, "rw")
            raFile.seek(raFile.length())
            inputStream = new BufferedInputStream(file.getInputStream())
            byte[] buf = new byte[1024]
            int length = 0
            while ((length = inputStream.read(buf)) != -1) {
                raFile.write(buf, 0, length)
            }
        }catch(Exception e){
            flag = false
            logger.info("上传出错:" + e.getMessage())
            throw new IOException(e.getMessage())
        }finally{
            try {
                if (inputStream != null) {
                    inputStream.close()
                }
                if (raFile != null) {
                    raFile.close()
                }
            }catch(Exception e){
                flag = false
                logger.info("上传出错:" + e.getMessage())
                throw new IOException(e.getMessage())
            }
        }
        if(flag){
            return JSONUtil.returnSuccessResult("/public/videoImg/" + uploadFileName)
        }else{
            return JSONUtil.returnFailReuslt("fail")
        }

    }



    @RequestMapping(value="/teacher/videoFileUpload", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String videoFileUpload(@RequestParam MultipartFile file, HttpServletRequest request){
        logger.info("视频上传....")
        //获取文件名
        String originalFilename = file.getOriginalFilename()
        logger.info("上传文件名：" + originalFilename)
        String realPath = request.getServletContext().getRealPath("/public/video/")
        File _dirFile = new File(realPath)
        if (!_dirFile?.exists() && !_dirFile?.isDirectory()){
            _dirFile?.mkdir()
        }
        String uploadFileName = System.currentTimeMillis()+"_"+ originalFilename
        logger.info("获取上传路径：" + realPath + ", 上传的真实文件名：" + uploadFileName)
        boolean flag = true

        //合并文件
        RandomAccessFile raFile = null
        BufferedInputStream inputStream = null
        try{
            File dirFile = new File(realPath, uploadFileName)
            //以读写的方式打开目标文件
            raFile = new RandomAccessFile(dirFile, "rw")
            raFile.seek(raFile.length())
            inputStream = new BufferedInputStream(file.getInputStream())
            byte[] buf = new byte[1024]
            int length = 0
            while ((length = inputStream.read(buf)) != -1) {
                raFile.write(buf, 0, length)
            }
        }catch(Exception e){
            flag = false
            logger.info("上传出错:" + e.getMessage())
            throw new IOException(e.getMessage())
        }finally{
            try {
                if (inputStream != null) {
                    inputStream.close()
                }
                if (raFile != null) {
                    raFile.close()
                }
            }catch(Exception e){
                flag = false
                logger.info("上传出错:" + e.getMessage())
                throw new IOException(e.getMessage())
            }
        }
        if(flag){
            return JSONUtil.returnSuccessResult("/public/video/" + uploadFileName)
        }else{
            return JSONUtil.returnFailReuslt("fail")
        }

    }


    @RequestMapping(value="/teacher/getAllGrade",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String getAllGrade(){
        List<Grade> gradeList = gradeService?.findAllGrade()
        return JSONUtil.returnEntityReuslt(gradeList)
    }

    @RequestMapping(value="/teacher/addVideo",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String addVideo(Video video){
        logger.info("添加的video信息：" + video)
        if(!video?.getVideoName() || video?.getVideoName()?.trim() == ""){
            return JSONUtil.returnFailReuslt("视频名称不能为空")
        }else if(!video?.getVideoUrl() || video?.getVideoUrl()?.trim() == ""){
            return JSONUtil.returnFailReuslt("请上传视频")
        }else if(!video?.getImgUrl() || video?.getImgUrl()?.trim() == ""){
            return JSONUtil.returnFailReuslt("请上传视频封面图片")
        }else if(!video?.getGrade() || video?.getGrade()?.getGradeId() == 0){
            return JSONUtil.returnFailReuslt("请选择年级")
        }else if(!video?.getSubject() || video?.getSubject()?.getSubjectId() == 0){
            return JSONUtil.returnFailReuslt("请选择科目")
        }
        video?.setCreateTime(new Date(System.currentTimeMillis()))
        video?.setTeacher((Teacher)SecurityUtils?.getSubject()?.getPrincipal())
        try{
            if(videoService?.addVideo(video)){
                return JSONUtil.returnSuccessResult("添加成功")
            }else{
                return JSONUtil.returnSuccessResult("添加失败")
            }
        }catch (Exception e){
            logger.info("添加视频失败信息：" + e.getMessage())
            return JSONUtil.returnSuccessResult("添加失败")
        }
    }


    @RequestMapping(value="/teacher/getVideoById",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String getVideoById(Integer videoId){
        logger.info("获取的视频VideoId = " + videoId)
        return JSONUtil.returnEntityReuslt(videoService?.getVideoById(videoId))
    }

    @RequestMapping(value="/teacher/updateVideo",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String updateVideo(Video video){
        logger.info("修改的video信息：" + video)
        if(!video?.getVideoName() || video?.getVideoName()?.trim() == ""){
            return JSONUtil.returnFailReuslt("视频名称不能为空")
        }else if(!video?.getVideoUrl() || video?.getVideoUrl()?.trim() == ""){
            return JSONUtil.returnFailReuslt("请上传视频")
        }else if(!video?.getImgUrl() || video?.getImgUrl()?.trim() == ""){
            return JSONUtil.returnFailReuslt("请上传视频封面图片")
        }else if(!video?.getGrade() || video?.getGrade()?.getGradeId() == 0){
            return JSONUtil.returnFailReuslt("请选择年级")
        }else if(!video?.getSubject() || video?.getSubject()?.getSubjectId() == 0){
            return JSONUtil.returnFailReuslt("请选择科目")
        }
        try{
            if(videoService?.updateVideo(video)){
                return JSONUtil.returnSuccessResult("修改成功")
            }else{
                return JSONUtil.returnSuccessResult("修改失败")
            }
        }catch (Exception e){
            logger.info("修改视频失败信息：" + e.getMessage())
            return JSONUtil.returnSuccessResult("修改失败")
        }
    }

    @RequestMapping(value="/teacher/deleteVideo",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String deleteVideo(Integer videoId){
        try{
            if(videoService?.deleteVideo(videoId)){
                return JSONUtil.returnSuccessResult("删除成功")
            }else{
                return JSONUtil.returnFailReuslt("删除失败")
            }
        }catch (Exception e){
            logger.info("删除Video错误：" + e.getMessage())
            return JSONUtil.returnFailReuslt("删除失败，请重试")
        }
    }


    @RequestMapping(value="/teacher/queryVideo")
    ModelAndView queryVideo(String searchContent,Integer page){
        if(page == null){
            page = 1
        }
        if(!searchContent || searchContent?.trim() == ""){
            ModelAndView mv = new ModelAndView("redirect:manageVideo")
            return mv
        }else{
            ModelAndView mv = new ModelAndView("/teacher/manageVideo")
            logger.info("queryVideo : 查询第${page}页，携带查询参数=${searchContent}")
            //不赋值pageSize，默认为10
            Page<Video> videos = videoService?.queryVideoByPage(searchContent?.trim(),page,3)
            // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
            PageInfo<Video> pageInfo = new PageInfo<Video>(videos)
            pageInfo?.setUrl("/teacher/queryVideo?searchContent=${searchContent}&")
            mv?.addObject("pageInfo",pageInfo)
            return mv
        }

    }
}
