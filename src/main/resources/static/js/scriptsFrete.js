let productsArray = [];
let shippingPrice;
let productsPrice = 0;
let overallPrice = 0;
let cartItems = JSON.parse(localStorage.getItem('cart'));
let shippingCountry = JSON.parse(localStorage.getItem('shippingCountry'));

$(document).ready(function () {


    shippingPrice = shippingCountry.code === "BRA" ? 0 : 150;

    $('#shippingPriceh5').append(' R$ ' + shippingPrice.toFixed(2));

    let i = 0;
    cartItems.forEach(item => {
        let totalPrice = item.quantity * item.price;
        productsPrice += totalPrice;
        $('#cart-table > tbody')
            .append(`<tr class="text-center" id="${item.id}">\n` +
                `<th scope="row"><img src="${item.imagesrc}" width="100" /></th>` +
                `<td>${item.name}</td>` +
                `<td><input type="number" value="${item.quantity}" id="${i}_quantity"` +
                `class="prodQuantityInput form-control text-center w-100" min="0"/></td>` +
                `<td>R$ ${item.price}</td>` +
                `<td class="total-item-price" id="${i}_price">R$ ${totalPrice}</td>` +
                `</tr>`
            );
        productsArray.push({product: {id: item.id}, quantity: item.quantity});
        i++;
    });

    $('#cart-table > tbody')
        .append(`<tr>` +
            ` <th colspan="4" class="text-right">Total:</th>` +
            `<td class="justify-content-end" id="productsPrice">R$ ${productsPrice}</td> ` +
            `</tr>`);

    overallPrice = shippingPrice + productsPrice;
    $('#overallPrice').append(' R$ ' + overallPrice.toFixed(2));

    $('.prodQuantityInput').on('input', function (e) {
        let inputId = $(this).attr('id').split('_')[0];

        let quantity = Number($(this).val());
        let price = cartItems[Number(inputId)].price;
        cartItems[Number(inputId)].quantity = quantity;
        cartItems[Number(inputId)].quantity = quantity;
        productsArray[Number(inputId)].quantity = quantity;
        let totalProduct = quantity * price;
        $(`#${inputId}_price`).text('R$ ' + totalProduct);

        let totalPrice = 0;
        cartItems.forEach(x => {
            totalPrice += x.price * x.quantity;
        })
        overallPrice = totalPrice
        $('#productsPrice').text('R$ ' + totalPrice.toFixed(2));
        $('#overallPrice').text('R$ ' + (totalPrice + shippingPrice).toFixed(2));
        localStorage.setItem('cart', JSON.stringify(cartItems));
    });

});

function buy() {
    let toSave = {
        productClientPurchases: [],
        country: {},
        user: {
            id: 1
        }
    };
    toSave.productClientPurchases = productsArray.filter(item => item.quantity > 0);

    if(toSave.productClientPurchases.length === 0 ){
        swal({
            title: "Avast ye!",
            text: 'A quantidade dos produtos deve ser maior que 0!',
            type: 'warning'
        });
        return;
    }

    toSave.country.id = shippingCountry.id;
    $.ajax({
        method: 'POST',
        url: '/buy/save',
        contentType: 'application/json',
        data: JSON.stringify(toSave),
        success: () => {
            swal({
                title: "Yo ho ho!",
                text: 'Compra feita com sucesso!',
                type: 'success'
            }, () => {
                localStorage.removeItem('cart');
                localStorage.removeItem('shippingCountry');
                window.location = 'home';
            });
        },
        error: (e) => {
            alert('errou' + String().valueOf(this.url));
            console.error(e);
        }
    });
}
