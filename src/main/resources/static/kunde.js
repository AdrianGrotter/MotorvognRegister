//Registrere kunde
function regKunde() {
    const kunde = {
        fornavn : $("#fornavn").val(),
        passord : $("#passord").val()
    };
    console.log("Fornavn: "+$("#fornavn").val())
    $.post("/lagreKunde", kunde, function(){})
}


//Innlogging
function login(){
    const login = {
        fornavn : $("#fornavn").val(),
        passord : $("#passord").val()
    }
    $.post("/login", login, function(){});
}


//Hente og formatere alle eksisterende kunder for utskrift
function hentKunder(){
    $.post("/hentKunder", function (kunder){
        formaterKunder(kunder)
    });
}

function formaterKunder(kunder) {
    let ut = "<table class='table table-striped'><tr><th>Fornavn</th><th>Passord</th>";
    for (const kunde of kunder) {
        ut += "<tr><td>" + kunde.fornavn + "</td><td>" + kunde.passord + "</td>";
    }
    ut += "</table>";
    $("#kundene").html(ut);
}