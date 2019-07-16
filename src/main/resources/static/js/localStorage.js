"use strict";

$(document).ready(function () {
    let total = getCartQuantity();
    if (total >= 1) {
        $('#nitens').text(total);
    }
});


function getCartQuantity() {
    let total = 0;

    if (localStorage.getItem('cart') !== null) {
        let localCart = JSON.parse(localStorage.getItem('cart'));

        localCart.forEach(x => {
            total += x.quantity;
        });

    }

    return total;
}

function getCartQuantityByProductId(productId) {
    if (localStorage.getItem('cart') !== null) {
        let localCart = JSON.parse(localStorage.getItem('cart'));

        try {
            return localCart.filter(product => product.id == productId)[0].quantity;
        }catch (e) {
            return 0;
        }
    }
    return 0;
}