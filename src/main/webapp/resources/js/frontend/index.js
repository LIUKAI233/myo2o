$(function() {
    var url = '/myo2o/frontend/listmainpageinfo';

    $.getJSON(url, function (data) {
        if (data.success) {
            var headLineList = data.headLineList;
            var swiperHtml = '';
            headLineList.map(function (item, index) {
                swiperHtml += ''
                    + '<div class="swiper-slide img-wrap">'
                    +      '<img class="banner-img" src="'+ item.lineImg +'" alt="'+ item.lineName +'">'
                    + '</div>';
            });
            $('.swiper-wrapper').html(swiperHtml);
            $(".swiper-container").swiper({
                autoplay: 2000,
                autoplayDisableOnInteraction: false
            });
            var shopCategoryList = data.shopCategoryList;
            var categoryHtml = '';
            shopCategoryList.map(function (item, index) {
                categoryHtml += ''
                    +  '<div class="col-50 shop-classify" data-category='+ item.shopCategoryId +'>'
                    +      '<div class="word">'
                    +          '<p class="shop-title">'+ item.shopCategoryName +'</p>'
                    +          '<p class="shop-desc">'+ item.shopCategoryDesc +'</p>'
                    +      '</div>'
                    +      '<div class="shop-classify-img-warp">'
                    +          '<img class="shop-img" src="'+ item.shopCategoryImg +'">'
                    +      '</div>'
                    +  '</div>';
            });
            $('.row').html(categoryHtml);
        }
    });

    /*打开关闭侧栏*/
    $('#me').click(function () {
        $.openPanel('#panel-left-demo');
    });

    /*点击事件，查找对应的店铺类别列表*/
    $('.row').on('click', '.shop-classify', function (e) {
        var shopCategoryId = e.currentTarget.dataset.category;
        var newUrl = '/myo2o/frontend/shoplist?parentId=' + shopCategoryId;
        window.location.href = newUrl;
    });

});
