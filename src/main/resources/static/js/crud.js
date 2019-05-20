"use strict";

function remove(id, url){
    swal({
            title: "Confirma a remoção do registro?",
            text: "Esta ação não poderá ser desfeita!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            cancelButtonText: "Cancelar",
            confirmButtonText: "Remover",
            closeOnConfirm: false
        }, function(){
            var urlDestino = url + '/' + id;
            $.ajax({
                type: 'DELETE',
                url: urlDestino,
                success: function(){
                    $('#row_' + id).remove();
                    swal({
                        title: 'Salvo!',
                        text: 'Registro removido com sucesso!',
                        type: 'success'
                    }, () => {
                        window.location = url + '/page';
                    });
                },
                error: function(){
                    swal('Erro!',
                        'Não foi possível remover o registro!',
                        'error');
                }
            });//Fim ajax
        }
    );//FIM SWAL
}

function save(urlDestino) {
    $.ajax({
        type: $('#frm').attr('method'),
        url: $('#frm').attr('action'),
        data: $('#frm').serialize(),
        success: function () {
            swal({
                title: 'Salvo!',
                text: 'Registro salvo com sucesso!',
                type: 'success'
            }, () => {
                window.location = urlDestino;
            });
        },
        error: function () {
            swal('Errou!', 'Falha ao salvar registro!', 'error');
        },
    });
}

function edit(url) {
    $.get(url, function (entity, status) {
        $('#id').val(entity.id);
        $('#name').val(entity.name);
    });
    $('#modal-form').modal();
}

function editBrand(url) {
    $.get(url, function (entity, status) {
        $('#id').val(entity.id);
        $('#name').val(entity.name);
        $('#country').val(entity.country.id);
    });
    $('#modal-form').modal();
}

function editSupplier(url) {
    $.get(url, function (entity, status) {
        $('#id').val(entity.id);
        $('#name').val(entity.name);
        $('#code').val(entity.code);
        $('#country').val(entity.country.id);
        $('#phone').val(entity.phone);
        $('#email').val(entity.email);
    });
    $('#modal-form').modal();
}

function editProduct(url) {
    $.get(url, function (entity, status) {
        $('#id').val(entity.id);
        $('#name').val(entity.name);
        $('#description').val(entity.description);
        $('#price').val(entity.price);
        $('#category').val(entity.category.id);
        $('#brand').val(entity.brand.id);
        $('#rating').val(entity.rating);
    });
    $('#modal-form').modal();
}

function viewPurchase(url) {
    $.get(url, function (entity, status) {
        $('#id').val(entity.id);
        $('#name').val(entity.name);
        $('#code').val(entity.code);
        $('#country').val(entity.country.id);
        $('#phone').val(entity.phone);
        $('#email').val(entity.email);
    });
    $('#modal-form').modal();
}