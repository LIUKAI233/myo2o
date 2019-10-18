$(function () {
    var awardName = '';
    var listUrl = '/myo2o/shopadmin/listawards?pageIndex=1&pageSize=99&awardName='+awardName;
    var modifyUrl = '/myo2o/shopadmin/modifyaward?awardId=';

    getList();

    //获取奖品列表
    function getList() {
        $.getJSON(listUrl,function (data) {
            if(data.success){
                var awardList = data.awardList;
                var tempHtml = '';
                awardList.map(function (item, index) {
                    //默认定义状态为下架，执行上架操作，状态值要更改为1
                    var ops = '上架';
                    var contrayStatus = 1;
                    if (item.enableStatus === 1){
                        //如果商品处于上架状态，执行下架操作，状态值为0
                        ops = '下架';
                        contrayStatus = 0;
                    }
                    tempHtml +=  '<div class="row row-award">'
                                +'<div class="award-name col-33">'+item.awardName+'</div>'
                                + '<div class="col-20">'+item.point+'</div>'
                                + '<div class="col-40">'
                                + '<a class="edit" data-award-id="'+item.awardId+'">编辑</a>'
                                + '<a class="changeOps" data-award-id="'+item.awardId+'" data-status="'+contrayStatus+'">'+ops+'</a>'
                                + '<a class="view" data-award-id="'+item.awardId+'">预览</a>'
                                + '</div></div>'
                });
                $('.award-wrap').html(tempHtml);
            }
        });
    }

    $('.award-wrap').on('click', 'a',function (e) {
        var target = $(e.currentTarget);
        var awardId = e.target.dataset.awardId;
        if(target.hasClass('edit')){
            //则说明跳转到编辑页面
            window.location.href = '/myo2o/shopadmin/awardoperation?awardId='+awardId;
        }
        if (target.hasClass('changeOps')){
            var contrayStatus = e.currentTarget.dataset.status;
            //则说明，执行上下架操作
            changeOps(awardId,contrayStatus);
        }
        if (target.hasClass('view')){
            //则说明跳转到预览页面
            window.location.href = '/myo2o/shopadmin/awarddetail?awardId='+awardId;
        }
    });

    /*更改上下架方法*/
    function changeOps(awardId,contrayStatus){
        var award = {};
        award.awardId = awardId;
        award.enableStatus = contrayStatus;
        $.ajax({
            url : modifyUrl+awardId,
            type : 'post',
            data : {
                awardStr : JSON.stringify(award),
                changeState : false
            },
            DataType : 'JSON',
            success : function (data) {
                if(data.success){
                    $.toast("操作成功!");
                    getList();
                }else{
                    $.toast("操作失败!");
                }
            }
        });
    }

    $('#search').on('change',function (e) {
        //获取输入框中内容
        awardName = e.target.value;
        //清空列表信息
        $('#award-wrap').empty();
        //重新加载列表
        getList();
    })
});