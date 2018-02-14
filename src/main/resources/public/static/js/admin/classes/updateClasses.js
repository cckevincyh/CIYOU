/**
 * ajax 提交修改班级
 */
$(function () {
	

    $('#updateClass').click(function () {

    	
   	 if (!validUpdateClasses()) {
         return;
     }


		$.ajax({
			type: 'POST',
			url: 'updateClasses',
			cache: false,
			dataType:'json',
			data: {
				classesId: $.trim($("#updateClassesId").val()),
				classes: $.trim($("#updateClasses").val()),
				gradeId :$.trim($("#updateGradeName").val())
			},
			success: function (data) {
				if(data.stateCode == "403"){
					showInfo(data.message);
					window.location.href = "/403";
				}else if(data.isSuccess){
					$("#updateModal").modal("hide");//关闭模糊框
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







function updateClasses(id){
	$("#updateGradeName option[value!=0]").remove();//移除先前的选项
	//先获取到年级列表
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

					document.getElementById("updateGradeName").appendChild(op);
				}
				//获取年级列表完毕之后在获取详细的信息
				$.ajax({
					type: 'POST',
					url: 'getClasses',
					cache: false,
					dataType:'json',
					data: {
						classesId: id
					},
					success: function (data) {
						if(data.stateCode == "403"){
							showInfo(data.message);
							window.location.href = "/403";
						}else{
							$("#updateClassesId").val(data.entity.classesId);
							$("#updateClasses").val(data.entity.classes);
							$("#updateGradeName").val(data.entity.grade.gradeId);
						}
					}
				});
			}

		},
		error: function (jqXHR, textStatus, errorThrown) {
			showInfo("获取年级列表失败，请重试");
		}
	});

}


function validUpdateClasses() {
	var flag = true;

	var gradeName = $.trim($("#updateGradeName").val());
	if (gradeName == 0) {
		$('#updateGradeName').parent().addClass("has-error");
		$('#updateGradeName').next().html("<i class='fa fa-times-circle-o'></i>    请选择年级");
		$("#updateGradeName").next().show();
		flag = false;
	}else {
		$('#updateGradeName').parent().removeClass("has-error");
		$('#updateGradeName').next().text("");
		$("#updateGradeName").next().hide();
	}

	var classes = $.trim($("#updateClasses").val());
	if(classes == ""){
		$('#updateClasses').parent().addClass("has-error");
		$('#updateClasses').next().html("<i class='fa fa-times-circle-o'></i>    请输入班级");
		$("#updateClasses").next().show();
		flag = false;
	}else if(classes<=0 || classes!=parseInt(classes)){
		$('#updateClasses').parent().addClass("has-error");
		$('#updateClasses').next().html("<i class='fa fa-times-circle-o'></i>    班级必须为正整数");
		$("#updateClasses").next().show();
		flag = false;
	}else {
		$('#updateClasses').parent().removeClass("has-error");
		$('#updateClasses').next().html("");
		$("#updateClasses").next().hide();
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
