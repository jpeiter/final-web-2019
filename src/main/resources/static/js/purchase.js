let purchase = {
    "supplier": {},
    "user": {},
    "date": String(),
    "productsPurchase": new Array,
    "totalPrice": 0.0
};

$('#totalPrice').mask('R$ 000.000.000.000.000,00', {reverse: true});

$('#quantity').on('input', function (e) {
    let productPrice = Number($('#product option:selected').attr('productPrice'));
    let quantity = Number($('#quantity').val());

    if (quantity < 0) {
        $('#quantity').val(0);
    } else if (quantity > 100) {
        $('#quantity').val(100);
    }

    let totalPrice = getProductTotalPrice(productPrice, quantity);

    $('#totalProductPrice').val(isNaN(totalPrice) ? 'R$ 0.00' : 'R$ ' + totalPrice);
});

function getProductTotalPrice(price, quantity) {
    return Number((price * quantity).toFixed(2));
}

function savePurchase() {
    if ($('#date').val() === "") {
        swal('Avast ye!',
            'Informe a data de compra!',
            'error');
        return;
    } else {
        let day = $('#date').val().split('/')[0];
        let month = $('#date').val().split('/')[1];
        let year = $('#date').val().split('/')[2];
        purchase.date = year + '-' + month + '-' + day;
    }

    if ($('#supplier').val() === "") {
        swal('Avast ye!',
            'Informe o fornecedor!',
            'error');
        return;
    } else {
        purchase.supplier.id = $('#supplier').val();
    }

    if ($('#user').val() === "") {
        swal('Avast ye!',
            'Informe o usuário!',
            'error');
        return;
    } else {
        purchase.user.id = $('#user').val();
    }

    if (purchase.productsPurchase.length === 0) {
        swal('Avast ye!',
            'Adicione pelo menos um produto!',
            'error');
        return;
    }

    $.ajax({
        url: '/purchase/json',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(purchase),
        success: function () {
            swal({
                title: 'Salvo!',
                text: 'Compra feita com sucesso!',
                type: 'success'
            }, () => {
                window.location = 'page';

            });
        },
        error: function () {
            swal('Erro!',
                'Não foi possível salvar a compra!',
                'error');
        }
    });//Fim ajax
};

function addProductPurchase() {
    let product = {
        product: {
            id: Number
        },
        quantity: Number
    };

    if ($('#product').val() === "") {
        swal('Avast ye!',
            'Selecione um produto!',
            'error');
        return;
    } else {
        product.product.id = $('#product').val();
    }

    if ($('#quantity').val() === "" || $('#quantity').val() === "0") {
        swal('Avast ye!',
            'Informe a quantidade!',
            'error');
        return;
    } else {
        product.quantity = $('#quantity').val();
    }

    purchase.productsPurchase.push(product);
    purchase.totalPrice += getProductTotalPrice(
        Number($('#product option:selected').attr('productPrice')),
        product.quantity
    );
    $('#totalPrice').val('R$ '+ purchase.totalPrice.toFixed(2));

    swal('Yo ho ho!',
        'Produto adicionado à compra!',
        'success');

    $('#product').val("");
    $('#quantity').val("");
    $('#totalProductPrice').val("");

    $('#modal-product').modal('hide');


}