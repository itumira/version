package metier;

import database.BasicsFunctions;
import database.DBConnect;
import entite.Absence;
import entite.Entrer;
import entite.Heuresup;
import entite.HoraireByEmp;
import entite.JourFerie;
import entite.Retard;
import entite.Sortir;
import exceptions.InvalidExcept;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import panel.Pointer;
import personne.Employe;

public class MetierPointer extends MetierJourFerie {

    Pointer pointer;

    public MetierPointer() {
    }

    public MetierPointer(Pointer pointer) {
        this.pointer = pointer;
    }

    /*/ CONTROLLEURS /*/
    /**
     * Mijery raha misy ilay employé : raha misy dia mamerina employé sinon
     * mamerina null
     *
     * @param emp
     * @param c
     * @return
     */
    public Employe empExiste(String emp, Connection c) {
        Employe employe = new Employe();
        String query = "SELECT * FROM employe WHERE emp_cin='" + emp + "' or emp_matr='" + emp + "'";
        BasicsFunctions bf = new BasicsFunctions();
        ResultSet rs = bf.execquery(query, c);
        int existe = 0;
        try {
            boolean hasnext = rs.next();
            while (hasnext) {
                existe++;
                hasnext = rs.next();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (existe == 1) {
            rs = bf.execquery(query, c);
            try {
                boolean hasnext = rs.next();
                while (hasnext) {
                    employe.setIdemploye(rs.getInt("idemploye"));
                    employe.setEmp_nom(rs.getString("emp_nom"));
                    employe.setEmp_prenom(rs.getString("emp_prenom"));
                    employe.setEmp_birth(rs.getDate("emp_birth"));
                    employe.setEmp_cin(rs.getString("emp_cin"));
                    employe.setEmp_contact(rs.getString("emp_contact"));
                    employe.setEmp_matr(rs.getString("emp_matr"));
                    employe.setDate_embauche(rs.getDate("date_embauche"));
                    hasnext = rs.next();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return employe;
        } else {
            return null;
        }
    }

    /**
     * Mijery raha efa misy entrée tamin'iny journée iny. Raha efa misy dia
     * alainy ny récent indrindra. Sinon mamerina null izy.
     *
     * @param emp
     * @param date
     * @param c
     * @return
     */
    public Entrer getLastEntreeDeLaJournee(String emp, String date, Connection c) {
        String query = "SELECT * FROM entrer e INNER JOIN employe emp ON e.idemploye=emp.idemploye "
                + "WHERE (emp.emp_matr='" + emp + "' or emp.emp_cin='" + emp + "') and e.dateentrer='" + date + "' "
                + "ORDER BY e.identrer DESC LIMIT 1";
        System.out.println(query);
        BasicsFunctions bf = new BasicsFunctions();

        ResultSet rs = bf.execquery(query, c);
        Entrer entrer = null;
        int isa = 0;
        try {
            boolean hasnext = rs.next();
            while (hasnext) {
                isa++;
                hasnext = rs.next();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (isa == 1) {
            try {
                rs = bf.execquery(query, c);

                boolean hasnext = rs.next();
                entrer = new Entrer();
                while (hasnext) {
                    entrer.setIdentrer(rs.getInt("identrer"));
                    entrer.setIdemploye(rs.getInt("idemploye"));
                    entrer.setDateentrer(rs.getDate("dateentrer"));
                    entrer.setHeureentree(rs.getTime("heureentree"));
                    hasnext = rs.next();
                }
            } catch (SQLException ex) {
            }
        } else {
            entrer = null;
        }
        return entrer;
    }

    public Sortir getLastSortieJournee(int idemp, int identrer, Connection c) throws InvalidExcept {
        Sortir sortir = new Sortir();
        String query = "SELECT * FROM sortie_emp WHERE idemploye='" + idemp + "' and identrer='" + identrer + "'";
        System.out.println(query);
        BasicsFunctions bf = new BasicsFunctions();
        ResultSet rs = bf.execquery(query, c);
        int isa = 0;
        try {
            System.out.println("miditra ato");
            boolean hasnext = rs.next();
            while (hasnext) {
                isa++;
                hasnext = rs.next();
            }
        } catch (SQLException e) {
            // sortir = null;
            System.out.println(e.getMessage());
        }

        if (isa == 1) {
            try {
                rs = bf.execquery(query, c);
                boolean hasnext = rs.next();
                while (hasnext) {
                    sortir.setIdsortir(rs.getInt("idsortir"));
                    sortir.setIdemploye(rs.getInt("idemploye"));
                    sortir.setDatesortir(rs.getDate("datesortir"));
                    sortir.setHeuresortir(rs.getTime("heuresortir"));
                    sortir.setIdentrer(rs.getInt("identrer"));
                    hasnext = rs.next();
                }
            } catch (SQLException ex) {
                Logger.getLogger(MetierPointer.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            sortir = null;
        }
        return sortir;
    }

    /**
     * Pointer entrée et retourne l'id récemment inséré.
     *
     * @param e
     * @param c
     * @return
     * @throws exceptions.InvalidExcept
     */
    public int pointerEntree(Entrer e, Connection c) throws InvalidExcept {
        pointerabsence(e.getIdemploye(), (e.getDateentrer()).toString(), e.getHeureentree(), c);
        BasicsFunctions bf = new BasicsFunctions();
        int a = bf.insertionObjI("entrer", e);
        return a;
    }

    /**
     * Pointer Entrer : mijery raha miasa atoandro na alina.
     *
     * @param emp
     * @param date
     * @param heure
     * @param c
     * @throws InvalidExcept
     */
    public void entrer(Employe emp, String date, Time heure, Connection c) throws InvalidExcept {
        HoraireByEmp heb = emp.getHoraire(c);

        Entrer e = new Entrer();
        e.setDateentrer(date);
        e.setHeureentree(heure);
        e.setIdemploye(emp.getIdemploye());

        Time nidirany = e.getHeureentree();
        Time fidirany = heb.getFidirana();
        Time firavany = heb.getFiravana();

        System.out.println(fidirany);

        long nidir = nidirany.getTime();
        long fidir = fidirany.getTime();
        long firav = firavany.getTime();
        long longdate = 0;

        if (firav < fidir) {
            System.out.println("miasa alina");
            longdate = (Date.valueOf(dateSouple(date))).getTime() + 86400000;
            fidir = fidir + longdate - 86400000;
            firav = firav + longdate;
            nidir = nidir + longdate - 86400000;
        } else {
            System.out.println("miasa antoandro");
            longdate = (Date.valueOf(dateSouple(date))).getTime();
            fidir = fidir + longdate;
            firav = firav + longdate;
            nidir = nidir + longdate;
        }
        Entrer entrer = getLastEntreeDeLaJournee(emp.getEmp_matr(), dateSouple(date), c);

        if (firav >= nidir) {
            if (nidir >= fidir) {
                if (tsyFiasana(e.getDateentrer(), e.getHeureentree())) {
                    System.out.println("tsy tara fa jour férié");
                    if (entrer != null) {
                        Sortir sor = getLastSortieJournee(emp.getIdemploye(), entrer.getIdentrer(), c);
                        if (sor == null) {
                            throw new InvalidExcept("Veuillez pointer la sortie avant de pointer une entree.", "Erreur");
                        } else {
                            System.out.println("afaka miditra raha supérieur am "
                                    + "last sortie de la journee ny entree vaovap");
                            if ((sor.getHeuresortir()).before(e.getHeureentree())) {
                                int rep = pointerEntree(e, c);
                            } else {
                                throw new InvalidExcept("L'heure d'entrée doit être "
                                        + "supérieure ou égale à l'heure de sortie.", "Erreur");
                            }
                        }
                    } else {
//                        Sortir sor = getLastSortieJournee(emp.getIdemploye(), entrer.getIdentrer(), c);
//                        System.out.println("afaka miditra raha supérieur am "
//                                + "last sortie de la journee ny entree vaovap");
//                        if ((sor.getHeuresortir()).before(e.getHeureentree())) {
                        int rep = pointerEntree(e, c);
//                        } else {
//                            throw new InvalidExcept("L'heure d'entrée doit être "
//                                    + "supérieure ou égale à l'heure de sortie.", "Erreur");
//                        }
                    }
                } else {
                    System.out.println("tara");
                    if (entrer != null) {
                        if (getLastSortieJournee(emp.getIdemploye(), entrer.getIdentrer(), c) == null) {
                            throw new InvalidExcept("Veuillez pointer la sortie avant de pointer une entree.", "Erreur");
                        } else {
                            Sortir sor = getLastSortieJournee(emp.getIdemploye(), entrer.getIdentrer(), c);
                            System.out.println("afaka miditra fa tara raha supérieur am "
                                    + "last sortie de la journee ny entree vaovap");
                            if ((sor.getHeuresortir()).before(e.getHeureentree())) {
                                int rep = pointerEntree(e, c);
                                pointerRetard(emp.getIdemploye(), dateSouple(date), fidir, nidir, rep, c);
                            } else {
                                throw new InvalidExcept("L'heure d'entrée doit être "
                                        + "supérieure ou égale à l'heure de sortie.", "Erreur");
                            }
                        }
                    } else {
//                        Sortir sor = getLastSortieJournee(emp.getIdemploye(), entrer.getIdentrer(), c);
//                        System.out.println("afaka miditra fa tara raha supérieur am "
//                                + "last sortie de la journee ny entree vaovap");
//                        if ((sor.getHeuresortir()).before(e.getHeureentree())) {
                        int rep = pointerEntree(e, c);
                        pointerRetard(emp.getIdemploye(), dateSouple(date), fidir, nidir, rep, c);
//                        } else {
//                            throw new InvalidExcept("L'heure d'entrée doit être "
//                                    + "supérieure ou égale à l'heure de sortie.", "Erreur");
//                        }
                    }
                }
            } else if (nidir < fidir) {
                System.out.println("tonga aloha");
                if (entrer != null) {
                    Sortir sor = getLastSortieJournee(emp.getIdemploye(), entrer.getIdentrer(), c);
                    if ((sor.getHeuresortir()).before(e.getHeureentree())) {
                        int rep = pointerEntree(e, c);
                    } else {
                        throw new InvalidExcept("L'heure d'entrée doit être "
                                + "supérieure ou égale à l'heure de sortie.", "Erreur");
                    }
                } else {
                    int rep = pointerEntree(e, c);
                }
            }
        } else {
            System.out.println("tsy mahazo miditra aorian'ny firavana");
            throw new InvalidExcept("L'employe ne peut pas entrer après l'heure de sortie", "Erreur");
        }
    }

    /**
     * Pointer retard : heure d'arrivée - heure d'entrée normale - 3h
     *
     * @param emp
     * @param date
     * @param fidirana
     * @param nidirana
     * @param idEntrer
     * @param c
     */
    public void pointerRetard(int emp, String date, long fidirana, long nidirana, int idEntrer, Connection c) {
        Retard r = new Retard();
        r.setDate_retard(date);
        r.setHeure_retard(nidirana - fidirana - 10800000);
        r.setIdemploye(emp);
        r.setIdentrer(idEntrer);
        BasicsFunctions bf = new BasicsFunctions();
        bf.insertionObj("retard", r);
    }

    public Time pointerRetardH(int emp, String date, long fidirana, long nidirana, int idEntrer, Connection c) {
        Retard r = new Retard();
        r.setDate_retard(date);
        r.setHeure_retard(nidirana - fidirana - 10800000);
        r.setIdemploye(emp);
        r.setIdentrer(idEntrer);
        BasicsFunctions bf = new BasicsFunctions();
        return bf.insertionObjH("retard", r);
    }

    /**
     *
     * @param s
     * @param c
     * @return
     */
    public int pointerSortie(Sortir s, Connection c) {
        BasicsFunctions bf = new BasicsFunctions();
        int a = bf.insertionObjI("sortir", s);
        return a;
    }

    /**
     *
     * @param emp
     * @param date
     * @param heure
     * @param c
     * @throws InvalidExcept
     */
    public void sortir(Employe emp, String date, Time heure, Connection c) throws InvalidExcept {
        Entrer lastEntree = null;

        HoraireByEmp heb = emp.getHoraire(c);
        Time fidirany = heb.getFidirana();
        Time firavany = heb.getFiravana();
        long fidir = fidirany.getTime();
        long firav = firavany.getTime();
        if (firav < fidir) {
            System.out.println("miasa alina");
            Date datetmp = Date.valueOf(dateSouple(date));
            long lon = datetmp.getTime() - 86400000;
            Date newdate = new Date(lon);
            lastEntree = getLastEntreeDeLaJournee(emp.getEmp_matr(), String.valueOf(newdate), c);
        } else {
            System.out.println("miasa antoandro");
            lastEntree = getLastEntreeDeLaJournee(emp.getEmp_matr(), dateSouple(date), c);
        }
        //System.out.println("ATO LE IZY : " + lastEntree.getIdentrer());
        if (lastEntree != null) {

            Sortir s = new Sortir();
            s.setDatesortir(date);
            s.setHeuresortir(heure);
            s.setIdemploye(emp.getIdemploye());
            s.setIdentrer(lastEntree.getIdentrer());

            Time niravany = s.getHeuresortir();

            long nirav = niravany.getTime();// + 10800000;

            long longdate = 0;
            boolean miasaalina = false;
            if (firav < fidir) {
                System.out.println("miasa alina");
                longdate = (Date.valueOf(dateSouple(date))).getTime() + 86400000;
                fidir = fidir + longdate - 86400000;
                firav = firav + longdate;
                nirav = nirav + longdate;
                miasaalina = true;
            } else {
                System.out.println("miasa antoandro");
                longdate = (Date.valueOf(dateSouple(date))).getTime();
                fidir = fidir + longdate;
                firav = firav + longdate;
                nirav = nirav + longdate;
            }
            if (fidir < nirav) {
                if (nirav > firav) {
                    System.out.println("manao heure sup");
                    if (tsyFiasana(s.getDatesortir(), s.getHeuresortir())) {
                        if (getLastSortieJournee(emp.getIdemploye(), lastEntree.getIdentrer(), c) != null) {
                            throw new InvalidExcept("Veuillez pointer une entrée avant de pointer une sortie.", "Erreur");
                        } else {
                            System.out.println("Afaka mivoaka fa nanao heure sup");
                            long longentrer = (lastEntree.getDateentrer()).getTime() + (lastEntree.getHeureentree()).getTime();
                            if (nirav >= longentrer) {
                                int rep = pointerSortie(s, c);
                                if (miasaalina == true && isSamedi(s.getDatesortir())) {
                                    //pointerHeureSup(emp, date, fidir, nirav, rep, c);
                                    System.out.println("firavana normale satria miasa alina");
                                } else {
                                    JourFerie jf = getJourFerie(date);
                                    long longjf = (jf.getDatejourferie()).getTime() + (jf.getHeurefin()).getTime();
                                    if (longjf < nirav) {
                                        pointerHeureSup(emp, date, longentrer, longjf, rep, c);
                                        pointerHeureSup(emp, date, longjf, nirav, rep, c);
                                    } else {
                                        pointerHeureSup(emp, date, longentrer, nirav, rep, c);
                                    }

                                }
                            } else {
                                throw new InvalidExcept("L'heure de sortie doit être supérieure à l'heure d'entrée.", "Erreur");
                            }
                        }
                    } else if (!tsyFiasana(s.getDatesortir(), s.getHeuresortir())) {
                        if (getLastSortieJournee(emp.getIdemploye(), lastEntree.getIdentrer(), c) != null) {
                            throw new InvalidExcept("Veuillez pointer une entrée avant de pointer une sortie.", "Erreur");
                        } else {
                            System.out.println("Afaka mivoaka fa nanao heure sup");
                            long longentrer = (lastEntree.getDateentrer()).getTime() + (lastEntree.getHeureentree()).getTime();
                            if (nirav >= longentrer) {
                                int rep = pointerSortie(s, c);
                                pointerHeureSup(emp, date, firav, nirav, rep, c);
                            } else {
                                throw new InvalidExcept("L'heure de sortie doit être supérieure à l'heure d'entrée.", "Erreur");
                            }
                        }
                    }
                } else {
                    System.out.println("mirava aloha");
                    if (tsyFiasana(s.getDatesortir(), s.getHeuresortir())) {
                        long longentrer = (lastEntree.getDateentrer()).getTime() + (lastEntree.getHeureentree()).getTime();
                        if (nirav >= longentrer) {
                            int rep = pointerSortie(s, c);
                            pointerHeureSup(emp, date, fidir, nirav, rep, c);
                        } else {
                            throw new InvalidExcept("L'heure de sortie doit être supérieure à l'heure d'entrée.", "Erreur");
                        }
                    } else if (!tsyFiasana(s.getDatesortir(), s.getHeuresortir())) {
                        if (getLastSortieJournee(emp.getIdemploye(), lastEntree.getIdentrer(), c) != null) {
                            throw new InvalidExcept("Veuillez pointer une entrée avant de pointer une sortie.");
                        } else {
                            System.out.println("Afaka mivoaka fa nivoaka aloha.");
                            long longentrer = (lastEntree.getDateentrer()).getTime() + (lastEntree.getHeureentree()).getTime();
                            if (nirav >= longentrer) {
                                int rep = pointerSortie(s, c);
                            } else {
                                throw new InvalidExcept("L'heure de sortie doit être supérieure à l'heure d'entrée.", "Erreur");
                            }
                        }
                    }
                }
            } else {
                System.out.println("tsy mahazo mivoaka alohan'ny heure fidirana");
                throw new InvalidExcept("L'employe ne peut pas sortir avant l'heure d'entrée.", "Erreur");
            }
        } else {
            throw new InvalidExcept("Veuillez pointer une entrée avant de pointer une sortie.", "Erreur");
        }
    }

    /**
     *
     * @param emp
     * @param date
     * @param firavana
     * @param niravana
     * @param idSortir
     * @param c
     * @return
     */
    public int pointerHeureSup(Employe emp, String date, long firavana, long niravana, int idSortir, Connection c) {
        Heuresup hs = new Heuresup();
        hs.setIdemploye(emp.getIdemploye());
        hs.setDuree_heuresup(niravana - firavana - 10800000);
        hs.setDate_heuresup(date);
        hs.setIdsortir(idSortir);
        BasicsFunctions bf = new BasicsFunctions();
        return bf.insertionObjI("heuresup", hs);
    }

    /**
     *
     *
     * @throws InvalidExcept
     */
    public void faireentrer() throws InvalidExcept {
        DBConnect dbc = new DBConnect();
        Connection c = dbc.getconn();
        String date = pointer.getDate().getText();
        String heure = pointer.getEntrer().getText();
        String identifiant = pointer.getIdentifiant().getText();
        if (!date.equals("") && !heure.equals("") && !identifiant.equals("")) {
            Employe emp = empExiste(identifiant, c);
            if (emp != null) {
                try {
                    entrer(emp, date, heureSouple(heure), c);
                } catch (InvalidExcept ex) {

                }
            } else {
                throw new InvalidExcept("Veuillez entrer le matricule d'un employé existant.", "Erreur");
            }
        } else {
            throw new InvalidExcept("Veuillez remplir le champ date et/ou "
                    + "le champ identifiant et/ou l'heure d'entree.", "Erreur");
        }
    }

    /**
     *
     * @throws InvalidExcept
     */
    public void fairesortir() throws InvalidExcept {
        DBConnect db = new DBConnect();
        Connection c = db.getconn();
        if (!(pointer.getIdentifiant().getText()).equals("") && !(pointer.getDate().getText()).equals("") && !(pointer.getSortir().getText()).equals("")) {
            Employe emp = empExiste(pointer.getIdentifiant().getText(), c);
            System.out.println(emp.getEmp_matr());
            if (emp != null) {
                try {
                    sortir(emp, pointer.getDate().getText(), heureSouple(pointer.getSortir().getText()), c);
                } catch (InvalidExcept ex) {

                }
            } else {
                throw new InvalidExcept("Veuillez entrer le matricule d'un employé existant.", "Erreur");
            }
        } else {
            throw new InvalidExcept("Veuillez remplir le champ date et/ou "
                    + "le champ identifiant et/ou l'heure d'entree.", "Erreur");
        }
    }

    public int faireabsent(int emp, String date) {
        Absence absence = new Absence();
        absence.setIdemploye(emp);
        absence.setDateabsence(date);
        BasicsFunctions bf = new BasicsFunctions();
        return bf.insertionObjI("absence", absence);
    }

    public void pointerabsence(int emp, String date, Time heure, Connection c) throws InvalidExcept {
        Entrer e = avoirlastentree(emp, date, c);
        if (e != null && !tsyFiasana(Date.valueOf(dateSouple(date)), heure)) {
            long datefarany = e.getDateentrer().getTime();
            long dateandroany = (Date.valueOf(dateSouple(date))).getTime();
            if (dateandroany - datefarany > 86400000) {
                for (long o = datefarany + 86400000; o < dateandroany; o = o + 86400000) {
                    System.out.println(o);
                    int faireabsen = faireabsent(emp, (new Date(o)).toString());
                }
            }
        }
    }

    public boolean efananaopointageve(int emp, String date, Connection c) {
        String query = "SELECT count(*) as nombre FROM entrer WHERE idemploye=" + emp + "";
        System.out.println(query);
        BasicsFunctions bf = new BasicsFunctions();
        ResultSet rs = bf.execquery(query, c);
        int existe = 0;
        try {
            boolean hasnext = rs.next();
            while (hasnext) {
                existe = rs.getInt("nombre");
                hasnext = rs.next();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return existe != 0;
    }

    public Entrer avoirlastentree(int emp, String date, Connection c) throws InvalidExcept {
        Entrer entrer = null;
        if (efananaopointageve(emp, date, c)) {
            String query = "SELECT * FROM entrer "
                    + "WHERE idemploye=" + emp + ""
                    + " and dateentrer<'" + dateSouple(date) + "' "
                    + "ORDER BY dateentrer DESC LIMIT 1";
            System.out.println(query);
            BasicsFunctions bf = new BasicsFunctions();

            ResultSet rs = bf.execquery(query, c);
            entrer = new Entrer();
            int isa = 0;
            try {
                boolean hasnext = rs.next();
                while (hasnext) {

                    entrer.setIdentrer(rs.getInt("identrer"));
                    entrer.setIdemploye(rs.getInt("idemploye"));
                    entrer.setDateentrer(rs.getDate("dateentrer"));
                    entrer.setHeureentree(rs.getTime("heureentree"));
                    hasnext = rs.next();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            //  return entrer;
        }
        return entrer;
    }
    
//    
//    public Entrer avoirlastentree(int emp, String date, Connection c) throws InvalidExcept {
//        Entrer entrer = null;
//        if (efananaopointageve(emp, date, c)) {
//            String query = "SELECT * FROM entrer "
//                    + "WHERE idemploye=" + emp + ""
//                    + " and dateentrer<'" + dateSouple(date) + "' "
//                    + "ORDER BY dateentrer DESC LIMIT 1";
//            System.out.println(query);
//            BasicsFunctions bf = new BasicsFunctions();
//
//            ResultSet rs = bf.execquery(query, c);
//            entrer = new Entrer();
//            int isa = 0;
//            try {
//                boolean hasnext = rs.next();
//                while (hasnext) {
//
//                    entrer.setIdentrer(rs.getInt("identrer"));
//                    entrer.setIdemploye(rs.getInt("idemploye"));
//                    entrer.setDateentrer(rs.getDate("dateentrer"));
//                    entrer.setHeureentree(rs.getTime("heureentree"));
//                    hasnext = rs.next();
//                }
//            } catch (SQLException e) {
//                System.out.println(e.getMessage());
//            }
//            //  return entrer;
//        }
//        return entrer;
//    }

}
