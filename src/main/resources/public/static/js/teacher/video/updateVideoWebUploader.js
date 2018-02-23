/**
 * Created by c on 2018/2/22.
 */
jQuery(function() {
    var $ = jQuery,
    $list1 = $('#updateThelist'),
    $btn = $('#updateCtlBtn'),
    state = 'pending', videoUploader;

     videoUploader = WebUploader.create({

        // swf文件路径
        swf: $.trim($("#BASE_URL").val()) + '/static/webuploader/Uploader.swf',

        // 文件接收服务端。
        server: 'videoFileUpload',

        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#updatePicker',
        //fileNumLimit: 1,
        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false,
        accept: {
            title: '视频文件上传',  //文字描述
            extensions: 'mp4',     //允许的文件后缀，不带点，多个用逗号分割。,jpg,png,
            mimeTypes: 'video/mp4'      //多个用逗号分割。,
        },
         duplicate : true,// 是否可重复选择同一文件
        compress: null,
        chunked: true, // 分片处理
        chunkSize: 50 * 1024 * 1024, // 每片50M,经过测试，发现上传1G左右的视频大概每片50M速度比较快的，太大或者太小都对上传效率有影响
        chunkRetry: false,// 如果失败，则不重试
        threads: 1// 上传并发数。允许同时最大上传进程数。
    });

    /**
     * 验证文件格式以及文件大小
     */
    videoUploader.on("error", function (type) {
        if (type == "Q_TYPE_DENIED") {
            showInfo1("请上传mp4格式文件");
        }
    });

// 当有文件被添加进队列的时候
    videoUploader.on('fileQueued', function (file) {
        //清空之前append的
        $list1.empty();
        $list1.append('<div id="' + file.id + '" class="item">' +
            '<h4 class="info">' + file.name + '</h4>' +
            '<p class="state">等待上传...</p>' +
            '</div>');
    });

// 文件上传过程中创建进度条实时显示。
    videoUploader.on('uploadProgress', function (file, percentage) {
        var $li = $('#' + file.id),
            $percent = $li.find('.progress .progress-bar');

        // 避免重复创建
        if (!$percent.length) {
            $percent = $('<div class="progress progress-striped active">' +
                '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                '</div>' +
                '</div>').appendTo($li).find('.progress-bar');
        }

        $li.find('p.state').text('上传中');

        $percent.css('width', percentage * 100 + '%');
    });

    videoUploader.on('uploadSuccess', function (file,response) {
        $('#' + file.id).find('p.state').text('已上传');
        $("#updateVideo").val(response.message);
    });

    videoUploader.on('uploadError', function (file,response) {
        $('#' + file.id).find('p.state').text('上传出错');
    });

    videoUploader.on('uploadComplete', function (file) {
        $('#' + file.id).find('.progress').fadeOut();
    });

    videoUploader.on( 'all', function( type ) {
        if ( type === 'startUpload' ) {
            state = 'uploading';
        } else if ( type === 'stopUpload' ) {
            state = 'paused';
        } else if ( type === 'uploadFinished' ) {
            state = 'done';
        }

        if ( state === 'uploading' ) {
            $btn.text('上传中');
        } else {
            $btn.text('开始上传');
        }
    });

    $btn.click( function() {
        if ( state === 'uploading' ) {
            videoUploader.stop();
        } else {
            videoUploader.upload();
        }
    });
    $('#updateModal').on('shown.bs.modal',function() {//修改提示框显示时候触发
        videoUploader.refresh();  	//刷新当前webUploder
    });

});

function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


function showInfo1(msg) {
    $("#div_info1").text(msg);
    $("#modal_info1").modal('show');
}

