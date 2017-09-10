goods = {
    bookId: 0,
    bookPic: "",
    bookName: "",
    bookAuthor: "",
    publisher: "",
    num: 0,
    bookPrice: 0.0,
};

orderdetail = {
    username: "",
    totalNumber: 0,
    totalAmount: 0.00
};

cart = {
    addGoods: function (goods) {
        var ShoppingCart = localStorage.getItem("ShoppingCart");
        var jsonStr;
        if (ShoppingCart == null || ShoppingCart == "") {
            //第一次加入东西
            jsonStr = {
                "goodsList": [
                    {
                        "bookId": goods.bookId,
                        "bookPic": goods.bookPic,
                        "bookName": goods.bookName,
                        "bookAuthor": goods.bookAuthor,
                        "publisher": goods.publisher,
                        "num": goods.num,
                        "bookPrice": goods.bookPrice
                    }
                ],
                "totalNumber": goods.num,
                "totalAmount": (goods.bookPrice * goods.num)

            };
            localStorage.setItem("ShoppingCart", JSON.stringify(jsonStr));
        } else {
            jsonStr = JSON.parse(ShoppingCart.substr(0, ShoppingCart.length));
            var goodsList = jsonStr.goodsList;
            var result = false;
            for (var i in goodsList) {
                if (goodsList[i].bookId === goods.bookId) {
                    goodsList[i].num = parseInt(goodsList[i].num) + parseInt(goods.num);
                    result = true;
                }
            }

            if (!result) {
                goodsList.push(
                    {
                        "bookId": goods.bookId,
                        "bookPic": goods.bookPic,
                        "bookName": goods.bookName,
                        "bookAuthor": goods.bookAuthor,
                        "publisher": goods.publisher,
                        "num": goods.num,
                        "bookPrice": goods.bookPrice
                    }
                );
            }
            //重新计算总价
            jsonStr.totalNumber = parseInt(jsonStr.totalNumber) + parseInt(goods.num);
            jsonStr.totalAmount = parseFloat(jsonStr.totalAmount) + (parseInt(goods.num) * parseFloat(goods.bookPrice));
            orderdetail.totalNumber = jsonStr.totalNumber;
            orderdetail.totalAmount = jsonStr.totalAmount;
            localStorage.setItem("ShoppingCart", JSON.stringify(jsonStr));
        }
    },

    getGoodsList: function () {
        var ShoppingCart = localStorage.getItem("ShoppingCart");
        if (ShoppingCart == null || ShoppingCart == "") {
            return false;
        }
        var jsonStr = JSON.parse(ShoppingCart.substr(0, ShoppingCart.length));
        var goodsList = jsonStr.goodsList;
        orderdetail.totalNumber = jsonStr.totalNumber;
        orderdetail.totalAmount = jsonStr.totalAmount;
        return goodsList;

    },

    updateGoodsNum: function (index, num) {
        var ShoppingCart = localStorage.getItem("ShoppingCart");
        var jsonStr = JSON.parse(ShoppingCart.substr(0, ShoppingCart.length));
        var goodsList = jsonStr.goodsList;

        for (var i in goodsList) {
            if (i == index) {
                jsonStr.totalNumber = parseInt(jsonStr.totalNumber) + (parseInt(num) - parseInt(goodsList[i].num));
                jsonStr.totalAmount = parseFloat(jsonStr.totalAmount) + ((parseInt(num) * parseFloat(goodsList[i].bookPrice)) - parseInt(goodsList[i].num) * parseFloat(goodsList[i].bookPrice));
                goodsList[i].num = parseInt(num);
                orderdetail.totalNumber = jsonStr.totalNumber;
                orderdetail.totalAmount = jsonStr.totalAmount;
                localStorage.setItem("ShoppingCart", JSON.stringify(jsonStr));
                return;
            }
        }
    },

    deleteGoods: function (index) {
        var ShoppingCart = localStorage.getItem("ShoppingCart");
        var jsonStr = JSON.parse(ShoppingCart.substr(0, ShoppingCart.length));
        var goodsList = jsonStr.goodsList;
        var list = [];
        for (var i in goodsList) {
            if (i == index) {
                jsonStr.totalNumber = parseInt(jsonStr.totalNumber) - parseInt(goodsList[i].num);
                jsonStr.totalAmount = parseFloat(jsonStr.totalAmount) - parseInt(goodsList[i].num) * parseFloat(goodsList[i].bookPrice);
            } else {
                list.push(goodsList[i]);
            }
        }
        jsonStr.goodsList = list;
        orderdetail.totalNumber = jsonStr.totalNumber;
        orderdetail.totalAmount = jsonStr.totalAmount;
        localStorage.setItem("ShoppingCart", JSON.stringify(jsonStr));
    },

    clearGoods: function () {
        localStorage.clear();
        orderdetail.totalNumber = 0;
        orderdetail.totalAmount = 0;
    }
};

function addCart(goodsId, bookPic, goodsName, goodsbookAuthor, publisher, goodsbookPrice) {

    var goods = {
        "bookId": goodsId,
        "bookPic": bookPic,
        "bookName": goodsName,
        "bookAuthor": goodsbookAuthor,
        "publisher": publisher,
        "num": 1,
        "bookPrice": goodsbookPrice
    };

    cart.addGoods(goods);
    Materialize.toast('添加成功', 4000);

}

function showCart() {

    var goodsList = cart.getGoodsList();
    var s = "";
    if (goodsList != null && goodsList.length > 0) {
        s += "<table class='highlight'><thead><tr>" + " <th>编号</th><th>缩略图</th><th>图书名称</th><th>图书作者</th><th>出版社</th><th>图书单价</th><th>数量</th><th>小计</th><th>操作</th></tr></thead><tbody>";
        for (var i = 0; i < goodsList.length; i++) {
            var goods = goodsList[i];
            s += "<tr><td>" + goods.bookId + "</td>";
            s += "<td><img src='" + goods.bookPic + "' width='50'></td>";
            s += "<td><a>" + goods.bookName + "</a></td>";
            s += "<td><span>" + goods.bookAuthor + "</span></td>";
            s += "<td><span>" + goods.publisher + "</span></td>";
            s += "<td>价格:" + goods.bookPrice + "元</td>";
            s += "<td><input style='width: 70px;' onblur='changeCart(" + i + ")' id='num" + i + "' type='number' value='" + goods.num + "'/></td>";
            s += "<td>" + goods.num * goods.bookPrice + "</td>";
            s += "<td><a href='#' onclick='delCart(" + i + ")'>删除</a></td></tr>";
        }
        s += "</tbody></table>";
        $(".footer").html("<a id='commit' class='right btn waves-effect waves-light blue' onclick='addOrder()'>去结算</a><div class='right' style='margin: 10px;10px'>已选" + orderdetail.totalNumber + "件商品&nbsp;&nbsp;共" + orderdetail.totalAmount + "元</div>");
    } else {
        s += "<div class='tableTip'>列表为空</div>";
        $(".footer").html("<a id='commit' class='right btn waves-effect waves-light blue' onclick='addOrder()'>去结算</a><div class='right' style='margin: 10px;10px'>已选" + orderdetail.totalNumber + "件商品&nbsp;&nbsp;共" + orderdetail.totalAmount + "元</div>");
        $("#commit").addClass("disabled");
    }

    $(".table").html(s);
    $('#cart').modal('open');

}

function clearCart() {
    $("#commit").addClass("disabled");
    $(".table").html("<div class='tableTip'>列表为空</div>");
    cart.clearGoods();
}

function delCart(index) {
    cart.deleteGoods(index);
    showCart();
}

function changeCart(index) {
    var newNum = $("#" + "num" + index).val();
    cart.updateGoodsNum(index, newNum);
    showCart();
}

function addOrder() {

    $.post(getContextPath() + "/order/addOrder",
        {
            json: localStorage.getItem("ShoppingCart"),
        }, function (result) {
            let arr = result.message;
            if (arr == "提交成功") {
                localStorage.clear();
                showCart();
            }
            Materialize.toast(arr, 4000);

        }
    );
}