function clearFormUser() {
    $('#id').val('');
    $('#name').val('');
    $('#username').val('');
    $('#password').val('');
    $('#frm input:checkbox:checked').each(function () {
        $(this).prop('checked', false);
    })
}

function editUser(url) {
    clearFormUser();
    $.get(url, function (entity, status) {
        $('#id').val(entity.id);
        $('#name').val(entity.name);
        $('#username').val(entity.username);
        $.each(entity.roles, function (i, item) {
            $('#chk_' + entity.roles[i].id).prop('checked', true);
        });
    });

    $('#modal-form').modal();

}