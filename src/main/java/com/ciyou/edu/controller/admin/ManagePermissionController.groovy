package com.ciyou.edu.controller.admin

import com.ciyou.edu.entity.Permission
import com.ciyou.edu.entity.TreeNode
import com.ciyou.edu.service.PermissionService
import net.sf.json.JSONArray
import net.sf.json.JSONObject
import net.sf.json.JsonConfig
import net.sf.json.util.PropertyFilter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

/**
 * @Author C.
 * @Date 2018-02-11 11:16
 */
@Controller
class ManagePermissionController {
    private static final Logger logger = LoggerFactory.getLogger(ManagePermissionController.class)

    @Autowired
    private PermissionService permissionService

    @RequestMapping(value="/admin/getAllPermission", method=RequestMethod.POST , produces="application/json;charset=UTF-8")
    @ResponseBody
    String getAllPermission(){
        List<TreeNode> permissionTree = permissionService?.getPermissionTree()
        logger.info("获得的permissionTree：" + permissionTree)
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
            public boolean apply(Object obj, String name, Object value) {
                if(value == null){
                    return true
                }else{
                    return false
                }
            }
        })
        String treeJson = JSONArray.fromObject(permissionTree,jsonConfig)?.toString()
        //这里要转为json对象，前端ajax才解析的了
        logger.info("获得的treeJson：" + treeJson)
        return treeJson
    }

    @RequestMapping(value="/admin/getPermission", method=RequestMethod.POST , produces="application/json;charset=UTF-8")
    @ResponseBody
    String getPermission(Integer permissionId){
        Permission permission = permissionService?.findPermissionById(permissionId)
        logger.info("获得的Permission：" + permission)
        return JSONObject.fromObject(permission)?.toString()
    }
}
