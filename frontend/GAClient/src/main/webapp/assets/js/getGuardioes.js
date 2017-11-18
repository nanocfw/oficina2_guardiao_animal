// get guardioes function
getGuardioes = () => {
    var country = window.localStorage.getItem('country');
    var cityStorage = window.localStorage.getItem('loc').split(',')[0]
    var cityFormated;
    var guardioes = '';

    switch (cityStorage) {
        case (cityStorage.split(' ')[1] !== undefined):
            cityFormated = cityStorage.split(' ')[0]+'%20'+cityStorage.split(' ')[1];
            break;
        case (cityStorage.split(' ')[2] !== undefined):
            cityFormated = cityStorage.split(' ')[0]+'%20'+cityStorage.split(' ')[1]+'%20'+cityStorage.split(' ')[2];
            break;
        case (cityStorage.split(' ')[3] !== undefined):
            cityFormated = cityStorage.split(' ')[0]+'%20'+cityStorage.split(' ')[1]+'%20'+cityStorage.split(' ')[2]+'%20'+cityStorage.split(' ')[3];
            break;
        default:
            cityFormated = cityStorage;
            break;
    }

    $.ajax({
        url: "http://localhost:8090/ga/person/fetchserviceprovider/0/" + country + "/" + cityFormated + "/20/0",
        type: "GET",
        contentType: 'application/json; charset=utf-8',
        async: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", "Basic " + btoa("gaadminserver:@g4re5tp4ss#"))
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(textStatus)
        },
        success: function (persons) {
            console.log(persons);
            persons.map( person =>
            {
                var guardiao = `
                               <div class="card vertical size-guardiao inline-guardiao">
                            <div class="inline-component">
                                <img class="img-guardiao" src="assets/images/img.jpg">
                                <div class="content-guardiao text-grey">
                                  <h3>${person.name}</h3>
                                  <h4>${person.city}</h4>
                                </div>
                            </div>
                            <div class="valor-guardiao">
                              <h4 class="orange-text right-align" style="margin-right: 5px; margin-top: 0">R$ 40</h4>
                              <h6 class="grey-text right-align" style="margin-right: 5px: margin-top: 0">por noite</h6>
                            </div>
                        </div>`;
                guardioes = guardioes + guardiao;
            })
        }
    });
    if (guardioes === '') {
        return document.querySelector('#guardioes').innerHTML = `<h4 class="text-grey center-align" style="padding: 120px 0;">Desculpe, nenhum <span class="text-orange">Guardião</span> foi encontrado nesta regiao.</h4>`;
    } else {
        for (i = 0; i < guardioes.length; i++) {
            return document.querySelector('#guardioes').innerHTML = guardioes;
        }
    }
};

// get guardioes function
getGuardioesAuth = () => {
    var country = window.localStorage.getItem('country');
    var cityStorage = window.localStorage.getItem('loc').split(',')[0]
    var cityFormated;
    var guardioes = '';

    switch (cityStorage) {
        case (cityStorage.split(' ')[1] !== undefined):
            cityFormated = cityStorage.split(' ')[0]+'%20'+cityStorage.split(' ')[1];
            break;
        case (cityStorage.split(' ')[2] !== undefined):
            cityFormated = cityStorage.split(' ')[0]+'%20'+cityStorage.split(' ')[1]+'%20'+cityStorage.split(' ')[2];
            break;
        case (cityStorage.split(' ')[3] !== undefined):
            cityFormated = cityStorage.split(' ')[0]+'%20'+cityStorage.split(' ')[1]+'%20'+cityStorage.split(' ')[2]+'%20'+cityStorage.split(' ')[3];
            break;
        default:
            cityFormated = cityStorage;
            break;
    }

    $.ajax({
        url: "http://localhost:8090/ga/person/fetchserviceprovider/0/" + country + "/"+ cityFormated + "/20/0",
        type: "GET",
        contentType: 'application/json; charset=utf-8',
        async: false,
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Basic " + btoa("gaadminserver:@g4re5tp4ss#"))
        },
        error : function(jqXHR, textStatus, errorThrown) {
            console.log(textStatus)
        },
        success: function(persons) {
            console.log(persons)
            persons.map( person =>
            {
                var guardiao = `
                        <div class="card vertical size-guardiao inline-guardiao">
                            <div class="inline-component">
                                <img class="img-guardiao" src="assets/images/img.jpg">
                                <div class="content-guardiao text-grey">
                                  <h3>${person.name}</h3>
                                  <h4>${person.city}</h4>
                                </div>
                            </div>
                            <div class="valor-guardiao">
                              <h4 class="orange-text right-align" style="margin-right: 5px; margin-top: 0">R$ 40</h4>
                              <h6 class="grey-text right-align" style="margin-right: 5px: margin-top: 0">por noite</h6>
                            </div>
                            <div class="right-align row padding-top-bottom" style="margin-right: 5px">
                              <a class="waves-effect waves-light btn deep-orange lighten-1" href="#contratarServico">Contratar</a>
                            </div>
                        </div>`;
                guardioes = guardioes + guardiao;
            });
        }
    });
    if (guardioes === '') {
        return document.querySelector('#guardioes').innerHTML = `<h4 class="text-grey center-align" style="padding: 120px 0">Desculpe, nenhum <span class="text-orange">Guardião</span> foi encontrado nesta regiao.</h4>`;
    } else {
        for (i = 0; i < guardioes.length; i++) {
            return document.querySelector('#guardioes').innerHTML = guardioes;
        }
    }
};

getMarkers = (map, icon = 'assets/images/mark.png') => {
    var country = window.localStorage.getItem('country');
    var cityStorage = window.localStorage.getItem('loc').split(',')[0]
    var cityFormated;

    switch (cityStorage) {
        case (cityStorage.split(' ')[1] !== undefined):
            cityFormated = cityStorage.split(' ')[0]+'%20'+cityStorage.split(' ')[1];
            break;
        case (cityStorage.split(' ')[2] !== undefined):
            cityFormated = cityStorage.split(' ')[0]+'%20'+cityStorage.split(' ')[1]+'%20'+cityStorage.split(' ')[2];
            break;
        case (cityStorage.split(' ')[3] !== undefined):
            cityFormated = cityStorage.split(' ')[0]+'%20'+cityStorage.split(' ')[1]+'%20'+cityStorage.split(' ')[2]+'%20'+cityStorage.split(' ')[3];
            break;
        default:
            cityFormated = cityStorage;
            break;
    }


    $.ajax({
        url: "http://localhost:8090/ga/person/fetchserviceprovider/" + country + "/" + cityFormated + "/20/0",
        type: "GET",
        contentType: 'application/json; charset=utf-8',
        async: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", "Basic " + btoa("gaadminserver:@g4re5tp4ss#"))
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus)
        },
        success: function (persons) {
            persons.map( person =>
            {
                var marker = new google.maps.Marker({
                    position: new google.maps.LatLng(person.latitude, person.longitude),
                    // title: "Meu ponto personalizado! ID: " + ponto.Id.toString(),
                    map: map,
                    draggable: false,
                    optimized: false,
                    icon: icon
                });

                var infowindow = new google.maps.InfoWindow(), marker;

                google.maps.event.addListener(marker, 'mouseover', function () {

                    infowindow.setContent(`<p class="markMaps"> ${person.name} <br/> R$ Diaria <br/> 
                            ${person.city} </p>`);
                    infowindow.open(map, marker);

                    marker.setIcon("assets/images/mark.png");
                });
                google.maps.event.addListener(marker, 'mouseout', function () {
                    infowindow.close(map, marker);
                    marker.setIcon(icon);
                });
            });
        }
    });
};1