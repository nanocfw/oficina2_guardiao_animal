(() => document.getElementById("address").value = window.localStorage.getItem('loc'))();

initMap = () => {
    let latitude = parseFloat(window.localStorage.getItem('lat'));
    let longitude = parseFloat(window.localStorage.getItem('lon'));
    let map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: latitude, lng: longitude},
        zoom: 13,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });
    options = {
        types: ['(cities)']
    };
    getData(map, options);
    getMarkers(map);
};

getCity = (event) => {
    let key = event.key;
    if (key === "Enter" && ($('#address')[0].value) !== "") {
        let city = window.localStorage.getItem('loc');
        let actualCity = ($('#address')[0].value);

        if (city.match(actualCity)) {
            console.log($('#address')[0].value);
            getGuardioes();
            Materialize.fadeInImage('#guardioes');
        }
    }
};