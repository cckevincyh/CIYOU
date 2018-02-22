package com.ciyou.edu.controller.teacher

import com.ciyou.edu.entity.PageInfo
import com.ciyou.edu.entity.Video
import com.ciyou.edu.service.VideoService
import com.ciyou.edu.utils.JSONUtil
import com.github.pagehelper.Page
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

    @RequestMapping("/teacher/manageVideo")
    ModelAndView findVideoByPage(Integer page){
        if(page == null){
            page = 1
        }
        ModelAndView mv = new ModelAndView("teacher/manageVideo")
        logger.info("findAdminByPage : 查询第${page}页")
        //不赋值pageSize，默认为10
        Page<Video> videos = videoService?.findByPage(page)
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
        String realPath = request.getServletContext().getRealPath("/static/upload/videoImg/")
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
            return JSONUtil.returnSuccessResult("/static/upload/videoImg/" + uploadFileName)
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
        String realPath = request.getServletContext().getRealPath("/static/upload/video/")
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
            return JSONUtil.returnSuccessResult("/static/upload/video/" + uploadFileName)
        }else{
            return JSONUtil.returnFailReuslt("fail")
        }

    }
}
