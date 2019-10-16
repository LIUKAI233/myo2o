$(function () {
    var awardName = '';
    var listUrl = '/myo2o/shopadmin/listawards?pageIndex=1&pageSize=99&awardName='+awardName;
    var modifyUrl = '/myo2o/shopadmin/modifyaward?awardId=';

    getList()

    //获取奖品列表
    function getList() {
        $.getJSON(listUrl,function (data) {
            if(data.success){
                var awardList = data.awardList;
                var tempHtml = '';
                var ops = '上架'
                awardList.map(function (item, index) {
                    if (item.enableStatus === 1){
                        ops = '下架';
                    }
                    tempHtml += '<div class="col-40">'+item.awardName+'</div>'
                                + '<div class="col-40">'+item.point+'</div>'
                                + '<div class="col-20">'
                                + '<a class="edit" data-award-id="'+item.awardId+'">'+编辑+'</a>'
                                + '<a class="changeOps">'+ops+'</a>'
                                + '<a class="view">'+预览+'</a>'
                                + '</div>'
                });
            }
            $('#award-wrap').html(tempHtml);
        });
    }

    $('#award-wrap a').on('click',function (e) {
        var awardId = e.target.dataset.awardId;
        if($(e.target).hasClass('edit')){
            //则说明跳转到编辑页面
            window.location.href = '/myo2o/shopadmin/awardedit?awardId='+awardId;
        }
        if ($(e.target).hasClass('changeOps')){
            //则说明，执行上下架操作
            changeOps(awardId);
        }
        if ($(e.target).hasClass('view')){
            //则说明跳转到预览页面
            window.location.href = '/myo2o/shopadmin/awarddetail';
        }
    });

    function changeOps(awardId){
        $.ajax({
            url : modifyUrl+awardId,
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