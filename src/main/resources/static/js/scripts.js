"use strict";

$(function () {
    $('#frm').click(function () {
        clearForm(e);
    });
});

function clearForm(e){
    e.preventDefault();
    $('#frm').each (function(){
        this.reset();
    });
}