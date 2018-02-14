/**
 * ajax 添加班级
 */
$(function () {

    $('#addClass').click(function () {
         if (!validAddClasses()) {
            return;
        }
        $.ajax({
            type: 'POST',
            url: 'addClasses',
            cache: false,
            dataType:'json',
            data: {
                classes: $.trim($("#addClasses").val()),
                gradeId: $.trim($("#addGradeName").val())
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


    /**
     * 获取年级列表
     */
    $('#btn_add').click(function () {
        $("#addClasses option[value!=0]").remove();//移除先前的选项
        $.ajax({
            type: 'POST',
            url: 'getAllGrade',
            cache: false,
            dataType:'json',
            success: function (result) {
                if(result.stateCode == "403"){
                    showInfo(result.message);
                    window.location.href = "/403";
                }else{
                    var data = result.entity;
                    // 循环遍历每个年级，每个名称生成一个option对象，添加到<select>中
                    for(var index in data) {
                        var op = document.createElement("option");//创建一个指名名称元素
                        op.value = data[index].gradeId;//设置op的实际值为当前的年级ID
                        var textNode = document.createTextNode(data[index].gradeName);//创建文本节点
                        op.appendChild(textNode);//把文本子节点添加到op元素中，指定其显示值

                        document.getElementById("addGradeName").appendChild(op);
                    }
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showInfo("获取年级列表失败，请重试");
            }
        });
    });

	
		$('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
       		 location.reload();  	//刷新当前页面
    	});
	
	

});



function validAddClasses() {
    var flag = true;

    var gradeName = $.trim($("#addGradeName").val());
    if (gradeName == 0) {
        $('#addGradeName').parent().addClass("has-error");
        $('#addGradeName').next().html("<i class='fa fa-times-circle-o'></i>    请选择年级");
        $("#addGradeName").next().show();
        flag = false;
    }else {
        $('#addGradeName').parent().removeClass("has-error");
        $('#addGradeName').next().text("");
        $("#addGradeName").next().hide();
    }

    var classes = $.trim($("#addClasses").val());
    if(classes == ""){
        $('#addClasses').parent().addClass("has-error");
        $('#addClasses').next().html("<i class='fa fa-times-circle-o'></i>    请输入班级");
        $("#addClasses").next().show();
        flag = false;
    }else if(classes<=0 || classes!=parseInt(classes)){
        $('#addClasses').parent().addClass("has-error");
        $('#addClasses').next().html("<i class='fa fa-times-circle-o'></i>    班级必须为正整数");
        $("#addClasses").next().show();
        flag = false;
    }else {
        $('#addClasses').parent().removeClass("has-error");
        $('#addClasses').next().html("");
        $("#addClasses").next().hide();
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

