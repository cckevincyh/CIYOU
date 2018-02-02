package com.ciyou.edu.entity

/**
 * @Author C.
 * @Date 2018-02-02 15:42
 */
class Admin implements Serializable{
    private static final long serialVersionUID = 1L
    //管理员编号（自增长）
    private Integer adminId
    //管理员用户名
    private String adminName
    //密码
    private String password
    //状态 1：正常 2：冻结
    private Integer isAvalible
    //权限
    private Permission permission
    //所属角色
    private Role role

    Integer getAdminId() {
        return adminId
    }

    void setAdminId(Integer adminId) {
        this.adminId = adminId
    }

    String getAdminName() {
        return adminName
    }

    void setAdminName(String adminName) {
        this.adminName = adminName
    }

    String getPassword() {
        return password
    }

    void setPassword(String password) {
        this.password = password
    }

    Integer getIsAvalible() {
        return isAvalible
    }

    void setIsAvalible(Integer isAvalible) {
        this.isAvalible = isAvalible
    }

    Permission getPermission() {
        return permission
    }

    void setPermission(Permission permission) {
        this.permission = permission
    }

    Role getRole() {
        return role
    }

    void setRole(Role role) {
        this.role = role
    }
}
