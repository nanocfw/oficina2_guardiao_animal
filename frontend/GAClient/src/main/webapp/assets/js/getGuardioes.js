getListadeGuardioes= () => {
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

    return $.ajax({
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
            return persons;
        }
    })
}

// get guardioes function
getGuardioes = () => {
    var guardioes = '';
    persons = getListadeGuardioes();

    persons.responseJSON.map( person => {

        if (person.message === null) {
            person.message = "Nenhuma mensagem foi adicionada por este cuidador."
        }

        if (person.picture === "") {
            person.picture = "assets/images/img.jpg"
        }

        for (var i = 0; i < person.serviceList.length; i++) {
            switch (person.serviceList[i].animalSize) {
                case "SMALL":
                    person.serviceList[i].animalSize = "Pequeno";
                    break;
                case "MIDDLE":
                    person.serviceList[i].animalSize = "Médio";
                    break;
                case "LARGE":
                    person.serviceList[i].animalSize = "Grande";
                    break;
                default:
                    person.serviceList[i].animalSize ="Inválido";
            }
        }

        for (var i = 0; i < person.serviceList.length; i++) {
            switch (person.serviceList[i].billingType) {
                case "PER_HOUR":
                    person.serviceList[i].billingType = "Por Hora";
                    break;
                case "PER_DAY":
                    person.serviceList[i].billingType = "Por Dia";
                    break;
                default:
                    person.serviceList[i].billingType = "Inválido";
            }
        }

        for (var i = 0; i < person.serviceList.length; i++) {
            var services = services +  `
              <div>
                  <h4 class="text-orange right-align" style="margin-right: 5px; margin-top: 0">R$ ${person.serviceList[i].value}
                        <h6 class="grey-text right-align" style="margin-right: 5px; margin-top: 0">${person.serviceList[i].billingType}</h6></h4>
                  <h6 class="grey-text right-align" style="margin-right: 5px; margin-top: 0">${person.serviceList[i].serviceTypeDescription} de 
                        ${person.serviceList[i].animalTypeDescription}</h6>
                  <h6 class="grey-text right-align" style="margin-right: 5px; margin-top: 0">Porte ${person.serviceList[i].animalSize}</h6>
                  <div class="divider"></div>
              </div>`
        }

        if (services !== undefined) {
            var guardiao = `
                <div class="card vertical size-guardiao inline-guardiao">
                    <div class="inline-component">
                        <p style="display: block">${person.id}</p>
                        <img class="img-guardiao" src="${person.picture}">
                        <div class="content-guardiao text-grey">
                          <h3>${person.name}</h3>
                          <h4>${person.city}</h4>
                        </div>
                    </div>
                    <div class="inline-message-money ">
                        <p class="message">${person.message}</p>
                        <div class="valor-guardiao"> 
                          <div class="dropdown">
                               <h4 class="text-orange right-align pulsate" style="margin-right: 5px; margin-top: 0">SERVIÇOS</h4>
                              <div class="dropdown-guardiao services">
                                  <p style="display: none;">${services}</p>
                              </div>
                            </div>
                        </div>
                    </div>
                </div>`;
            guardioes = guardioes + guardiao;
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
    var guardioes = '';
    persons = getListadeGuardioes();

    persons.responseJSON.map( person => {

        if (person.message === null) {
            person.message = "Nenhuma mensagem foi adicionada por este cuidador."
        }

        if (person.picture === "") {
            person.picture = "assets/images/img.jpg"
        }

        for (var i = 0; i < person.serviceList.length; i++) {
            switch (person.serviceList[i].animalSize) {
                case "SMALL":
                    person.serviceList[i].animalSize = "Pequeno";
                    break;
                case "MIDDLE":
                    person.serviceList[i].animalSize = "Médio";
                    break;
                case "LARGE":
                    person.serviceList[i].animalSize = "Grande";
                    break;
                default:
                    person.serviceList[i].animalSize ="Inválido";
            }
        }

        for (var i = 0; i < person.serviceList.length; i++) {
            switch (person.serviceList[i].billingType) {
                case "PER_HOUR":
                    person.serviceList[i].billingType = "Por Hora";
                    break;
                case "PER_DAY":
                    person.serviceList[i].billingType = "Por Dia";
                    break;
                default:
                    person.serviceList[i].billingType = "Inválido";
            }
        }

        for (var i = 0; i < person.serviceList.length; i++) {
            var services = services +  `
              <div>
                  <h4 class="text-orange right-align" style="margin-right: 5px; margin-top: 0">R$ ${person.serviceList[i].value}
                        <h6 class="grey-text right-align" style="margin-right: 5px; margin-top: 0">${person.serviceList[i].billingType}</h6></h4>
                  <h6 class="grey-text right-align" style="margin-right: 5px; margin-top: 0">${person.serviceList[i].serviceTypeDescription} de 
                        ${person.serviceList[i].animalTypeDescription}</h6>
                  <h6 class="grey-text right-align" style="margin-right: 5px; margin-top: 0">Porte ${person.serviceList[i].animalSize}</h6>
                  <div class="divider"></div>
              </div>`
        }

        if (services !== undefined) {
            var guardiao = `
            <div class="card vertical size-guardiao inline-guardiao">
                <div class="inline-component">
                    <inputHidden id="id_guardiao" style="display: block" value="${person.id}"></inputHidden>
                    <img class="img-guardiao" src="${person.picture}">
                    <div class="content-guardiao text-grey">
                      <h3>${person.name}</h3>
                      <h4>${person.city}</h4>
                    </div>
                </div>
                <div class="inline-message-money ">
                    <p class="message">${person.message}</p>
                    <div class="valor-guardiao"> 
                      <div class="dropdown">
                           <h4 class="text-orange right-align pulsate" style="margin-right: 5px; margin-top: 0">SERVIÇOS</h4>
                          <div class="dropdown-guardiao services">
                              <p style="display: none;">
                                ${services}
                              </p>
                          </div>
                        </div>
                    </div>
                </div>
                   <p:commandButton value="Contratar"
                         action="#{serviceProviderAnimalTypeBean.loadServicesForServiceProvider(${person.id})}"
                         ajax="true"
                         update="formContratarServico"
                         oncomplete="javascript:location.hash = '#formContratarServico';"
                         class="waves-effect waves-light btn deep-orange lighten-1 button">
                   </p:commandButton>
            </div>`;
            guardioes = guardioes + guardiao;
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
    persons = getListadeGuardioes();

    persons.responseJSON.map( person => {
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

                infowindow.setContent(`<div class="markMaps center-align"><img width="80" height="80" src="${person.picture}" />
                <p class="center-align">${person.name} <br /> ${person.city}</p></div>`);
                infowindow.open(map, marker);

                marker.setIcon("assets/images/mark.png");
            });
            google.maps.event.addListener(marker, 'mouseout', function () {
                infowindow.close(map, marker);
                marker.setIcon(icon);
            });
        }
    });
};