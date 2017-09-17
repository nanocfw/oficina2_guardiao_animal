var enter = (event) => {
};
getCity = () => {
    var latitude = parseFloat(window.localStorage.getItem('lat'));
    var longitude = parseFloat(window.localStorage.getItem('lon'));
    var map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: latitude, lng: longitude},
        zoom: 13,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });
    options = {
        types: ['(cities)']
    };

    getData(map, options);
};

getData = (map, options) => {
    input = $('#address');
    autocomplete = new google.maps.places.Autocomplete(input[0], options);

    var postal_code = document.getElementById("postal_code").innerHTML;
    var country = document.getElementById("country").innerHTML;
    var loc = document.getElementById("loc").innerHTML;
    var lat = document.getElementById("lat").innerHTML;
    var lon = document.getElementById("lon").innerHTML;

    autocomplete.addListener('place_changed', function () {

        var place = autocomplete.getPlace();
        if (!place.geometry) {
            return window.alert("Autocomplete's returned place contains no geometry");

        }

        // If the place has a geometry, then present it on a map.
        if (place.geometry.viewport) {
            map.fitBounds(place.geometry.viewport);
        } else {
            map.setCenter(place.geometry.location);
            map.setZoom(13);
        }

        for (var i = 0; i < place.address_components.length; i++) {
            if (place.address_components[i].types[0] === 'postal_code') {
                postal_code = place.address_components[i].long_name;
            }
            if (place.address_components[i].types[0] === 'country') {
                country = place.address_components[i].long_name;
            }
        }
        loc = place.formatted_address;
        lat = place.geometry.location.lat();
        lon = place.geometry.location.lng();

        setCity(postal_code, country, loc, lat, lon);
        
        getGuardioes();
    });

};

setCity = (postal_code, country, loc, lat, lon) => {
    window.localStorage.setItem('postal_code', postal_code);
    window.localStorage.setItem('country', country);
    window.localStorage.setItem('loc', loc);
    window.localStorage.setItem('lat', lat);
    window.localStorage.setItem('lon', lon);

    enter = (event) => {
        let key = event.key;
        if (key === "Enter" && ($('#address')[0].value) !== "") {
            console.log($('#address')[0].value);
            window.location = 'searchGuardiao.xhtml';
        }
    };
};

// type enter to scroll on main page function
