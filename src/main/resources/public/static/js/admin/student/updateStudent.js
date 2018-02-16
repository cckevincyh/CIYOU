/**
 * Created by c on 2018/2/15.
 */
$(function () {


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
                //清空之前的
                $("#updateGrade option[value!='0']").remove();
                //设置年级下拉菜单
                /*
                 * <select id="addGrade" name="addGrade">
                 <option value="">请选择....</option>
                 </select>
                 */
                var data = result.entity;
                for(var index in data) {
                    var $option = $("<option></option>");
                    $option.attr("value",data[index].gradeId);
                    $option.text(data[index].gradeName);
                    var $gradeElement = $("#updateGrade");
                    $gradeElement.append($option);
                }

            }

        }
    });


    /**
     * 年级下拉框改变时调用
     */
    $("#updateGrade").change(function() {
        //获得选中的年级ID
        var $gradeValue = $("#updateGrade").val();
        $.ajax({
            type: 'POST',
            url: 'getClassesByGrade',
            cache: false,
            dataType:'json',
            data:{
                gradeId : $gradeValue
            },
            success: function (result) {
                if(result.stateCode == "403"){
                    showInfo(result.message);
                    window.location.href = "/403";
                }else if(result.isSuccess){
                    //清楚班级下拉框之前的项目
                    $("#updateClasses option[value!='0']").remove();
                    //设置年级下拉菜单
                    /*
                     * <select id="addClasses" name="addClasses">
                     <option value="">请选择....</option>
                     </select>
                     */
                    var data = result.entity;
                    for(var index in data) {
                        var $option = $("<option></option>");
                        $option.attr("value",data[index].classesId);
                        $option.text(data[index].classes);
                        var $classesElement = $("#updateClasses");
                        $classesElement.append($option);
                    }
                }else{
                    showInfo1(result.message)
                }

            }
        });
    });
});
/**
 * 修改学生
 */
function updateStudent(id){
    $.ajax({
        type: 'POST',
        url: 'getStudentById',
        cache: false,
        dataType: 'json',
        data: {
            sid: id
        },
        success: function (data) {
            if(data.stateCode == "403"){
                showInfo(data.message);
                window.location.href = "/403";
            }else{
                if(data.entity.classes != null){
                    $.ajax({
                        type: 'POST',
                        url: 'getClassesByGrade',
                        cache: false,
                        async: false,
                        dataType:'json',
                        data:{
                            gradeId : data.entity.classes.grade.gradeId
                        },
                        success: function (result) {
                            if(result.stateCode == "403"){
                                showInfo(result.message);
                                window.location.href = "/403";
                            }else if(result.isSuccess){
                                //清楚班级下拉框之前的项目
                                $("#updateClasses option[value!='0']").remove();
                                //设置年级下拉菜单
                                /*
                                 * <select id="addClasses" name="addClasses">
                                 <option value="">请选择....</option>
                                 </select>
                                 */
                                var data1 = result.entity;
                                for(var index in data1) {
                                    var $option = $("<option></option>");
                                    $option.attr("value",data1[index].classesId);
                                    $option.text(data1[index].classes);
                                    var $classesElement = $("#updateClasses");
                                    $classesElement.append($option);
                                }

                                $("#updateStudentId").val(data.entity.studentId);
                                $("#updateName").val(data.entity.name);
                                $("#updateGrade").val(data.entity.classes.grade.gradeId);
                                $("#updateClasses").val(data.entity.classes.classesId);
                                $(":radio[name='updateSex'][value='" + data.entity.sex + "']").attr("checked", "checked");
                                $("#updateAge").val(data.entity.age);
                                $("#updatePhone").val(data.entity.mobile);
                                $("#updateEmail").val(data.entity.email);
                                $("#updateParentPhone").val(data.entity.parentMobile);
                                $("#updateParentEmail").val(data.entity.parentEmail);
                            }else{
                                showInfo1(result.message)
                            }

                        }
                    });
                }else{
                    //当班级没有的时候
                    $("#updateGrade").val(0);
                    $("#updateClasses").val(0);
                    $("#updateStudentId").val(data.entity.studentId);
                    $("#updateName").val(data.entity.name);
                    $(":radio[name='updateSex'][value='" + data.entity.sex + "']").attr("checked", "checked");
                    $("#updateAge").val(data.entity.age);
                    $("#updatePhone").val(data.entity.mobile);
                    $("#updateEmail").val(data.entity.email);
                    $("#updateParentPhone").val(data.entity.parentMobile);
                    $("#updateParentEmail").val(data.entity.parentEmail);
                }
            }
        }
    });

}