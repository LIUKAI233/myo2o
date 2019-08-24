$(function () {
    var initUrl = "/myo2o/shopadmin/getshopinitinfo";
    var registerShopUrl = "/myo2o/shopadmin/register";
    shopinitinfo();
    function shopinitinfo(){
       $.getJSON(initUrl , function (date) {
          if (date.success){
              var tempHtml = "";
              var tempAreaHtml = "";
              date.shopCategoryList.map(function (item, index) {
                  tempHtml += "<option id = '" + item.shopCategoryId + "'>" + item.shopCategoryName + "</option>"
              });
              date.areaList.map(function (item, index) {
                  tempAreaHtml += "<option id = '" + item.areaId + "'>" + item.areaName + "</option>"
              });
              $('#shop-category').html(tempHtml);
              $('#area').html(tempAreaHtml);
          }
       });

       $('#submit').click(function () {
           var shop = {};
           shop.shopName = $('#shop-name').val();
           shop.shopAddr = $('#shop-addr').val();
           shop.phone = $('#shop-phone').val();
           shop.shopDesc = $('#shop-desc').val();
           shop.shopCategory={
               shopCategoryId:$('#shop-category').find('option').not(function(){
                   return !this.selected;
               }).data('id')
           };
           shop.area={
               areaId:$('#area').find('option').not(function(){
                   return !this.selected;
               }).data('id')
           };
           var shopImg = $('#shop-img')[0].files[0];
           var formData = new FormData();
           formData.append('shopStr',JSON.stringify(shop));
           formData.append('shopImg',shopImg);
           $.ajax({
               url : registerShopUrl,
               type : 'POST',
               data : formData,
               contentType : false,
               processdata : false,
               cache : false,
               success : function (data) {
                   if(data.success){
                       $.toast('提交成功');
                   }else{
                       $.toast('提交失败'+data.errMsg);
                   }
               }
           });
       });
    }
});