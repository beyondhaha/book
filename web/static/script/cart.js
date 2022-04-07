window.onload = function () {
    var vue = new Vue({
        el: "#cart_div",
        data: {
            cart: {}
        },
        methods: {
            getCart: function () {
                axios({
                    method: "POST",
                    url: "cart.do",
                    params: {
                        operate: 'cartInfo'
                    }
                })
                    .then(function (value) {
                        var cart = value.data;
                        vue.cart = cart;
                    })
                    .catch(function (reason) {
                    })
            },
            editCartItem: function (id, buyCount) {
                if (buyCount < 0) return;
                axios({
                    method: "POST",
                    url: "cart.do",
                    params: {
                        operate: "editCart",
                        cartItemId: id,
                        buyCount: buyCount
                    }
                })
                    .then(function (value) {
                        vue.getCart();
                    })
                    .catch(function (reason) {
                    })
            }
        },
        mounted: function () {
            this.getCart();
        }
    });
}