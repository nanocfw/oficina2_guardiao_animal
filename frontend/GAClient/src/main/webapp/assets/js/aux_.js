var $ = jQuery;

$(function () {
    $(".button-collapse").sideNav({
        closeOnClick: true
    });
    $('.slider').slider({
        full_width: true,
        interval: 3000,
        transition: 1000,
        indicators: false,
        height: 100+'vh'
    });
    $('.modal').modal();
});


function enter(event) {
    let key = event.key;
    if (key === "Enter" && ($('#location')[0].innerHTML) !== "") {
        var $doc = $('html, body');
        $doc.animate({
            scrollTop: $($.attr($('.scroll')[0], 'href')).offset().top
        }, 500);
    }
}

$(document).ready(function () {

    // Fakes the loading setting a timeout
    setTimeout(function () {
        $('body').addClass('loaded');
    }, 2500);

});