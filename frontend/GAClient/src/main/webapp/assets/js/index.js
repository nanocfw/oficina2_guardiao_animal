var $ = jQuery;

// Materialize functions
$(function () {
    $(".button-collapse").sideNav({
        closeOnClick: true
    });
    $('.slider').slider({
        full_width: true,
        interval: 3000,
        transition: 1000,
        indicators: false,
        height: 100 + 'vh'
    });
    $('.modal').modal();
});

// preload function
$(document).ready(function () {
    setTimeout(function () {
        $('body').addClass('loaded');
    }, 0);

});