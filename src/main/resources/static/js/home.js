$(document).ready(() => {
    let imgs = $('.prod-img');

    Array(imgs).forEach(img => {
        $.ajax({
            url: '/product/' + $(img).attr('prodId') + '/images',
            method: 'GET',
            success: function (data, status) {
                console.log("Status: " + status);
                $(img).attr('src', 'data:image/png;base64, ' + data);
                result = data;
            },
            error: (e) => {
                console.error(e)
            }
        });
    });

});