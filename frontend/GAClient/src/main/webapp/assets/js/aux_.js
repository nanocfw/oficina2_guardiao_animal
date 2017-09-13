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


// type enter to scroll on main page function
function enter(event) {
    let key = event.key;
    if (key === "Enter" && ($('#location')[0].innerHTML) !== "") {
        var $doc = $('html, body');
        $doc.animate({
            scrollTop: $($.attr($('.scroll')[0], 'href')).offset().top
        }, 500);
    }
}

// preload function
$(document).ready(function () {
    setTimeout(function () {
        $('body').addClass('loaded');
    }, 2000);

});