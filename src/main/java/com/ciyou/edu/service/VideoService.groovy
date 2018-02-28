package com.ciyou.edu.service

import com.ciyou.edu.entity.Video
import com.github.pagehelper.Page

/**
 * @Author C.
 * @Date 2018-02-20 18:30
 */
interface VideoService {
    Page<Video> findByPage(int pageNo, int pageSize)

    int addVideo(Video video)

    Video getVideoById(Integer videoId)

    int updateVideo(Video video)

    int deleteVideo(Integer videoId)

    Page<Video> queryVideoByPage(String value,int pageNo, int pageSize)

    int updateVideoType(Integer videoId, Integer videoType)

    Page<Video> findByGradeAndPage(Integer gradeId,int pageNo, int pageSize)

    Page<Video> findVideoByPage(int pageNo, int pageSize)

    Page<Video> findBySubjectAndPage(Integer subjectId,int pageNo, int pageSize)

    Page<Video> findBySubjectGradePage(Integer gradeId, Integer subjectId,int pageNo, int pageSize)
}
