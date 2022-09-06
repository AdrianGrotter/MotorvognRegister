package oslomet.webprog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class MotorvognRepository {

    @Autowired
    private JdbcTemplate db;

    Logger logger = LoggerFactory.getLogger(MotorvognRepository.class);

    //Hente, lagre og slette biler/motorvogner
    public List<Bil> hentBiler(){
        String sql = "SELECT * FROM Bil";
        return db.query(sql, new BeanPropertyRowMapper(Bil.class));
    }

    public void lagreBil(Motorvogn bil) {
        String sql = "INSERT INTO Motorvogn (personnr, navn, adresse, kjennetegn, merke, type) VALUES(?,?,?,?,?,?)";
        db.update(sql, bil.getPersonnr(), bil.getNavn(), bil.getAdresse(), bil.getKjennetegn(), bil.getMerke(), bil.getType());
    }

    public List<Motorvogn> hentAlleMotorvogner(){
        String sql = "SELECT * FROM Motorvogn";
        return db.query(sql, new BeanPropertyRowMapper(Motorvogn.class));
    }

    public void slettAlleBiler(){
        String sql = "DELETE FROM Motorvogn";
        db.update(sql);
    }

    public void slettBil(String kjennetegn){
        String sql = "DELETE FROM Motorvogn WHERE kjennetegn LIKE '"+kjennetegn+"'";
        db.update(sql);
    }

    public void endreBil(Motorvogn bil){
        String sql = "UPDATE Motorvogn SET personnr=?,navn=?,adresse=?,kjennetegn=?,merke=?,type=? where id=?";
        db.update(sql,bil.getPersonnr(), bil.getNavn(),bil.getAdresse(),bil.getKjennetegn(),bil.getMerke(),bil.getType(),bil.getId());
    }


    //Alt om passord. Kryptering og sjekking
    public boolean sjekkNavnOgPassord (Kunde kunde) {
        String sql = "SELECT * FROM Kunde WHERE fornavn=?";
        try{
            Kunde dbKunde = db.queryForObject(sql,
                    BeanPropertyRowMapper.newInstance(Kunde.class),new Object[]{kunde.getFornavn()});
            return sjekkPassord(dbKunde.getPassord(),kunde.getPassord());
        }
        catch (Exception e){
            logger.error("Feil i sjekkNavnOgPassord : "+e);
            return false;
        }
    }

    private boolean sjekkPassord(String passord, String hashPassord){
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        return bCrypt.matches(hashPassord, passord);
    }

    private String krypterPassord(String passord){
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder(14);
        return bCrypt.encode(passord);
    }


    //Lagring og henting av kunder
    public void lagreKunde(Kunde enKunde) {
        String sql = "INSERT INTO Kunde (fornavn, passord) VALUES(?,?)";
        String kryptertPassord = krypterPassord(enKunde.getPassord());
        db.update(sql, enKunde.getFornavn(), kryptertPassord);
    }

    public List<Kunde> hentAlleKunder(){
        String sql = "SELECT * FROM Kunde";
        return db.query(sql,new BeanPropertyRowMapper(Kunde.class));
    }
}




/*@Transactional
    public void lagreBil(Motorvogn bil) {
        String sql1 = "INSERT INTO Motorvogn (personnr, navn, adresse, kjennetegn, merke, type) VALUES(?,?,?,?,?,?)";
        KeyHolder id = new GeneratedKeyHolder();
        try {
            db.update(con -> {
                PreparedStatement par = con.prepareStatement(sql1, new String[]{"MId"});
                par.setString(1, bil.getPersonnr());
                par.setString(2, bil.getNavn());
                par.setString(3, bil.getAdresse());
                par.setString(4, bil.getKjennetegn());
                par.setString(5, bil.getMerke());
                par.setString(6, bil.getType());
                return par;
            }, id);
            int mid = id.getKey().intValue();
            db.update(sql1,mid);
        }
        catch (Exception e) {
            logger.error("Feil i lagring av motorvognen! " + e);
        }
    }*/