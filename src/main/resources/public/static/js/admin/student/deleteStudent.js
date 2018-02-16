/**
 * Created by c on 2018/2/16.
 */
function deleteStudent(id) {
    $.ajax({
        type: 'POST',
        url: 'deleteStudent',
        cache: false,
        dataType:'json',
        data: {
            sid: id
        },
        success: function (data) {
            if(data.stateCode == "403"){
                showInfo(data.message);
                window.location.href = "/403";
            }else {
                showInfo(data.message);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            showInfo("提交失败，请重试");
        }
    });
}