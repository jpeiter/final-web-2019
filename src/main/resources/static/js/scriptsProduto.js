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
            let preco = Number($('#preco').text().replace(/[^0-9.-]+/g, ""));
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
    $('#qtdeItens').click(function (e) {
        e.preventDefault();

        if ($('#qtdeItens').val() == "") {
            $('#aviso-qtde-zero').addClass('d-block');
            $('#aviso-qtde-zero').removeClass('d-none');
        } else if ($('#qtdeItens').val() == 10) {
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
        let estado = $('#combo-estados option:selected');

        if (estado.hasClass('norte')) {
            $('#aviso-frete').removeClass('d-block')
            $('#aviso-frete').addClass('d-none')
            $('#valor-frete').text('R$ 95,99');
        } else if (estado.hasClass('nordeste')) {
            $('#aviso-frete').removeClass('d-block')
            $('#aviso-frete').addClass('d-none')
            $('#valor-frete').text('R$ 75,99');
        } else if (estado.hasClass('centro-oeste')) {
            $('#aviso-frete').removeClass('d-block')
            $('#aviso-frete').addClass('d-none')
            $('#valor-frete').text('R$ 55,95');
        } else if (estado.hasClass('sudeste')) {
            $('#aviso-frete').removeClass('d-block')
            $('#aviso-frete').addClass('d-none')
            $('#valor-frete').text('R$ 35,99');
        } else if (estado.hasClass('sul')) {
            $('#aviso-frete').addClass('d-none')
            $('#valor-frete').text('GRÁTIS!!!');
        } else {
            if ($('#aviso-frete').hasClass('d-none')) {
                $('#aviso-frete').removeClass('d-block')
                $('#aviso-frete').removeClass('d-none')
            }
        }
    });

    $("#btn-comprar").click(function (e) {
        e.preventDefault();
        window.location.href = "frete.html";
    });

    //ENVIAR COMENTARIO
    $('#sendReview').click(function (e) {
        e.preventDefault();
        let titulo = $('#tituloAv').val();
        let comentario = $('#comentariosAv').val();
        let apelido = $('#apelidoAv').val();
        let local = $('#localAv').val();
        let geral = $("#recomendacao input:checked").val();
        if (titulo !== "" || comentario !== "" || apelido !== ""
            || local !== "" || geral === undefined) {
            $('#apelidoCom').text(apelido);
            $('#localCom').text(local);
            $('#tituloCom').text(titulo);
            $('#comentario').text(comentario);
            $('#geralCom').text(geral);

            $('#tituloAv').val("");
            $('#comentariosAv').val("");
            $('#apelidoAv').val("");
            $('#localAv').val("");
            $('#geralAv').val("");

            $('#classificao-produto').addClass('d-block');
            $('#modalFormAvaliacao').modal('hide');
        } else {
            alert("Preencha os campos obrigatórios!");
        }

    });

    $('#ul-classificacao li').click(function (e) {
        $(this).addClass('yellow');
    });


})