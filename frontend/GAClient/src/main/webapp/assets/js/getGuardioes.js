// get guardioes function
getGuardioes = () => {
    var country = window.localStorage.getItem('country');
    var city = window.localStorage.getItem('loc').split(',')[0]
    var cityFormated;
    var guardioes = 0;

    switch (city) {
        case (city.split(' ')[1] !== undefined):
            cityFormated = city.split(' ')[0]+'%20'+city.split(' ')[1];
            break;
        case (city.split(' ')[2] !== undefined):
            cityFormated = city.split(' ')[0]+'%20'+city.split(' ')[1]+'%20'+city.split(' ')[2];
            break;
        case (city.split(' ')[3] !== undefined):
            cityFormated = city.split(' ')[0]+'%20'+city.split(' ')[1]+'%20'+city.split(' ')[2]+'%20'+city.split(' ')[3];
            break;
        default:
            cityFormated = city;
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
            sucess: function(person) {
                console.log('data: ', data)
                let guardiao = `<li id="guardiao" class="container" opacity="0">
                <div class="col s12 m8 offset-m2 l6 offset-l3">
                    <div class="card-panel grey lighten-5 z-depth-1">
                        <div class="inline">
                            <div class="row valign-wrapper">
                                <div class="col s2">
                                    <img src="assets/images/img.jpg" alt="" class="circle responsive-img"/>
                                </div>
                                <div class="col s10">
                                    <h5 class="grey-text">${person.nome}</h5>
                                    <br />
                                    <span class="grey-text">
                                        ${person.cidade}
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
                guardioes = guardioes + 1;
            }
        });
        if (guardioes === 0) {
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
    var city = window.localStorage.getItem('loc').split(',')[0]
    var cityFormated;
    var guardioes = 0;

    switch (city) {
        case (city.split(' ')[1] !== undefined):
            cityFormated = city.split(' ')[0]+'%20'+city.split(' ')[1];
            break;
        case (city.split(' ')[2] !== undefined):
            cityFormated = city.split(' ')[0]+'%20'+city.split(' ')[1]+'%20'+city.split(' ')[2];
            break;
        case (city.split(' ')[3] !== undefined):
            cityFormated = city.split(' ')[0]+'%20'+city.split(' ')[1]+'%20'+city.split(' ')[2]+'%20'+city.split(' ')[3];
            break;
        default:
            cityFormated = city;
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
        sucess: function(person) {
            console.log('data: ', data)
            let guardiao = `<li id="guardiao" class="container" opacity="0">
                <div class="col s12 m8 offset-m2 l6 offset-l3">
                    <div class="card-panel grey lighten-5 z-depth-1">
                        <div class="inline">
                            <div class="row valign-wrapper">
                                <div class="col s2">
                                    <img src="assets/images/img.jpg" alt="" class="circle responsive-img"/>
                                </div>
                                <div class="col s10">
                                    <h5 class="grey-text">${person.nome}</h5>
                                    <br />
                                    <span class="grey-text">
                                        ${person.cidade}
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
            guardioes = guardioes + 1;
        }
    });
        if (guardioes === 0) {
            return document.querySelector('#guardioes').innerHTML = `<h4 class="text-grey center-align">Desculpe, nenhum <span class="text-orange">Guardião</span> foi encontrado nesta regiao.</h4>`;
        } else {
            for (i = 0; i < guardioes.length; i++) {
                return document.querySelector('#guardioes').innerHTML = guardioes;
            }
        }
};

getMarkers = (map, icon = 'assets/images/mark.png') => {
    var country = window.localStorage.getItem('country');
    var city = window.localStorage.getItem('loc').split(',')[0]
    var cityFormated;
    var guardioes = ""

    switch (city) {
        case (city.split(' ')[1] !== undefined):
            cityFormated = city.split(' ')[0] + '%20' + city.split(' ')[1];
            break;
        case (city.split(' ')[2] !== undefined):
            cityFormated = city.split(' ')[0] + '%20' + city.split(' ')[1] + '%20' + city.split(' ')[2];
            break;
        case (city.split(' ')[3] !== undefined):
            cityFormated = city.split(' ')[0] + '%20' + city.split(' ')[1] + '%20' + city.split(' ')[2] + '%20' + city.split(' ')[3];
            break;
        default:
            cityFormated = city;
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
        sucess: function (index, person) {

            var marker = new google.maps.Marker({
                position: new google.maps.LatLng(person.Latitude, person.Longitude),
                // title: "Meu ponto personalizado! ID: " + ponto.Id.toString(),
                map: map,
                draggable: false,
                optimized: false,
                icon: icon
            });

            var infowindow = new google.maps.InfoWindow(), marker;

            google.maps.event.addListener(marker, 'mouseover', function () {

                infowindow.setContent(`<p class="markMaps"> ${person.nome} <br/> R$ Diaria <br/> 
                            ${person.cidade} </p>`);
                infowindow.open(map, marker);

                marker.setIcon(icon);
            });
            google.maps.event.addListener(marker, 'mouseout', function () {
                infowindow.close(map, marker);
                marker.setIcon(icon);
            });
            }
        });
};