package oslomet.webprog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
public class MotorvognController {


    @Autowired
    MotorvognRepository rep;

    @GetMapping("/hentBiler")
    public List<Bil> hentBiler() {
        return rep.hentBiler();
        }

    @PostMapping("/lagre")
    public void lagreBil(Motorvogn bil) {
        if (session.getAttribute("Innlogget") != null) {
            rep.lagreBil(bil);
        }else{
            System.out.println("Ikke innlogget");
        }
    }

    @GetMapping("/hentAlle")
    public List<Motorvogn> hentAlle(String verdi){
        List<Motorvogn> motorvogner = rep.hentAlleMotorvogner();

        // :)
        System.out.println("Hent alle sin verdi: "+verdi);

        if (verdi.equals("Merke")){
            Collections.sort(motorvogner, new Comparator<Motorvogn>() {
                @Override
                public int compare(Motorvogn b1, Motorvogn b2) {
                    if (!b1.getMerke().equals(b2.getMerke())) {
                        return b1.getMerke().compareTo(b2.getMerke());
                    }
                    return b1.getType().compareTo(b2.getType());
                }
            });
        }else if (verdi.equals("Navn")){
            Collections.sort(motorvogner, new Comparator<Motorvogn>() {
                @Override
                public int compare(Motorvogn b1, Motorvogn b2) {
                    return b1.getNavn().compareTo(b2.getNavn());
                }
            });
        }else{
            System.out.print("Error i hent alle. Udefinert select? verdi: "+verdi+"\n");
        }

        return motorvogner;
    }

    @GetMapping("/slettAlle")
    public void slettAlle(){
        rep.slettAlleBiler();
    }

    @GetMapping("/slettBil")
    public void slettBil(String kjennetegn){
        rep.slettBil(kjennetegn);
    }

    @PostMapping("/endre")
    public void endreBil(Motorvogn bil){
        if (session.getAttribute("Innlogget") != null) {
            rep.endreBil(bil);
        }else{
            System.out.println("Feil 37");
        }
    }

    @PostMapping("/lagreKunde")
    public void lagreKunde(Kunde enKunde){
        rep.lagreKunde(enKunde);

    }

    @PostMapping("/hentKunder")
    public List<Kunde> hentKunder() {
        List<Kunde> alleKunder = new ArrayList<>();
        if (session.getAttribute("Innlogget") != null) {
            alleKunder = rep.hentAlleKunder();
        }
        return alleKunder;
    }

    @Autowired
    private HttpSession session;

    @PostMapping("/login")
    public boolean login(Kunde kunde) {
        if(rep.sjekkNavnOgPassord(kunde)) {
            session.setAttribute("Innlogget", kunde);
            System.out.println("Du ble logget inn");
            return true;
        }
        return false;
    }

}

