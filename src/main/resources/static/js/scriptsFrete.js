$(document).ready(function () {
    $('.div-tipo-frete').click(function (e) {
        if (!($(this).hasClass('frete-selecionado'))) {
            $('.div-tipo-frete').toggleClass('tipo-frete frete-selecionado');
        }
    });

    preencheCamposFrete();
});

function preencheCamposFrete() {
    if (localStorage.getItem("dadosCadastro") === null ||
        localStorage.getItem("infosProduto") === null
    ) {
        alert("Primeiramente faça seu cadastro e depois faça seu pedido!")
        window.location.href = "formulario.html";
    } else {
        let dadosCadastro = JSON.parse(localStorage.getItem('dadosCadastro'));
        let infosProduto = JSON.parse(localStorage.getItem('infosProduto'));

        //ENDEREÇO
        $("#main-address").text(
            "Rua " + dadosCadastro.rua + " " +
            dadosCadastro.numero + ", " + dadosCadastro.bairro
        );

        $("#detail-address").text(
            dadosCadastro.complemento + ", " +
            dadosCadastro.cidade + " - " +
            dadosCadastro.estado + " - " +
            dadosCadastro.cep
        );

        //VALOR FRETE NORMAL
        $("#valor-normal").text(
            "Valor: R$ " + infosProduto.valorfrete.toFixed(2).replace(".", ",")
        );

        //NOME DO PRODUTO
        $("#nomeProduto").text(infosProduto.nome);

        //QUANTIDADE SELECIONADA
        $("#quantidadeProduto").text("Quantidade: " + infosProduto.qtde);

        //VALOR TOTAL PRODUTOS
        let totalProdutos = (infosProduto.preco * infosProduto.qtde);
        $("#totalProdutos").text("Total de Produtos: R$ " + totalProdutos.toFixed(2).replace(".", ","));

        //VALOR FINAL FRETE
        $("#precoFinalFrete").text("Frete: R$ " +
            infosProduto.valorfrete.toFixed(2).replace(".", ",")
        );

        //TOTAL DA COMPRA
        let valorTotalCompra = parseFloat(totalProdutos) + parseFloat(infosProduto.valorfrete);
        $("#totalCompra").text(
            "Total: R$ " +
            valorTotalCompra.toFixed(2).replace(".", ",")
        );
    }
}