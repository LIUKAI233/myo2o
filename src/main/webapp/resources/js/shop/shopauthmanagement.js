$(function () {
    //列出该店铺下的所有授权信息的URL
    var listUrl = '/myo2o/shopadmin/listshopauthmaplistbyshopid?pageIndex=1&pageSize=5';
    //修改授权信息的Url
    var modifyUrl = '/myo2o/shopadmin/modifyshopauthmap';

    function getList() {
        $.getJSON(listUrl,function (data) {
           if(data.success){
               var shopAuthList = data.shopAuthMapList;
               var tempHtml = '';
               if (shopAuthList == null){
                   return;
               }
               //遍历店铺权限数组
               shopAuthList.map(function (item, index) {
                   var textOp = '恢复';
                   var contraryStatus = 0;
                   //若状态值为1，表明授权生效，操作变为删除
                   if(item.enableStatus === 1){
                       textOp = "删除";
                       contraryStatus = 0;
                   }else{
                       contraryStatus = 1;
                   }
                   tempHtml += '<div class="row row-shopauth">'
                                +'<div class="col-33 shopauth-name">'
                                + item.employee.name
                                +'</div>';
                   if(item.titleFlag !== 0){
                       //若不是店家本人的授权信息，则加入编辑，改变状态等操作
                        tempHtml += '<div class="col-20">'+item.title
                                +'</div>'+'<div class="col-40">'
                                +'<a href="#" class="edit" data-employee-id="'
                                +item.employee.userId + '" data-auth-id="'
                                +item.shopAuthId+'">编辑</a>'
                                +'<a href="#" class="status" data-auth-id="'
                                +item.shopAuthId+'" data-status="'
                                +contraryStatus+'">'+textOp+'</a>'
                                +'</div>'
                   }else{
                       //若为店家则不允许操作
                       tempHtml += '<div class="col-20">'+item.title
                            +'</div>'+'<div class="col-40">'
                            +'不可操作'+'</div>'
                   }
                   tempHtml += '</div>';
               });
               $(".shopauth-wrap").html(tempHtml);
           }
        });
    }

    /*
     * 给a标签的click事件绑定上对应的方法，即点击带有edit的a标签就会跳转到授权编辑页面
     * 点击带有status的a标签就会去更新该授权信息的状态
     */
    $('.shopauth-wrap').on('click','a',function (e) {
        var target = $(e.currentTarget);
        if (target.hasClass('edit')){
            window.location.href = '/myo2o/shopadmin/shopauthedit?shopAuthId='+e.currentTarget.dataset.authId;
        } else if(target.hasClass('status')){
            changeStatus(e.currentTarget.dataset.authId,e.currentTarget.dataset.status);
        }
    });

    function changeStatus(id , status) {
        var shopAuth = {};
        shopAuth.shopAuthId = id;
        shopAuth.enableStatus = status;
        $.confirm('确定么?', function () {
            $.ajax({
                url: modifyUrl,
                type: 'POST',
                data: {
                    shopAuthMapStr : JSON.stringify(shopAuth),
                    statusChange : false
                },
                dataType: 'json',
                success: function (data) {
                    if (data.success) {
                        $.toast('操作成功！');
                        getList();
                    } else {
                        $.toast('操作失败！');
                    }
                }
            });
        });
    }

    getList();

});