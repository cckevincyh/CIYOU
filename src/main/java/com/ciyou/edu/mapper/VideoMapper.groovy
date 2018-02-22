package com.ciyou.edu.mapper

import com.ciyou.edu.entity.Video
import com.github.pagehelper.Page
import org.apache.ibatis.annotations.Select

/**
 * @Author C.
 * @Date 2018-02-20 18:31
 */
interface VideoMapper {

    @Select("select * from Video order by videoId")
    Page<Video> findAllVideo()
}
