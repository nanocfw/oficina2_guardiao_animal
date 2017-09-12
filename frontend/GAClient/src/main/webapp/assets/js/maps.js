var map;
var bounceTimer;

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: -25.7464549, lng: -53.06157339},
        zoom: 14,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });
    var input = document.getElementById('address');

    var autocomplete = new google.maps.places.Autocomplete(input);
    autocomplete.bindTo('bounds', map);
    var infowindow = new google.maps.InfoWindow();
    var marker = new google.maps.Marker({
        map: map
    });
    autocomplete.addListener('place_changed', function () {
        infowindow.close();
        marker.setVisible(false);
        var place = autocomplete.getPlace();
        if (!place.geometry) {
            window.alert("Autocomplete's returned place contains no geometry");
            return;
        }

        // If the place has a geometry, then present it on a map.
        if (place.geometry.viewport) {
            map.fitBounds(place.geometry.viewport);
        } else {
            map.setCenter(place.geometry.location);
            map.setZoom(17);
        }

        for (var i = 0; i < place.address_components.length; i++) {
            if (place.address_components[i].types[0] === 'postal_code') {
                document.getElementById('postal_code').innerHTML = place.address_components[i].long_name;
            }
            if (place.address_components[i].types[0] === 'country') {
                document.getElementById('country').innerHTML = place.address_components[i].long_name;
            }
        }
        document.getElementById('location').innerHTML = place.formatted_address;
        document.getElementById('lat').innerHTML = place.geometry.location.lat();
        document.getElementById('lon').innerHTML = place.geometry.location.lng();

    });

    function carregarPontos(icon = 'assets/images/mark.png') {

        $.getJSON('assets/auxiliar/aa.json', function (pontos) {

            $.each(pontos, function (index, ponto) {

                var marker = new google.maps.Marker({
                    position: new google.maps.LatLng(ponto.Latitude, ponto.Longitude),
                    // title: "Meu ponto personalizado! ID: " + ponto.Id.toString(),
                    map: map,
                    draggable: false,
                    optimized: false,
                    icon: icon
                });

                var infowindow = new google.maps.InfoWindow(), marker;

                google.maps.event.addListener(marker, 'mouseover', function () {

                    infowindow.setContent(`<p class="markMaps"> ID: ${ponto.Id} - ${ponto.nome} <br/> R$${ponto.valor}/Diaria <br/> 
                        ${ponto.cidade} </p>`);
                    infowindow.open(map, marker);

                    marker.setIcon("assets/images/mark1.png")

                });
                google.maps.event.addListener(marker, 'mouseout', function () {
                    infowindow.close(map, marker);
                    marker.setIcon(icon);
                });
            });
        });
    }
    carregarPontos();
}