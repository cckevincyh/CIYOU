package com.ciyou.edu.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * @Author C.
 * @Date 2018-02-01 23:30
 */
@Controller
class IndexController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        def str = "hello world!!哈哈"
        return str
    }

}
