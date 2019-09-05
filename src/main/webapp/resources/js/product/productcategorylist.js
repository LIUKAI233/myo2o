$(function () {
    var listUrl = '/myo2o/productadmin/getproductcategorylist';
    var addUrl = '/myo2o/productadmin/addproductcategorys';
    var deleteUrl = '/myo2o/productadmin/removeproductcategory?productCategoryId=';

    function getlist() {
        $.ajax({
            url: listUrl,
            type: "get",
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    handleList(data.productCategoryList);
                }
            }
        });
    }

    function handleList(data) {
        var html = '';
        data.map(function(item, index) {
            html += '<div class="row row-product"><div class="col-40">' + item.productCategoryName + '</div><div class="col-40">' + item.priority + '</div><div class="col-20">' + deleteProductCategory(item.productCategoryId) + '</div></div>';

        });
        $('.product-wrap').html(html);
    }

    $('#new').click(function () {
                var tempHtml = '<div class="row row-product-category temp">'
                    + '<div class="col-33"><input class="category-input category" type="text" placeholder="分类名"></div>'
                    + '<div class="col-33"><input class="category-input priority" type="number" placeholder="优先级"></div>'
                    + '<div class="col-33"><a href="#" class="button delete">删除</a></div>'
                    + '</div>';
                $('.category-wrap').append(tempHtml);
                    });

    $('#submit').click(function() {
        var tempArr = $('.temp');
        var productCategoryList = [];
        tempArr.map(function(index, item) {
            var tempObj = {};
            tempObj.productCategoryName = $(item).find('.category').val();
            tempObj.priority = $(item).find('.priority').val();
            if (tempObj.productCategoryName && tempObj.priority) {
                productCategoryList.push(tempObj);
            }
        });
        $.ajax({
            url : addUrl,
            type : 'POST',
            data : JSON.stringify(productCategoryList),
            contentType : 'application/json',
            success : function(data) {
                if (data.success) {
                    $.toast('提交成功！');
                    getList();
                } else {
                    $.toast('提交失败！');
                }
            }
        });
    });

    function deleteProductCategory(id) {
        return '<a href=' + deleteUrl + id + '>删除</a>';
    }

    getlist();
});
