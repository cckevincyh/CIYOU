/**
 * ajax 添加年级
 */
$(function () {

    $('#addGrade').click(function () {
         if (!validAddGrade()) {
            return;
        }
        $.ajax({
            type: 'POST',
            url: 'addGrade',
            cache: false,
            dataType:'json',
            data: {
                gradeName: $.trim($("#addGradeName").val())
            },
            success: function (data) {
                if(data.stateCode == "403"){
                    showInfo(data.message);
                    window.location.href = "/403";
                }else if(data.isSuccess){
                    $("#addModal").modal("hide");//关闭模糊框
                    showInfo(data.message);
                }else{
                    showInfo1(data.message);
                }

            },
            error: function (jqXHR, textStatus, errorThrown) {
                showInfo("提交失败，请重试");
            }
        });

		
	});
	
		$('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
       		 location.reload();  	//刷新当前页面
    	});
	
	

});



function validAddGrade() {
    var flag = true;

    var gradeName = $.trim($("#addGradeName").val());
    if (gradeName == "") {
        $('#addGradeName').parent().addClass("has-error");
        $('#addGradeName').next().html("<i class='fa fa-times-circle-o'></i>    请输入年级");
        $("#addGradeName").next().show();
        flag = false;
    }else {
        $('#addGradeName').parent().removeClass("has-error");
        $('#addGradeName').next().text("");
        $("#addGradeName").next().hide();
    }

    return flag;
}






function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


function showInfo1(msg) {
    $("#div_info1").text(msg);
    $("#modal_info1").modal('show');
}
