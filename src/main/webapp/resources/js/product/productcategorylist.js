$(function () {
    var listUrl = '/myo2o/productadmin/getproductcategorylist';
    var addUrl = '/myo2o/productadmin/addproductcategorys';
    var deleteUrl = '/myo2o/productadmin/removeproductcategory';
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
        data.map(function (item, index) {
            html += '<div class="row row-product"><div class="col-40">' + item.productCategoryName + '</div><div class="col-40">' + item.priority + '</div><div class="col-20">' + deleteProductCategory(item.productCategoryId) + '</div></div>';

        });
        $('.product-wrap').html(html);
    }

    function deleteProductCategory(id){
        return '<a href="/myo2o/productadmin/####?productCategoryId=' + id + '">删除</a>';
    }

    $('#log-out').click(function () {
        $.ajax({
            url: "/myo2o/shop/logout",
            type: "post",
            contentType: false,
            processData: false,
            cache: false,
            success: function (data) {
                if (data.success) {
                    window.location.href = '/myo2o/shop/ownerlogin';
                }
            },
            error: function (data, error) {
                alert(error);
            }
        });
    });


    getlist();
});
