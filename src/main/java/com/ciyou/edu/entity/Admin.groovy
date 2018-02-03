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
    private List<Permission> permissionList

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

    List<Permission> getPermissionList() {
        return permissionList
    }

    void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList
    }


    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                ", password='" + password + '\'' +
                ", isAvalible=" + isAvalible +
                ", permissionList=" + permissionList +
                '}';
    }
}
