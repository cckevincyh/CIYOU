/**
 * Created by c on 2018/2/23.
 */
function deleteVideo(id){
    $.ajax({
        type: 'POST',
        url: 'deleteVideo',
        cache: false,
        dataType:'json',
        data: {
            videoId: id
        },
        success: function (data) {
            showInfo(data.message);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            showInfo("提交失败，请重试");
        }
    });
}