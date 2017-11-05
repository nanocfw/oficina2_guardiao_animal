let $ = jQuery;

// Materialize functions
$(function () {
    $(".button-collapse").sideNav({
        edge: 'right',
        closeOnClick: true
    });
    $(".button-collapse-nav").sideNav({
        edge: 'left'
    });
    $('.modal').modal();
    $('.dropdown-button').dropdown({
        width: 300,
        inDuration: 500,
        outDuration: 325,
        hover: true, // Activate on hover
        belowOrigin: false, // Displays dropdown below the button
        alignment: 'right'
    })
});

// preload function
$(document).ready(function () {
    setTimeout(function () {
        $('body').addClass('loaded');
    }, 0);

});