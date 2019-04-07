<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>签名验证</title>
</head>

<body>
签名校验.<br>
timestamp:${timestamp} <br>
nonceStr:${nonceStr} <br>
signature:${signature} <br>

<button class="btn btn_primary" id="checkJsApi">checkJsApi</button>

<h3 id="menu-image">图像接口</h3>
<span class="desc">拍照或从手机相册中选图接口</span>
<button class="btn btn_primary" id="chooseImage">chooseImage</button>
<span class="desc">预览图片接口</span>
<button class="btn btn_primary" id="previewImage">previewImage</button>
<span class="desc">上传图片接口</span>
<button class="btn btn_primary" id="uploadImage">uploadImage</button>
<span class="desc">下载图片接口</span>
<button class="btn btn_primary" id="downloadImage">downloadImage</button>
</body>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
    wx.config({
        debug: true,
        appId: '${appId}',
        timestamp: '${timestamp}',
        nonceStr: '${nonceStr }',
        signature: '${signature}',
        jsApiList: [
            'checkJsApi',
            'onMenuShareTimeline',
            'onMenuShareAppMessage',
            'onMenuShareQQ',
            'onMenuShareWeibo',
        ]
    });

    wx.ready(function () {
        // 1 判断当前版本是否支持指定 JS 接口，支持批量判断
        document.querySelector('#checkJsApi').onclick = function () {
            wx.checkJsApi({
                jsApiList: [
                    'getNetworkType',
                    'previewImage'
                ],
                success: function (res) {
                    alert(JSON.stringify(res));
                }
            });
        };

        // 2. 分享接口
        // 2.1 监听“分享给朋友”，按钮点击、自定义分享内容及分享结果接口
        document.querySelector('#onMenuShareAppMessage').onclick = function () {
            wx.onMenuShareAppMessage({
                title: '互联网之子',
                desc: '在长大的过程中，我才慢慢发现，我身边的所有事，别人跟我说的所有事，那些所谓本来如此，注定如此的事，它们其实没有非得如此，事情是可以改变的。更重要的是，有些事既然错了，那就该做出改变。',
                link: 'http://movie.douban.com/subject/25785114/',
                imgUrl: 'http://demo.open.weixin.qq.com/jssdk/images/p2166127561.jpg',
                trigger: function (res) {
                    // 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回
                    alert('用户点击发送给朋友');
                },
                success: function (res) {
                    alert('已分享');
                },
                cancel: function (res) {
                    alert('已取消');
                },
                fail: function (res) {
                    alert(JSON.stringify(res));
                }
            });
            alert('已注册获取“发送给朋友”状态事件');
        };
    }

</script>
<script src="http://res.wx.qq.com/open/js/zepto.min.js"></script>
</html>
