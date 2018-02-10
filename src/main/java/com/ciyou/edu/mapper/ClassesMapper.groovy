package com.ciyou.edu.mapper

import com.ciyou.edu.entity.Classes
import com.github.pagehelper.Page
import org.apache.ibatis.annotations.One
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select

/**
 * @Author C.
 * @Date 2018-02-10 9:24
 */
interface ClassesMapper {

    @Select("select * from Classes")
    @Results([
        //查询关联对象
        @Result(property = "grade",
                column = "gradeId",
                one = @One(select = "com.ciyou.edu.mapper.GradeMapper.getGrade"))])
    Page<Classes> findByPage()
}
