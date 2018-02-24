/**
 * Created by c on 2018/2/24.
 */
function deleteRoster(id){
    $.ajax({
        type: 'POST',
        url: 'deleteRoster',
        cache: false,
        dataType:'json',
        data: {
            rid: id
        },
        success: function (data) {
            showInfo(data.message);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            showInfo("提交失败，请重试");
        }
    });
}