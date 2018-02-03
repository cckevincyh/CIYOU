package com.ciyou.edu.config.shiro.common

/**
 * @Author C.
 * @Date 2018-02-03 11:50
 */
enum  LoginType {
    STUDENT("Student"),  ADMIN("Admin"), TEACHER("Teacher")

    private String type

    private LoginType(String type) {
        this.type = type
    }

    @Override
    public String toString() {
        return this.type.toString()
    }
}
