$(function () {
    /**
     * 校验添加子权限
     */
    $("#dict-form-add").bootstrapValidator({
        //live: 'disabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
        //excluded: [':disabled', ':hidden', ':not(:visible)'],//排除无需验证的控件，比如被禁用的或者被隐藏的
        message: '请输入有效值',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            addName: {
                validators: {
                    notEmpty: {
                        message: '请输入权限名'
                    },
                    remote: {//将内容发送至指定页面验证，返回验证结果，比如查询用户名是否存在
                        url: "validatePermissionName",
                        data: function (validator) {
                            return {
                                permissionName:$('#addName').val()
                            };
                        },
                        delay: 2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                        type: 'POST',//请求方式
                        message: '该权限名已被使用'
                    }
                }
            },
            addPermission: {
                validators: {
                    notEmpty: {
                        message: '请输入权限字符串'
                    }
                }
            },
            addURL: {
                validators: {
                    notEmpty: {
                        message: '请输入资源路径'
                    }
                }
            }
        }
    });

    /**
     * 校验添加根权限
     */
    $("#dict-form-root").bootstrapValidator({
        //live: 'disabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
        //excluded: [':disabled', ':hidden', ':not(:visible)'],//排除无需验证的控件，比如被禁用的或者被隐藏的
        message: '请输入有效值',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            addRootName: {
                validators: {
                    notEmpty: {
                        message: '请输入权限名'
                    },
                    remote: {//将内容发送至指定页面验证，返回验证结果，比如查询用户名是否存在
                        url: "validatePermissionName",
                        data: function (validator) {
                            return {
                                permissionName:$('#addRootName').val()
                            };
                        },
                        delay: 2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                        type: 'POST',//请求方式
                        message: '该权限名已被使用'
                    }
                }
            },
            addRootPermission: {
                validators: {
                    notEmpty: {
                        message: '请输入权限字符串'
                    }
                }
            },
            addRootURL: {
                validators: {
                    notEmpty: {
                        message: '请输入资源路径'
                    }
                }
            }
        }
    });

    /**
     * 校验修改权限
     */
    $("#dict-form-edit").bootstrapValidator({
        //live: 'disabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
        //excluded: [':disabled', ':hidden', ':not(:visible)'],//排除无需验证的控件，比如被禁用的或者被隐藏的
        message: '请输入有效值',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            editName: {
                validators: {
                    notEmpty: {
                        message: '请输入权限名'
                    },
                    remote: {//将内容发送至指定页面验证，返回验证结果，比如查询用户名是否存在
                        url: "validatePNameByUpdate",
                        data: function (validator) {
                            return {
                                permissionId: $("#editId").val(),
                                permissionName:$('#editName').val()
                            };
                        },
                        delay: 2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                        type: 'POST',//请求方式
                        message: '该权限名已被使用'
                    }
                }
            },
            editPermission: {
                validators: {
                    notEmpty: {
                        message: '请输入权限字符串'
                    }
                }
            },
            editURL: {
                validators: {
                    notEmpty: {
                        message: '请输入资源路径'
                    }
                }
            }
        }
    });



});