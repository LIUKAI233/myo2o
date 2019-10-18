$(function () {
    //从连接中获取奖品id
    var awardId = getQueryString("awardId");
    //判断是否有奖品id，有则是修改，没有则是添加
    var isEdit = !!awardId;
    //修改信息url
    var modifyUrl = '/myo2o/shopadmin/modifyaward';
    //添加奖品url
    var addUrl = '/myo2o/shopadmin/addaward';
    //获取奖品信息url
    var infoUrl = '/myo2o/shopadmin/getawardbyid?awardId='+awardId;

    //判断是修改奖品还是添加奖品
    if (isEdit){
        //修改奖品,获取奖品信息
        getAwardInfo();
    }

    function getAwardInfo() {
        $.getJSON(infoUrl,function (data) {
           if (data.success){
               //获取奖品信息
               var award = data.award;
               //填充奖品信息
               $('#award-name').val(award.awardName);
               $('#point').val(award.point);
               $('#priority').val(award.priority);
               $('#award-img').val(award.awardImg);
               $('#award-desc').val(award.awardDesc);
           }
        });
    }

    $('#submit').click(function () {
       var award = {};
       //获取奖品相关信息
       award.awardName = $('#award-name').val();
       award.point = $('#point').val();
       award.priority = $('#priority').val();
       award.awardDesc = $('#award-desc').val();
       //若是修改则添加奖品id，添加则为空
       award.awardId = awardId;
       //获取传入的图片信息
       var thumbnail = $('#award-img')[0].files[0];
       //获取验证码相关信息
       var verifyCodeActul = $('#j-kaptcha').val();
       if (verifyCodeActul == null && verifyCodeActul === "") {
           $.toast("请输入验证码");
           return;
       }
       //生成表单对象，用于接收参数并返回给后台
       var formData = new FormData();
       formData.append('awardStr',JSON.stringify(award));
       formData.append('thumbnail',thumbnail);
       formData.append('verifyCodeActul',verifyCodeActul);
       formData.append('changeState',true);
       $.ajax({
          url : (isEdit ? modifyUrl : addUrl),
          type : 'POST',
          data : formData,
          contentType: false,
          processData: false,
          cache: false,
          success : function (data) {
              if (data.success){
                  $.toast("更改成功");
                  //跳转到管理页面
                  window.location.href = '/myo2o/shopadmin/awardmanagement';
              }else{
                  $.toast("更改失败"+data.errMsg);
              }
              $('#kaptcha_img').click();
              document.getElementById('j-kaptcha').value = '';
          }
       });
    });
});