/**
 * Created by c on 2018/2/23.
 */
function passVideo(id){
    $.ajax({
        type: 'POST',
        url: 'passVideo',
        cache: false,
        dataType:'json',
        data: {
            videoId:id
        },
        success: function (data) {
            if(data.stateCode == "403"){
                showInfo(data.message);
                window.location.href = "/403";
            }else{
                showInfo(data.message);
            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
            showInfo("提交失败，请重试");
        }
    });
}



function notPassVideo(id){
    $.ajax({
        type: 'POST',
        url: 'notPassVideo',
        cache: false,
        dataType:'json',
        data: {
            videoId:id
        },
        success: function (data) {
            if(data.stateCode == "403"){
                showInfo(data.message);
                window.location.href = "/403";
            }else{
                showInfo(data.message);
            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
            showInfo("提交失败，请重试");
        }
    });
}