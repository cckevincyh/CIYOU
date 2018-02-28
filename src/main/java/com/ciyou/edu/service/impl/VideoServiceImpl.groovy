package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Video
import com.ciyou.edu.mapper.VideoMapper
import com.ciyou.edu.service.VideoService
import com.github.pagehelper.Page
import com.github.pagehelper.PageHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

    @Transactional
    @Override
    int addVideo(Video video) {
        return videoMapper?.addVideo(video)
    }

    @Override
    Video getVideoById(Integer videoId) {
        return videoMapper?.getVideoById(videoId)
    }

    @Override
    int updateVideo(Video video) {
        return videoMapper?.updateVideo(video)
    }

    @Override
    int deleteVideo(Integer videoId) {
        return videoMapper?.deleteVideo(videoId)
    }

    @Override
    Page<Video> queryVideoByPage(String value, int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return videoMapper?.queryVideoByPage(value)
    }

    @Override
    int updateVideoType(Integer videoId, Integer videoType) {
        return videoMapper?.updateVideoType(videoId,videoType)
    }

    @Override
    Page<Video> findByGradeAndPage(Integer gradeId, int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return videoMapper?.findByGradeAndPage(gradeId)
    }

    @Override
    Page<Video> findVideoByPage(int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return videoMapper?.findVideoByPage()
    }

    @Override
    Page<Video> findBySubjectAndPage(Integer subjectId, int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return videoMapper?.findBySubjectAndPage(subjectId)
    }

    @Override
    Page<Video> findBySubjectGradePage(Integer gradeId, Integer subjectId, int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return videoMapper?.findBySubjectGradePage(gradeId,subjectId)
    }
}
