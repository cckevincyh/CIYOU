package com.ciyou.edu.controller.admin

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
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView

/**
 * @Author C.
 * @Date 2018-02-23 17:22
 */
@Controller
class CheckVideoController {

    private static final Logger logger = LoggerFactory.getLogger(CheckVideoController.class)
    @Autowired
    private VideoService videoService

    @RequestMapping("/admin/checkVideo")
    ModelAndView findVideoByPage(Integer page){
        if(page == null){
            page = 1
        }
        ModelAndView mv = new ModelAndView("admin/checkVideo")
        logger.info("findVideoByPage : 查询第${page}页")
        //不赋值pageSize，默认为10
        Page<Video> videos = videoService?.findByPage(page,3)
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Video> pageInfo = new PageInfo<Video>(videos)
        pageInfo?.setUrl("/admin/checkVideo?")
        mv?.addObject("pageInfo",pageInfo)
        return mv
    }


    @RequestMapping(value="/admin/queryVideo")
    ModelAndView queryVideo(String searchContent,Integer page){
        if(page == null){
            page = 1
        }
        if(!searchContent || searchContent?.trim() == ""){
            ModelAndView mv = new ModelAndView("redirect:checkVideo")
            return mv
        }else{
            ModelAndView mv = new ModelAndView("/admin/checkVideo")
            logger.info("queryVideo : 查询第${page}页，携带查询参数=${searchContent}")
            //不赋值pageSize，默认为10
            Page<Video> videos = videoService?.queryVideoByPage(searchContent?.trim(),page,3)
            // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
            PageInfo<Video> pageInfo = new PageInfo<Video>(videos)
            pageInfo?.setUrl("/admin/queryVideo?searchContent=${searchContent}&")
            mv?.addObject("pageInfo",pageInfo)
            return mv
        }

    }


    @RequestMapping(value="/admin/passVideo",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String passVideo(Integer videoId){
        logger.info("审核通过视频ID：" + videoId)
        try{
            if(videoService?.updateVideoType(videoId,2)){
                return JSONUtil.returnSuccessResult("通过审核")
            }else{
                return JSONUtil.returnFailReuslt("审核失败")
            }
        }catch (Exception e){
            logger.info("审核Video错误：" + e.getMessage())
            return JSONUtil.returnFailReuslt("审核失败，请重试")
        }
    }

    @RequestMapping(value="/admin/notPassVideo",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String notPassVideo(Integer videoId){
        logger.info("取消审核视频ID：" + videoId)
        try{
            if(videoService?.updateVideoType(videoId,1)){
                return JSONUtil.returnSuccessResult("取消审核")
            }else{
                return JSONUtil.returnFailReuslt("取消审核失败")
            }
        }catch (Exception e){
            logger.info("取消审核Video错误：" + e.getMessage())
            return JSONUtil.returnFailReuslt("取消审核失败，请重试")
        }
    }
}
