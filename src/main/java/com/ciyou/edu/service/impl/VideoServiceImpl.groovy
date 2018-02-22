package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Video
import com.ciyou.edu.mapper.VideoMapper
import com.ciyou.edu.service.VideoService
import com.github.pagehelper.Page
import com.github.pagehelper.PageHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @Author C.
 * @Date 2018-02-20 18:30
 */
@Service
class VideoServiceImpl implements VideoService{

    @Autowired
    private VideoMapper videoMapper

    @Override
    Page<Video> findByPage(int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return videoMapper?.findAllVideo()
    }
}
