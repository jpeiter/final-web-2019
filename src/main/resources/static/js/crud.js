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