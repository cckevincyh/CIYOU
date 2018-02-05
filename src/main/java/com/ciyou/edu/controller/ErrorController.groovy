package com.ciyou.edu.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

/**
 * @Author C.
 * @Date 2018-02-04 21:07
 */
class ErrorController {

    /**
     * 找不到资源
     * @return
     */
    @RequestMapping("/404")
    public ModelAndView notFound() {
        return new ModelAndView("/404")
    }


    /**
     * 服务器内部错误
     * @return
     */
    @RequestMapping(value = "/500")
    public ModelAndView serverError() {
        return new ModelAndView("/500")
    }
}
