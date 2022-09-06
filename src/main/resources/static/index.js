//Registrere en bil
function regMotorvogn() {
    const motorvogn = {
        personnr : $("#personnr").val(),
        navn : $("#navn").val(),
        adresse : $("#adresse").val(),
        kjennetegn : $("#kjennetegn").val(),
        merke : $("#valgtMerke").val(),
        type : $("#valgtType").val()
    };
    $.post("/lagre", motorvogn, function(){
        hentAlle();
    });
}


//Hente og formatere biler for utskrift
function hentAlle() {
    let verdi = $("#ddlSortering").val();
    console.log(verdi);
    /*$.ajax({
        type: "GET",
        url: "/hentAlle",
        data: verdi,
        success: function (biler) {
            formaterData(biler);
        },error: function (biler){
            formaterData(biler);
        }
    });*/

    $.get("/hentAlle/?verdi="+verdi, function(biler){
        formaterData(biler)
    });
}

function formaterData(biler) {
    let ut = "<table class='table table-striped'><tr><th>Personnr</th><th>Navn</th><th>Adresse</th>" +
        "<th>Kjennetegn</th><th>Merke</th><th>Type</th><th>Slett</th></tr>";
    if (biler.length > 0){
        for (const bil of biler) {
            ut += "<tr><td>" + bil.personnr + "</td><td>" + bil.navn + "</td><td>" + bil.adresse + "</td>" +
                "<td>" + bil.kjennetegn + "</td><td>" + bil.merke + "</td><td>" + bil.type + "</td><td><button value='"+bil.id+"' onclick='endreBil(this.value)' class='btn btn-primary'>Endre</button></td><td><button value='"+bil.kjennetegn+"' onclick='slettBil(this.value)' class='btn btn-danger'>Slett</button></td></tr>";
        }
    }
    ut += "</table>";
    $("#bilene").html(ut);
}


//Sletting av biler
function slettAlle() {
    $.get( "/slettAlle", function() {
        hentAlle();
    });
}

function slettBil(kjennetegn) {
    $.get("/slettBil/?kjennetegn="+kjennetegn, function() {
        hentAlle();
    });
}


//Endring av biler
function endreBil(id){
    window.location.href = "endreBil.html?id="+id;
}

function lagreEndring(){
    let id = window.location.href.split('=')[1];
    const motorvogn = {
        personnr : $("#personnr").val(),
        navn : $("#navn").val(),
        adresse : $("#adresse").val(),
        kjennetegn : $("#kjennetegn").val(),
        merke : $("#valgtMerke").val(),
        type : $("#valgtType").val(),
        id : id
    };
    $.post("/endre", motorvogn, function(){
        hentAlle();
    });
}









//Link til alle sider
function registrering(){
    window.location.href = "registrere.html";
}
function registreringKunde(){
    window.location.href = "registrereKunde.html";
}
function oversikt(){
    window.location.href = "index.html";
    hentAlle();
}
function oversiktKunde(){
    window.location.href = "kundeOversikt.html";
}
function reflogin(){
    window.location.href = "login.html";
}
function forside(){
    window.location.href = "forside.html";
}