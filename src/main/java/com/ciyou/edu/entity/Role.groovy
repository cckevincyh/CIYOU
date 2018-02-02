package com.ciyou.edu.entity

/**
 * @Author C.
 * @Date 2018-02-02 17:20
 */
class Role implements Serializable{
    private static final long serialVersionUID = 1L
    private Integer roleId
    private String roleName

    Integer getRoleId() {
        return roleId
    }

    void setRoleId(Integer roleId) {
        this.roleId = roleId
    }

    String getRoleName() {
        return roleName
    }

    void setRoleName(String roleName) {
        this.roleName = roleName
    }


    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
