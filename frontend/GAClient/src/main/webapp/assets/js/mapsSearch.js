initMap = () => {
    var latitude = parseFloat(window.localStorage.getItem('lat'));
    var longitude = parseFloat(window.localStorage.getItem('lon'));
    var map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: latitude, lng: longitude},
        zoom: 14,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });
    options = {
        types: ['(cities)']
    };

    var marker = new google.maps.Marker({
        map: map

    });

    getData(map, options);
};


getCity = (event) => {
    let key = event.key;
    if (key === "Enter" && ($('#address')[0].value) !== "") {
        var city = window.localStorage.getItem('loc');
        var actualCity = ($('#address')[0].value);

        if (city.match(actualCity)) {
            console.log($('#address')[0].value);
            Materialize.fadeInImage('#guardioes');
        }
    }
};


(() => document.getElementById("address").value = window.localStorage.getItem('loc'))();