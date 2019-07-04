let shippingPrice;
let quantity;

$(document).ready(function () {
    const prodId = $('h1').attr('prodId');
    // const url = `/product/images`;
    debugger;
    let result;


    $.ajax({
        url: '/product/' + prodId + '/images',
        method: 'GET',
        success: function (data, status) {
            console.log("Status: " + status);
            $('#foto-grande').attr('src', 'data:image/png;base64, '+ data);
            result = data;
            debugger;
        },
        error: (e) => alert('errou' + String().valueOf(this.url))

    });
});

$(function () {

//TROCAR FOTO PELA MINIATURA CLICADA
    $('.foto-menor-li').click(function () {
        let imgSrc = $(this).children().attr('src');
        $('#foto-grande').attr('src', imgSrc.replace("-mini", ""));
    });

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
        let qtde = Number($('#qtdeItens').val());

        e.preventDefault();
        if (qtde >= 1) {
            $('#nitens').text(qtde);
            localStorage.setItem('itensCarrinho', qtde);

            let nome = $('#nome-produto').text();
            let preco = Number($('h1').attr('prodPrice'));
            let valorFrete = $('#valor-frete').text();
            let frete;

            if (valorFrete === "" || valorFrete === "GRÁTIS!!!") {
                frete = 0;
            } else {
                frete = parseFloat($('#valor-frete').text().replace("R$ ", "").replace(",", "."));
            }

            let infosProduto = {
                'nome': nome,
                'preco': preco,
                'qtde': qtde,
                'valorfrete': frete
            };
            localStorage.setItem('infosProduto', JSON.stringify(infosProduto));
            alert($('#qtdeItens').val() + " iten(s) adicionados ao carrinho!");
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


    //CALCULAR VALOR DO FRETE BASEADO POR REGIÃO
    $('#calc-frete').click(function (e) {
        e.preventDefault();
        let country = $('#country option:selected').text();

        if (country === "Selecione") {
            $('#aviso-frete').addClass('d-block');
        } else if (country === "Brasil") {
            $('#valor-frete').text('GRÁTIS!!!');
            $('#aviso-frete').addClass('d-none');
        } else {
            $('#aviso-frete').removeClass('d-block');
            $('#aviso-frete').addClass('d-none');
            $('#valor-frete').text('R$ 150.00');
        }
    });

    $("#btn-comprar").click(function (e) {
        e.preventDefault();
        window.location.href = "frete.html";
    });

    $('#ul-classificacao li').click(function (e) {
        $(this).addClass('yellow');
    });


})

function saveUpload(urlDestino) {
    debugger;
    var formData = new FormData($('#frm')[0]);
    $.ajax({
        type: $('#frm').attr('method'),
        url: $('#frm').attr('action'),
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function () {
            swal({
                title: 'Yo ho ho!',
                text: 'Registro salvo com sucesso!',
                type: 'success'
            }, () => {
                window.location.reload();
            });
        },
        error: function () {
            swal('Avast ye!', 'Não foi possível salvar registro!', 'error');
        },
    });
}