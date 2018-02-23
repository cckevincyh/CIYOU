/**
 * Created by c on 2018/2/21.
 */
// 初始化Web Uploader
jQuery(function() {
    var $ = jQuery,
    $list = $('#fileList'),
    ratio = window.devicePixelRatio || 1,

    thumbnailWidth = 200 * ratio,
    thumbnailHeight = 200 * ratio, uploader;
     uploader = WebUploader.create({

        // 选完文件后，是否自动上传。
        auto: true,

        // swf文件路径
        swf: $.trim($("#BASE_URL").val()) + '/static/webuploader/Uploader.swf',
        //fileNumLimit: 1,
        // 文件接收服务端。
        server: 'VideoImgFileUpload',

        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#filePicker',
         duplicate : true,// 是否可重复选择同一文件
        // 只允许选择图片文件。
        accept: {
            title: 'Images',
            // extensions: 'gif,jpg,jpeg,bmp,png',
            // mimeTypes: 'image/*'
            extensions: 'jpg,jpeg,png',
            //解决WebUploader chrome 点击上传文件选择框会延迟几秒才会显示 反应很慢
            mimeTypes: 'image/jpg,image/jpeg,image/png'   //修改这行
        }
    });

    /**
     * 验证文件格式以及文件大小
     */
    uploader.on("error", function (type) {
        if (type == "Q_TYPE_DENIED") {
            showInfo1("请上传jpg,jpeg,png格式文件");
        }
    });

// 当有文件添加进来的时候
    uploader.on('fileQueued', function (file) {
        var $li = $(
                '<div id="' + file.id + '" class="file-item thumbnail">' +
                '<img>' +
                '<div class="info">' + file.name + '</div>' +
                '</div>'
            ),
            $img = $li.find('img');

        $list.empty();
        // $list为容器jQuery实例
        $list.append($li);

        // 创建缩略图
        // 如果为非图片文件，可以不用调用此方法。
        // thumbnailWidth x thumbnailHeight 为 200 x 200
        uploader.makeThumb(file, function (error, src) {
            if (error) {
                $img.replaceWith('<span>不能预览</span>');
                return;
            }

            $img.attr('src', src);
        }, thumbnailWidth, thumbnailHeight);


        // 文件上传成功，给item添加成功class, 用样式标记上传成功。
        uploader.on('uploadSuccess', function (file, response) {
            $('#' + file.id).addClass('upload-state-done');
            $("#addVideoImg").val(response.message);
        });


// 文件上传失败，显示上传出错。
        uploader.on('uploadError', function (file, response) {
            var $li = $('#' + file.id),
                $error = $li.find('div.error');
            // 避免重复创建
            if (!$error.length) {
                $error = $('<div class="error"></div>').appendTo($li);
            }

            $error.text('上传失败');
        });
    });


    $('#addModal').on('shown.bs.modal',function() {//添加提示框显示时候触发
        uploader.refresh();  	//刷新当前webUploder
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

