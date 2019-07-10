"use strict";

$(document).ready(function () {
    let total = getCartQuantity();
    if (total >= 1) {
        $('#nitens').text(total);
    }
});


function getCartQuantity(){

    if (localStorage.getItem('cart') !== null) {
        let localCart = JSON.parse(localStorage.getItem('cart'));

        let total = 0;
        localCart.forEach(x => {
            total += x.quantity;
        });

        return total;
    }
}