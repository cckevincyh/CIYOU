package com.ciyou.edu.utils

import com.ciyou.edu.entity.AvgScore
import com.ciyou.edu.entity.IncorrectAnswer

/**
 * @Author C.
 * @Date 2018-03-07 20:06
 */
class Utils {


    public static Map<String, Object> getIncorrectMap(List<IncorrectAnswer> incorrectAnswerList){
        Map<String,Object> infoMap = new HashMap<String,Object>()
        String rgba = RGBAUtil.randomColor()
        infoMap?.put("fillColor",rgba)
        infoMap?.put("strokeColor",rgba)
        infoMap?.put("pointColor",rgba)
        infoMap?.put("pointStrokeColor",rgba)
        infoMap?.put("pointHighlightFill",rgba)
        infoMap?.put("pointHighlightStroke",rgba)
        infoMap?.put("data",incorrectAnswerList?.incorrectNum)
        return infoMap
    }


    public static Map<String, Object> getInfoMap(List<AvgScore> info, String label){
        Map<String,Object> infoMap = new HashMap<String,Object>()
        String rgba = RGBAUtil.randomColor()
        infoMap?.put("fillColor",rgba)
        infoMap?.put("strokeColor",rgba)
        infoMap?.put("pointColor",rgba)
        infoMap?.put("pointStrokeColor",rgba)
        infoMap?.put("pointHighlightFill",rgba)
        infoMap?.put("pointHighlightStroke",rgba)
        infoMap?.put("label",label)
        infoMap?.put("data",info?.avg)
        return infoMap
    }

}
