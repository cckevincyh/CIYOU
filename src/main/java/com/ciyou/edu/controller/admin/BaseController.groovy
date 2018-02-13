package com.ciyou.edu.controller.admin

import net.sf.json.JSONObject

/**
 * @Author C.
 * @Date 2018-02-13 16:05
 */
class BaseController {

    String returnJsonResult(String state = "200",String message,Object entity){
        Map map = new HashMap()
        map?.put("state",state)
        map?.put("message",message)
        map?.put("entity",entity)
        return JSONObject.fromObject(map)?.toString()
    }
}
