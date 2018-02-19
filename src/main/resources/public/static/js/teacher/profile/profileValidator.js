$(function () {
    /**
     * 获取科目
     */
    $.ajax({
        type: 'POST',
        url: 'getAllSubject',
        cache: false,
        dataType: 'json',
        success: function (result) {
            if(result.stateCode == "403"){
                showInfo(result.message);
                window.location.href = "/403";
            }else{
                //清空之前的
                $("#subject option[value!='0']").remove();
                //设置年级下拉菜单
                var data = result.entity;
                for(var index in data) {
                    var $option = $("<option></option>");
                    $option.attr("value",data[index].subjectId);
                    $option.text(data[index].subjectName);
                    var $subjectElement = $("#subject");
                    $subjectElement.append($option);
                }
            }
        }
    });



    $("#dict-form-profile").bootstrapValidator({
        //live: 'disabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
        //excluded: [':disabled', ':hidden', ':not(:visible)'],//排除无需验证的控件，比如被禁用的或者被隐藏的
        message: '请输入有效值',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: '请输入教师姓名'
                    },
                    callback: {
                        message: '教师姓名必须为中文',
                        callback: function(value, validator) {
                            var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");
                            if($.trim(value) != "" && !reg.test(value)){
                                return false;
                            }else{
                                return true;
                            }
                        }
                    }

                }
            },
            subject: {
                validators: {
                    callback: {
                        message: '请选择科目',
                        callback: function (value, validator) {
                            if (value == 0) {
                                return false;
                            } else {
                                return true;
                            }
                        }
                    }
                }
            },
            phone: {
                validators: {
                    callback:{
                        message: '电话号码格式不正确',
                        callback: function(value, validator) {
                            if($.trim(value) != "" && !(/^1[34578]\d{9}$/.test(value))){
                                return false;
                            }else{
                                return true;
                            }
                        }
                    }
                }
            },
            email: {
                validators: {
                    callback:{
                        message: '邮箱格式不正确',
                        callback: function(value, validator) {
                            if($.trim(value) != "" && !(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(value))){
                                return false;
                            }else{
                                return true;
                            }
                        }
                    }
                    // emailAddress: {
                    //     message: '邮箱格式正确'
                    // },
                }
            }
        }
    });



});