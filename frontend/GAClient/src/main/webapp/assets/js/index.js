var $ = jQuery;

// Materialize functions
$(function () {
    $(".button-collapse").sideNav({
        closeOnClick: true
    });
    $('.modal').modal();
    $('.dropdown-button').dropdown({
        inDuration: 500,
        outDuration: 325,
        hover: true, // Activate on hover
        belowOrigin: false, // Displays dropdown below the button
        alignment: 'right' // Displays dropdown with edge aligned to the left of button,
    }
    );
});

// preload function
$(document).ready(function () {
    setTimeout(function () {
        $('body').addClass('loaded');
    }, 0);

});