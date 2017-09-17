var $ = jQuery;

// Materialize functions
$(function () {
    $(".button-collapse").sideNav({
        closeOnClick: true
    });
    $('.modal').modal();
});

// preload function
$(document).ready(function () {
    setTimeout(function () {
        $('body').addClass('loaded');
    }, 0);

});