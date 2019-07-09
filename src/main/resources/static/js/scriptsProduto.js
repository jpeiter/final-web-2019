let prodId;
let shippingCountry;
let name;
let price;
let quantity;

$(document).ready(function () {
    let favoritado = localStorage.getItem('favoritado');

    if (favoritado === 'true') {

        $('#fav').removeClass('fa-heart-o');
        $('#fav').removeClass('yellow');
        $('#fav').addClass('fa-heart');
        $('#fav').addClass('red');
    } else {
        $('#fav').removeClass('fa-heart');
        $('#fav').removeClass('red');
        $('#fav').addClass('fa-heart-o');
        $('#fav').addClass('yellow');
    }

    prodId = Number($('h1').attr('prodId'));
    name = $('#prodName').text();
    price = Number($('#price').attr('prodPrice'));

    $.ajax({
        url: '/product/' + prodId + '/images',
        method: 'GET',
        success: function (data, status) {
            console.log("Status: " + status);
            $('#foto-grande').attr('src', 'data:image/png;base64, ' + data);
            result = data;
        },
        error: (e) => alert('errou' + String().valueOf(this.url))
    });


});

$(function () {

    //MARCAR PRODUTO COMO FAVORITO
    $('#fav').click(function () {
        $(this).toggleClass('fa-heart-o fa-heart');
        $(this).toggleClass('yellow red');
        if ($(this).hasClass('red')) {
            localStorage.setItem('favoritado', "true");
        } else {
            localStorage.setItem('favoritado', "false");
        }
    });

    //ADICIONAR PRODUTO NO CARRINHO
    $('#btn-add-carrinho').click(function (e) {
        e.preventDefault();

        quantity = Number($('#qtdeItens').val());

        if (quantity >= 1 && quantity <= 10) {

            let newProd = {
                'id': prodId,
                'price': price,
                'quantity': quantity,
                'imagesrc': $('#foto-grande').attr('src')
            };
            addToCart(newProd);
            let totalItems = getCartQuantity();
            $('#nitens').text(totalItems);
            alert(`${newProd.quantity} iten(s) adicionados ao carrinho!`);
        } else {
            alert('A quantidade deve ser pelo menos 1 e no máximo 10!');
        }

    });

    //SELECIONAR QUANTIDADES
    $('#qtdeItens').on('input', () => {
        if ($('#qtdeItens').val() == 10) {
            $('#aviso-vendas').addClass('d-block');
            $('#aviso-vendas').removeClass('d-none');
        } else {
            $('#aviso-qtde-zero').addClass('d-none');
            $('#aviso-qtde-zero').removeClass('d-block');
            $('#aviso-vendas').addClass('d-none');
            $('#aviso-vendas').removeClass('d-block');
        }
    });


    $('#calc-frete').click(function (e) {
        e.preventDefault();
        let country = $('#country option:selected').text();

        if (country === "Selecione") {
            $('#aviso-frete').addClass('d-block');
            return;
        } else if (country === "Brasil") {
            $('#valor-frete').text('GRÁTIS!!!');
            $('#aviso-frete').addClass('d-none');
            shippingPrice = 0.0;
        } else {
            $('#aviso-frete').removeClass('d-block');
            $('#aviso-frete').addClass('d-none');
            $('#valor-frete').text('R$ 150.00');
            shippingPrice = 150.0;
        }
        shippingCountry = $('#country option:selected').val();
        localStorage.setItem('shippingCountry ', shippingCountry);
    });

})


function addToCart(product) {
    if (localStorage.getItem('cart') === null) {
        localStorage.setItem('cart', JSON.stringify([product]));
    } else {
        let localCart = JSON.parse(localStorage.getItem('cart'));

        let ids = [];
        localCart.forEach(x => {
            if (x.id === product.id) {
                x.quantity += product.quantity;
            }
            ids.push(x.id);
        });

        if(ids.indexOf(product.id) === -1){
            localCart.push(product);
        }


        localStorage.setItem('cart', JSON.stringify(localCart));
    }
}