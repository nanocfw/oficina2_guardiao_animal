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
            url: "http://localhost:8090/ga/person/fetchserviceprovider/" + country + "/" + cityFormated + "/20/0",
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
                persons.map( person =>
                {
                    var guardiao = `<li id="guardiao" class="container" opacity="0">
                <div class="col s12 m8 offset-m2 l6 offset-l3">
                    <div class="card-panel grey lighten-5 z-depth-1">
                        <div class="inline">
                            <div class="row valign-wrapper">
                                <div class="col s2">
                                    <img src="assets/images/img.jpg" alt="" class="circle responsive-img"/>
                                </div>
                                <div class="col s10">
                                    <h5 class="grey-text">${person.name}</h5>
                                    <br />
                                    <span class="grey-text">
                                        ${person.city}
                                    </span>
                                </div>
                            </div>
                            <div class="valor row halign-wrapper">
                                <h5 class="orange-text" style="margin-bottom: 0">R$ </h5>
                                <span class="grey-text">por noite</span>
                            </div>
                        </div>
                    </div>
                </div>
            </li>`;
                    guardioes = guardioes + guardiao;
                })
            }
        });
        if (guardioes === '') {
            return document.querySelector('#guardioes').innerHTML = `<h4 class="text-grey center-align">Desculpe, nenhum <span class="text-orange">Guardião</span> foi encontrado nesta regiao.</h4>`;
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
        url: "http://localhost:8090/ga/person/fetchserviceprovider/" + country + "/"+ cityFormated + "/20/0",
        type: "GET",
        contentType: 'application/json; charset=utf-8',
        async: false,
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Basic " + btoa("gaadminserver:@g4re5tp4ss#"))
        },
        error : function(jqXHR, textStatus, errorThrown) {
            alert(textStatus)
        },
        success: function(persons) {
            persons.map( person =>
            {
                var guardiao = `<li id="guardiao" class="container" opacity="0">
                <div class="col s12 m8 offset-m2 l6 offset-l3">
                    <div class="card-panel grey lighten-5 z-depth-1">
                        <div class="inline">
                            <div class="row valign-wrapper">
                                <div class="col s2">
                                    <img src="assets/images/img.jpg" alt="" class="circle responsive-img"/>
                                </div>
                                <div class="col s10">
                                    <h5 class="grey-text">${person.name}</h5>
                                    <br />
                                    <span class="grey-text">
                                        ${person.city}
                                    </span>
                                </div>
                            </div>
                            <div class="valor row halign-wrapper">
                                <h5 class="orange-text center-align" style="margin-bottom: 0">R$${person.valor}</h5>
                                <span class="grey-text" style="display: block; text-align: center">por noite</span>
                                <a class="waves-effect waves-light btn deep-orange lighten-1" href="#contratarServico">Contratar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </li>`;
                guardioes = guardioes + guardiao;
            });
        }
    });
    if (guardioes === '') {
            return document.querySelector('#guardioes').innerHTML = `<h4 class="text-grey center-align">Desculpe, nenhum <span class="text-orange">Guardião</span> foi encontrado nesta regiao.</h4>`;
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
            alert(textStatus)
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