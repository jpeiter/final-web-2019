"use strict";

$(document).ready(function () {
    let favoritado = localStorage.getItem('favoritado');
    let itensCarrinho = localStorage.getItem('itensCarrinho');

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

    if (itensCarrinho >= 1) {
        $('#nitens').text(itensCarrinho);
    }
});
