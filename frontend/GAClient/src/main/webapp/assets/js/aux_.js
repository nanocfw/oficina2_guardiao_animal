var $ = jQuery;

$(function () {
    $(".button-collapse").sideNav({
        closeOnClick: true
    });
    $('.modal').modal();
});


function enter(event) {
    let key = event.key;
    if (key === "Enter" && ($('#location')[0].innerHTML) !== "" )  {
        var $doc = $('html, body');
        $doc.animate({
            scrollTop: $($.attr($('.scroll')[0], 'href')).offset().top
        }, 500);
    }
}
