package com.ciyou.edu.entity

/**
 * @Author C.
 * @Date 2018-02-02 15:43
 */
class Permission implements Serializable{
    private static final long serialVersionUID = 1L
    //权限编号（自增长）
    private Integer permissionId
    //权限名称
    private String permissionName
    //权限字符串
    private String permission
    //父编号
    private Integer parentId
    //资源类型
    private String type
    //资源路径
    private String url

    Integer getPermissionId() {
        return permissionId
    }

    void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId
    }

    String getPermissionName() {
        return permissionName
    }

    void setPermissionName(String permissionName) {
        this.permissionName = permissionName
    }

    String getPermission() {
        return permission
    }

    void setPermission(String permission) {
        this.permission = permission
    }

    Integer getParentId() {
        return parentId
    }

    void setParentId(Integer parentId) {
        this.parentId = parentId
    }

    String getType() {
        return type
    }

    void setType(String type) {
        this.type = type
    }

    String getUrl() {
        return url
    }

    void setUrl(String url) {
        this.url = url
    }


    @Override
    public String toString() {
        return "Permission{" +
                "permissionId=" + permissionId +
                ", permissionName='" + permissionName + '\'' +
                ", permission='" + permission + '\'' +
                ", parentId=" + parentId +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
