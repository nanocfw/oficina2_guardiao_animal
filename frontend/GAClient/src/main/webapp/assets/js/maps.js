function initMap() {
    var map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: -25.7464549, lng: -53.06157339},
        zoom: 14,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });
    var input = document.getElementById('address');

    var autocomplete = new google.maps.places.Autocomplete(input);
    autocomplete.bindTo('bounds', map);
    var infowindow = new google.maps.InfoWindow();
    var marker = new google.maps.Marker({
        map: map,
        anchorPoint: new google.maps.Point(0, -29)
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
        marker.setIcon(({
            url: place.icon,
            size: new google.maps.Size(71, 71),
            origin: new google.maps.Point(0, 0),
            anchor: new google.maps.Point(17, 34),
            scaledSize: new google.maps.Size(35, 35)
        }));
        marker.setPosition(place.geometry.location);
        marker.setVisible(true);
        var address = '';
        if (place.address_components) {
            address = [
                (place.address_components[0] && place.address_components[0].short_name || ''),
                (place.address_components[1] && place.address_components[1].short_name || ''),
                (place.address_components[2] && place.address_components[2].short_name || '')
            ].join(' ');
        }
        infowindow.setContent('<div><strong>' + place.name + '</strong><br/></div>');
        infowindow.open(map, marker);
        //Location details
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

    function carregarPontos() {

        $.getJSON('assets/auxiliar/aa.json', function (pontos) {

            $.each(pontos, function (index, ponto) {

                var marker = new google.maps.Marker({
                    position: new google.maps.LatLng(ponto.Latitude, ponto.Longitude),
                    title: "Meu ponto personalizado! ID: " + ponto.Id.toString(),
                    map: map,
                    icon: 'assets/images/mark1.png'
                });
                var infowindow = new google.maps.InfoWindow(), marker;

                google.maps.event.addListener(marker, 'click', (function (marker) {
                    return function () {
                        infowindow.setContent('<p class="markMaps">' + ponto.Id + '<br/>' + ponto.nome + '<br/>' + ponto.cidade + '</p>');
                        infowindow.open(map, marker);
                    };
                })(marker));
            });
        });
    }
    carregarPontos();
}
