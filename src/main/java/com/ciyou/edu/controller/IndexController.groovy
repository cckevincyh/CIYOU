package com.ciyou.edu.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * @Author C.
 * @Date 2018-02-01 23:30
 */
@Controller
class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(getClass())

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        def str = "hello world!!哈哈"
        return str
    }

}
