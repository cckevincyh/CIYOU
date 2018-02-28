$(function () {


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
                        message: '请输入学生姓名'
                    },
                    callback: {
                        message: '学生姓名必须为中文',
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
            age: {
                validators: {
                    callback: {
                        message: '年龄必须为正整数',
                        callback: function (value, validator) {
                            if ($.trim(value) != "" && (value<=0 || value!=parseInt(value))){
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
            },
            parent_phone: {
                validators: {
                    callback:{
                        message: '家长电话号码格式不正确',
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
            parent_email: {
                validators: {
                    callback:{
                        message: '家长邮箱格式不正确',
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