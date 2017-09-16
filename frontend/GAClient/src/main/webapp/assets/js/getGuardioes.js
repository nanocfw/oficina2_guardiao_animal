// get guardioes function
getGuardioes = () => {
    $.getJSON('assets/auxiliar/aa.json', function (pontos) {
        let guardioes = "";
        
        $.each(pontos, function (index, ponto) {
            
            var city = window.localStorage.getItem('loc');
            var cityJson = ponto.cidade;
            
            if (city.match(cityJson)) {
                let guardiao = `<li id="guardiao" class="container" opacity="0">
                <div class="col s12 m8 offset-m2 l6 offset-l3">
                    <div class="card-panel grey lighten-5 z-depth-1">
                        <div class="inline">
                            <div class="row valign-wrapper">
                                <div class="col s2">
                                    <img src="assets/images/img.jpg" alt="" class="circle responsive-img"/>
                                </div>
                                <div class="col s10">
                                    <h5 class="grey-text">${ponto.Id} - ${ponto.nome}</h5>
                                    <span class="black-text">
                                        ${ponto.descricao}
                                    </span>
                                    <br />
                                    <span class="grey-text">
                                        ${ponto.cidade}
                                    </span>
                                </div>
                            </div>
                            <div class="valor row halign-wrapper">
                                <h5 class="orange-text" style="margin-bottom: 0">R$${ponto.valor}</h5>
                                <span class="grey-text">por noite</span>
                            </div>
                        </div>
                    </div>
                </div>
            </li>`;
                guardioes += guardiao;
            }

        });
        for (i = 0; i < guardioes.length; i++) {
            return document.querySelector('#guardioes').innerHTML = guardioes;
        }
    });
}
;

getMarkers = (icon = 'assets/images/mark.png') => {
    
    $.getJSON('assets/auxiliar/aa.json', function (pontos) {

        $.each(pontos, function (index, ponto) {

            var marker = new google.maps.Marker({
                position: new google.maps.LatLng(ponto.Latitude, ponto.Longitude),
                // title: "Meu ponto personalizado! ID: " + ponto.Id.toString(),
                setMap: map,
                draggable: false,
                optimized: false,
                icon: icon
            });

            var infowindow = new google.maps.InfoWindow(), marker;

            google.maps.event.addListener(marker, 'mouseover', function () {
                var city = window.localStorage.getItem('loc');
                var cityJson = ponto.cidade;
                if (city.match(cityJson)) {

                    infowindow.setContent(`<p class="markMaps"> ID: ${ponto.Id} - ${ponto.nome} <br/> R$${ponto.valor}/Diaria <br/> 
                        ${ponto.cidade} </p>`);
                    infowindow.open(map, marker);

                    marker.setIcon("assets/images/mark1.png");
                }
            });
            google.maps.event.addListener(marker, 'mouseout', function () {
                infowindow.close(map, marker);
                marker.setIcon(icon);
            });
        });
    });
};

(() => onchange = () => {
    getGuardioes(); 
    getMarkers();
})();