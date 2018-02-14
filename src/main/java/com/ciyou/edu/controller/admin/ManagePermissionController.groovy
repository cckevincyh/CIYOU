package com.ciyou.edu.controller.admin

import com.ciyou.edu.entity.Permission
import com.ciyou.edu.entity.TreeNode
import com.ciyou.edu.service.PermissionService
import com.ciyou.edu.service.ShiroService
import com.ciyou.edu.utils.JSONUtil
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
    @Autowired
    private ShiroService shiroService

    /**
     * 获得权限树
     * @return
     */
    @RequestMapping(value="/admin/getAllPermission", method=RequestMethod.POST , produces="application/json;charset=UTF-8")
    @ResponseBody
    String getAllPermission(){
        List<TreeNode> permissionTree = permissionService?.getPermissionTree()
        logger.info("获得的permissionTree：" + permissionTree)
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
            public boolean apply(Object obj, String name, Object value) {
                return value == null
            }
        })
        String treeJson = JSONArray.fromObject(permissionTree,jsonConfig)?.toString()
        //这里要转为json对象，前端ajax才解析的了
        logger.info("获得的treeJson：" + treeJson)
        return JSONUtil.returnEntityReuslt(JSONArray.fromObject(permissionTree,jsonConfig))
    }

    @RequestMapping(value="/admin/getPermission", method=RequestMethod.POST , produces="application/json;charset=UTF-8")
    @ResponseBody
    String getPermission(Integer permissionId){
        Permission permission = permissionService?.findPermissionById(permissionId)
        logger.info("获得的Permission：" + permission)
        return JSONUtil.returnEntityReuslt(permission)
    }

    /**
     * 前端添加表单校验权限名
     * @param permissionName
     * @return
     */
    @RequestMapping(value="/admin/validatePermissionName", method=RequestMethod.POST , produces="application/json;charset=UTF-8")
    @ResponseBody
    String validatePermissionName(String permissionName){
        logger.info("校验权限名..")
        Permission permission = permissionService?.findPermissionByName(permissionName)
        Map<String, Boolean> map = new HashMap<String, Boolean>()
        if(permission){
            map.put("valid", false)
        }else{
            map.put("valid", true)
        }
        return JSONObject.fromObject(map)?.toString()
    }

    /**
     * 前端修改表单校验权限名
     * @param permissionId
     * @param permissionName
     * @return
     */
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

    /**
     * 新增权限
     * @param permission
     * @return
     */
    @RequestMapping(value="/admin/addRootPermission", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String addRootPermission(Permission permission){
        logger.info("新增根权限..")
        //校验数据
        if(!permission?.getPermissionName() || permission?.getPermissionName()?.trim() == ""){
            return JSONUtil.returnFailReuslt("权限名不能为空")
        }else if(!permission?.getPermission() || permission?.getPermission()?.trim() == ""){
            return JSONUtil.returnFailReuslt("权限字符串不能为空")
        }else if(!permission?.getUrl() || permission?.getUrl()?.trim() == ""){
            return JSONUtil.returnFailReuslt("权限资源URL不能为空")
        }
        //添加根权限
        permission?.setType(1)
        try{
            if(permissionService?.addPermission(permission)){
                //重新加载权限
                shiroService?.updatePermission()
                return JSONUtil.returnSuccessResult("添加成功")
            }else{
                return JSONUtil.returnFailReuslt("添加失败")
            }
        }catch (Exception e){
            logger.info("添加根权限异常：" + e.getMessage())
            return JSONUtil.returnFailReuslt("添加失败，请重试")
        }

    }
    @RequestMapping(value="/admin/addPermission", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String addPermission(Permission permission){
        logger.info("新增子权限..")
        //校验数据
        //查看是否是根权限下添加子权限
        if(permission?.getParentId() == null){
            return JSONUtil.returnFailReuslt("只能在根权限下添加子权限")
        }else if(!permission?.getPermissionName() || permission?.getPermissionName()?.trim() == ""){
            return JSONUtil.returnFailReuslt("权限名不能为空")
        }else if(!permission?.getPermission() || permission?.getPermission()?.trim() == ""){
            return JSONUtil.returnFailReuslt("权限字符串不能为空")
        }else if(!permission?.getUrl() || permission?.getUrl()?.trim() == ""){
            return JSONUtil.returnFailReuslt("权限资源URL不能为空")
        }
        //标识为子权限
        permission?.setType(2)
        try{
            if(permissionService?.addPermission(permission)){
                //重新加载权限
                shiroService?.updatePermission()
                return JSONUtil.returnSuccessResult("添加成功")
            }else{
                return JSONUtil.returnFailReuslt("添加失败")
            }
        }catch (Exception e){
            logger.info("添加子权限异常：" + e.getMessage())
            return JSONUtil.returnFailReuslt("添加失败，请重试")
        }

    }

    @RequestMapping(value="/admin/validatePermission", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String validatePermission(String permission){
        logger.info("校验权限字符串..")
        Permission p = permissionService?.findPermissionByPermission(permission)
        Map<String, Boolean> map = new HashMap<String, Boolean>()
        if(p){
            map.put("valid", false)
        }else{
            map.put("valid", true)
        }
        return JSONObject.fromObject(map)?.toString()
    }

    @RequestMapping(value="/admin/validatePermissionByUpdate", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String validatePermissionByUpdate(Integer permissionId,String permission){
        logger.info("校验修改权限字符串..")
        Permission p = permissionService?.findOtherPermission(permissionId,permission)
        Map<String, Boolean> map = new HashMap<String, Boolean>()
        if(p){
            map.put("valid", false)
        }else{
            map.put("valid", true)
        }
        return JSONObject.fromObject(map)?.toString()
    }

    @RequestMapping(value="/admin/updatePermission", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String updatePermission(Permission permission){
        logger.info("校验修改权限..")
        if(permission?.getPermissionId() == null){
            return JSONUtil.returnFailReuslt("请选择权限")
        }else if(!permission?.getPermissionName() || permission?.getPermissionName()?.trim() == ""){
            return JSONUtil.returnFailReuslt("权限名不能为空")
        }else if(!permission?.getPermission() || permission?.getPermission()?.trim() == ""){
            return JSONUtil.returnFailReuslt("权限字符串不能为空")
        }else if(!permission?.getUrl() || permission?.getUrl()?.trim() == ""){
            return JSONUtil.returnFailReuslt("权限资源URL不能为空")
        }
        try{
            if(permissionService?.updatePermission(permission)){
                //重新加载权限
                shiroService?.updatePermission()
                return JSONUtil.returnSuccessResult("修改成功")
            }else{
                return JSONUtil.returnFailReuslt("修改失败")
            }
        }catch (Exception e){
            logger.info("修改权限异常：" + e.getMessage())
            return JSONUtil.returnFailReuslt("修改失败，请重试")
        }
    }
    @RequestMapping(value="/admin/deletePermission", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    String deletePermission(Integer permissionId){
        logger.info("删除权限..")
        if(permissionId == null){
            return JSONUtil.returnFailReuslt("请选择权限")
        }
        Permission permission = permissionService?.findPermissionById(permissionId)
        //是父结点
        if(permission?.getType() == 1){
            List<Permission> permissionList = permissionService?.findChildPermission(permissionId)
            //有子权限
            if(permissionList?.size()){
                return JSONUtil.returnFailReuslt( "删除完子权限才能删除该父权限")
            }
        }
        try{
            if(permissionService?.deletePermission(permissionId)){
                //重新加载权限
                shiroService?.updatePermission()
                return JSONUtil.returnSuccessResult("删除成功")
            }else{
                return JSONUtil.returnFailReuslt("删除失败")
            }
        }catch (Exception e){
            logger.info("删除权限异常：" + e.getMessage())
            return JSONUtil.returnFailReuslt("删除失败，请重试")
        }
    }
}
