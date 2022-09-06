function validerNavn(navn){
    const regexp = /^[a-zA-ZæøåÆØÅ. \-]{2,20}$/;
    const ok = regexp.test(navn);

    if(!ok){
        $("#regexNavn").html("Navnet må bestå av 2 til 20 bokstaver");
        return false;
    }else{
        $("#regexNavn").html("");
        return true;
    }
}
function validerPass(pass){
    const regexp = /^[a-zA-ZæøåÆØÅ. \-]{4,20}$/;
    const ok = regexp.test(pass);

    if(!ok){
        $("#regexPassord").html("Passordet må bestå av 4 til 20 bokstaver");
        return false;
    }else{
        $("#regexPassord").html("");
        return true;
    }
}