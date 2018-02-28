$(function () {

    $("#dict-form-password").bootstrapValidator({
        //live: 'disabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
        //excluded: [':disabled', ':hidden', ':not(:visible)'],//排除无需验证的控件，比如被禁用的或者被隐藏的
        message: '请输入有效值',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            oldPwd: {
                validators: {
                    notEmpty: {
                        message: '请输入原密码'
                    }
                }
            },
            newPwd: {
                validators: {
                    notEmpty: {
                        message: '请输入新密码'
                    },
                    stringLength: {
                        min: 3,
                        max: 15,
                        message: '密码长度在3到15之间'
                    }
                }
            },
            confirmPwd: {
                validators: {
                    notEmpty: {
                        message: '请输入确认密码'
                    },
                    identical: {
                        field: 'newPwd',
                        message: '两次密码不相同'
                    }
                }
            }

        }
    });



});