/**
 * Created by c on 2018/2/26.
 */
function deleteQuiz(id){
    $.ajax({
        type: 'POST',
        url: 'deleteQuiz',
        cache: false,
        dataType:'json',
        data: {
            quizId: id
        },
        success: function (data) {
            showInfo(data.message);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            showInfo("提交失败，请重试");
        }
    });
}