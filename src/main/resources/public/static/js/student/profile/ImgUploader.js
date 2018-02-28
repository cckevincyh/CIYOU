(function( factory ) {
    if ( !window.jQuery ) {
        alert('jQuery is required.')
    }

    jQuery(function() {
        factory.call( null, jQuery );
    });
})
(function( $ ) {
// -----------------------------------------------------
// ------------ START ----------------------------------
// -----------------------------------------------------


// ---------------------------------
// ---------  Uploader -------------
// ---------------------------------
    var Uploader = (function() {

        // -------setting-------
        // 如果使用原始大小，超大的图片可能会出现 Croper UI 卡顿，所以这里建议先缩小后再crop.
        var FRAME_WIDTH = 1600;


        var _ = WebUploader;
        var Uploader = _.Uploader;
        var uploaderContainer = $('.uploader-container');
        var uploader, file;

        if ( !Uploader.support() ) {
            alert( 'Web Uploader 不支持您的浏览器！');
            throw new Error( 'WebUploader does not support the browser you are using.' );
        }

        // hook,
        // 在文件开始上传前进行裁剪。
        Uploader.register({
            'before-send-file': 'cropImage'
        }, {

            cropImage: function( file ) {

                var data = file._cropData,
                    image, deferred;

                file = this.request( 'get-file', file );
                deferred = _.Deferred();

                image = new _.Lib.Image();

                deferred.always(function() {
                    image.destroy();
                    image = null;
                });
                image.once( 'error', deferred.reject );
                image.once( 'load', function() {
                    image.crop( data.x, data.y, data.width, data.height, data.scale );
                });

                image.once( 'complete', function() {
                    var blob, size;

                    // 移动端 UC / qq 浏览器的无图模式下
                    // ctx.getImageData 处理大图的时候会报 Exception
                    // INDEX_SIZE_ERR: DOM Exception 1
                    try {
                        blob = image.getAsBlob();
                        size = file.size;
                        file.source = blob;
                        file.size = blob.size;

                        file.trigger( 'resize', blob.size, size );

                        deferred.resolve();
                    } catch ( e ) {
                        console.log( e );
                        // 出错了直接继续，让其上传原始图片
                        deferred.resolve();
                    }
                });

                file._info && image.info( file._info );
                file._meta && image.meta( file._meta );
                image.loadFromBlob( file.source );
                return deferred.promise();
            }
        });

        return {
            init: function( selectCb ) {
                uploader = new Uploader({
                    pick: {
                        id: '#filePicker',
                        multiple: false
                    },

                    // 设置用什么方式去生成缩略图。
                    thumb: {
                        quality: 70,

                        // 不允许放大
                        allowMagnify: false,

                        // 是否采用裁剪模式。如果采用这样可以避免空白内容。
                        crop: false
                    },

                    // 禁掉分块传输，默认是开起的。
                    chunked: false,

                    // 禁掉上传前压缩功能，因为会手动裁剪。
                    compress: false,

                    // fileSingleSizeLimit: 2 * 1024 * 1024,

                    server: 'StudentImgFileUpload',
                    swf: $.trim($("#BASE_URL").val()) + '/static/webuploader/Uploader.swf',
                    fileNumLimit: 1,
                    // 只允许选择图片文件。
                    accept: {
                        title: 'Images',
                        // extensions: 'gif,jpg,jpeg,bmp,png',
                        // mimeTypes: 'image/*'
                        extensions: 'jpg,jpeg,png',
                        //解决WebUploader chrome 点击上传文件选择框会延迟几秒才会显示 反应很慢
                        mimeTypes: 'image/jpg,image/jpeg,image/png'   //修改这行
                    }
                    //formData: {"Authorization": localStorage.token}, //额外参数传递，可以没有
                    // chunked: true, //分片
                    // chunkSize: 10 * 1024 * 1024, //分片大小指定
                    // threads:1, //线程数量
                    // disableGlobalDnd: true //禁用拖拽

                    // onError: function() {
                    //     var args = [].slice.call(arguments, 0);
                    //     alert(args.join('\n'));
                    // }
                });

                uploader.on('fileQueued', function( _file ) {
                    file = _file;

                    uploader.makeThumb( file, function( error, src ) {

                        if ( error ) {
                            alert('不能预览');
                            return;
                        }

                        selectCb( src );

                    }, FRAME_WIDTH, 1 );   // 注意这里的 height 值是 1，被当成了 100% 使用。
                });

                /**
                 * 验证文件格式以及文件大小
                 */
                uploader.on("error", function (type) {
                    if (type == "Q_TYPE_DENIED") {
                        showInfo("请上传JPG、JEPG、PNG、格式文件");
                    }
                });

                // 文件上传成功，给item添加成功class, 用样式标记上传成功。
                uploader.on( 'uploadSuccess', function( file ) {
                    showInfo("上传成功");
                });

                // 文件上传失败，显示上传出错。
                uploader.on( 'uploadError', function( file ) {
                    showInfo("上传失败");
                });
            },

            crop: function( data ) {

                var scale = Croper.getImageSize().width / file._info.width;
                data.scale = scale;

                file._cropData = {
                    x: data.x1,
                    y: data.y1,
                    width: data.width,
                    height: data.height,
                    scale: data.scale
                };
            },

            upload: function() {
                uploader.upload();
            }
        }
    })();

// ---------------------------------
// ---------  Crpper ---------------
// ---------------------------------
    var Croper = (function() {
        var container = $('.cropper-wraper');
        var $image = container.find('.img-container img');
        var btn = $('.upload-btn');
        var isBase64Supported, callback;

        $image.cropper({
            aspectRatio: 4 / 4,
            preview: ".img-preview",
            done: function(data) {
                // console.log(data);
            }
        });

        function srcWrap( src, cb ) {

            // we need to check this at the first time.
            if (typeof isBase64Supported === 'undefined') {
                (function() {
                    var data = new Image();
                    var support = true;
                    data.onload = data.onerror = function() {
                        if( this.width != 1 || this.height != 1 ) {
                            support = false;
                        }
                    }
                    data.src = src;
                    isBase64Supported = support;
                })();
            }

            if ( isBase64Supported ) {
                cb( src );
            } else {
                // otherwise we need server support.
                // convert base64 to a file.
                // $.ajax('', {
                //     method: 'POST',
                //     data: src,
                //     dataType:'json'
                // }).done(function( response ) {
                //     if (response.result) {
                //         cb( response.result );
                //     } else {
                //         alert("预览出错");
                //     }
                // });
            }
        }

        btn.on('click', function() {
            callback && callback($image.cropper("getData"));
            return false;
        });

        return {
            setSource: function( src ) {

                // 处理 base64 不支持的情况。
                // 一般出现在 ie6-ie8
                srcWrap( src, function( src ) {
                    $image.cropper("setImgSrc", src);
                });

                container.removeClass('webuploader-element-invisible');

                return this;
            },

            getImageSize: function() {
                var img = $image.get(0);
                return {
                    width: img.naturalWidth,
                    height: img.naturalHeight
                }
            },

            setCallback: function( cb ) {
                callback = cb;
                return this;
            },

            disable: function() {
                $image.cropper("disable");
                return this;
            },

            enable: function() {
                $image.cropper("enable");
                return this;
            }
        }

    })();


// ------------------------------
// -----------logic--------------
// ------------------------------
    var container = $('.uploader-container');

    Uploader.init(function( src ) {

        Croper.setSource( src );

        // 隐藏选择按钮。
        container.addClass('webuploader-element-invisible');

        // 当用户选择上传的时候，开始上传。
        Croper.setCallback(function( data ) {
            Uploader.crop(data);
            Uploader.upload();
        });
    });


// -----------------------------------------------------
// ------------ END ------------------------------------
// -----------------------------------------------------
});