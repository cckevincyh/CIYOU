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

    @RequestMapping(value="/admin/validatePermissionName", method=RequestMethod.POST , produces="application/json;charset=UTF-8")
    @ResponseBody
    String validatePermissionName(String permissionName){
        logger.info("校验权限名..")
        Permission permission = permissionService?.findPermissionByName(permissionName)
        Map<String, Boolean> map = new HashMap<>()
        if(permission){
            map.put("valid", false)
        }else{
            map.put("valid", true)
        }
        return JSONObject.fromObject(map)?.toString()
    }

    @RequestMapping(value="/admin/validatePNameByUpdate", method=RequestMethod.POST , produces="application/json;charset=UTF-8")
    @ResponseBody
    String validatePNameByUpdate(Integer permissionId,String permissionName){
        logger.info("校验修改权限名..")
        Permission permission = permissionService?.findOtherPermissionByName(permissionId,permissionName)
        Map<String, Boolean> map = new HashMap<>()
        if(permission){
            map.put("valid", false)
        }else{
            map.put("valid", true)
        }
        return JSONObject.fromObject(map)?.toString()
    }

    @RequestMapping(value="/admin/addRootPermission", method=RequestMethod.POST)
    @ResponseBody
    String addRootPermission(Permission permission){
        logger.info("新增根权限..")
        //校验数据
        if(!permission?.getPermissionName() || permission?.getPermissionName()?.trim() == ""){
            return "权限名不能为空"
        }else if(!permission?.getPermission() || permission?.getPermission()?.trim() == ""){
            return "权限字符串不能为空"
        }else if(!permission?.getUrl() || permission?.getUrl()?.trim() == ""){
            return "权限资源URL不能为空"
        }
        //添加根权限
        permission?.setType(1)
        try{
            if(permissionService?.addPermission(permission)){
                return "添加成功"
            }else{
                return "添加失败"
            }
        }catch (Exception e){
            logger.info("添加根权限异常：" + e.getMessage())
            return "添加失败，请重试"
        }

    }
    @RequestMapping(value="/admin/addPermission", method=RequestMethod.POST)
    @ResponseBody
    String addPermission(Permission permission){
        logger.info("新增子权限..")
        //校验数据
        //查看是否是根权限下添加子权限
        if(permission?.getParentId() == null){
            return "只能在根权限下添加子权限"
        }else if(!permission?.getPermissionName() || permission?.getPermissionName()?.trim() == ""){
            return "权限名不能为空"
        }else if(!permission?.getPermission() || permission?.getPermission()?.trim() == ""){
            return "权限字符串不能为空"
        }else if(!permission?.getUrl() || permission?.getUrl()?.trim() == ""){
            return "权限资源URL不能为空"
        }
        //标识为子权限
        permission?.setType(2)
        try{
            if(permissionService?.addPermission(permission)){
                return "添加成功"
            }else{
                return "添加失败"
            }
        }catch (Exception e){
            logger.info("添加根权限异常：" + e.getMessage())
            return "添加失败，请重试"
        }

    }
}
