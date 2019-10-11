$(function () {
    var shopAuthId = getQueryString("shopAuthId");
    //通过id获取信息的URL
    var queryInfoUrl = '/myo2o/shopadmin/getshopauthmapbyid?shopAuthId='+shopAuthId;
    //更改权限的URL
    var modifyInfoUrl = '/myo2o/shopadmin/modifyshopauthmap';

    if(shopAuthId){
        getInfo();
    }else{
        $.toast("用户信息不存在");
        window.location.href="/myo2o/shopadmin/shopmanage"
    }

    function getInfo() {
        $.getJSON(queryInfoUrl,function (data) {
            if (data.success){
                var shopAuthMap = data.shopAuthMap;
                //给html赋值
                $('#shopauth-name').val(shopAuthMap.employee.name);
                $('#title').val(shopAuthMap.title);
            }
        })
    }

    $('#submit').click(function () {
        var shopAuth = {};
        var employee = {};
        //获取更改后的值
        employee.name = $('#shopauth-name').val();
        shopAuth.title = $('#title').val();
        shopAuth.employee = employee;
        shopAuth.shopAuthId = shopAuthId;
        //获取验证码
        var verifyCodeActul = $('#j-kaptcha').val();
        if(verifyCodeActul == null || verifyCodeActul === ""){
            $.toast("请输入验证码！");
            return;
        }
        $.ajax({
            url:modifyInfoUrl,
            type:'POST',
            contentType:"application/x-www-form-urlencoded;charset=utf-8",
            data:{
                //将json参数转化为字符串
                shopAuthMapStr : JSON.stringify(shopAuth),
                verifyCodeActul : verifyCodeActul,
                statusChange : true
            },
            success:function (data) {
                if(data.success){
                    $.toast("修改成功");
                }else{
                    $.toast(data.errMsg);
                }
                $('#kaptcha_img').click();
                $('#j-kaptcha').val("");
            }
        })
    })
});