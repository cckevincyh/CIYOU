/**
 * Created by c on 2018/2/18.
 */
function deleteTeacher(id){
    $.ajax({
        type: 'POST',
        url: 'deleteTeacher',
        cache: false,
        dataType: 'json',
        data:{
          tid : id
        },
        success: function (result) {
            if(result.stateCode == "403"){
                showInfo(result.message);
                window.location.href = "/403";
            }else{
                showInfo(result.message);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            showInfo("抱歉系统繁忙..");
        }
    });
}