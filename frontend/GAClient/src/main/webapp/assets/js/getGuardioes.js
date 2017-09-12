// get guardioes function
function getGuardioes() {
    $.getJSON('assets/auxiliar/aa.json', function (pontos) {
        let guardioes = "";
        $.each(pontos, function (index, ponto) {
            let guardiao = `<div class="container">
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
            </div>`;
            console.log(ponto.Id);
            
            guardioes += guardiao;
        });
        for(i = 0; i < guardioes.length; i++) {
            return document.querySelector('#guardioes').innerHTML = guardioes;
        }  
    });
};