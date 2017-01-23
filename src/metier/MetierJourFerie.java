package metier;

import database.BasicsFunctions;
import database.DBConnect;
import entite.JourFerie;
import exceptions.InvalidExcept;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import panel.PanelJourFerie;

public class MetierJourFerie extends Metier {

    PanelJourFerie jourferie;

    /**
     * Constructeur tsotra
     */
    public MetierJourFerie() {
    }

    /**
     * Constructeur miasa raha te hampiasa izay zavatra ao amin'ilay panel jour
     * ferier
     *
     * @param jourferie
     */
    public MetierJourFerie(PanelJourFerie jourferie) {
        this.jourferie = jourferie;
    }

    /**
     * Fonction pour Inserer Jour Ferier
     *
     * @throws InvalidExcept
     */
    public void insertJourFerie() throws InvalidExcept {
        String name1 = jourferie.getNomfield().getText();
        String date1 = jourferie.getDatefield().getText();
        String heuredebut1 = jourferie.getHeuredebut().getText();
        String heurefin1 = jourferie.getHeurefin().getText();
        JourFerie jourf = new JourFerie();
        jourf.setNom(name1);
        jourf.setDatejourferie(date1);
        jourf.setHeuredebut(heuredebut1);
        jourf.setHeurefin(heurefin1);

        BasicsFunctions bf = new BasicsFunctions();
        bf.insertionObj("jourferie", jourf);
        setTableJourFerier();
    }

    /**
     * Avoir tous les jours fériers de l'année
     *
     * @param annee
     * @return
     */
    public List<JourFerie> getJoursFerieThisYear(String annee) {
        List<JourFerie> liste = new ArrayList();
        String query = "SELECT * FROM jourferie where (EXTRACT(YEAR FROM datejourferie))='" + annee + "'";

        DBConnect db = new DBConnect();
        Connection c = db.getconn();
        BasicsFunctions bf = new BasicsFunctions();
        ResultSet rs = bf.execquery(query, c);
        try {
            boolean hasnext = rs.next();
            while (hasnext) {
                JourFerie tmp = new JourFerie();
                tmp.setIdjourferie(rs.getInt("idjourferie"));
                tmp.setNom(rs.getString("nom"));
                tmp.setDatejourferie(rs.getDate("datejourferie"));
                tmp.setHeuredebut(rs.getTime("heuredebut"));
                tmp.setHeurefin(rs.getTime("heurefin"));
                liste.add(tmp);
                hasnext = rs.next();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
        return liste;
    }

    /**
     * Avoir les détails sur le jour férier donné.
     *
     * @param date
     * @return
     */
    public JourFerie getJourFerie(String date) {
        JourFerie jourferie = new JourFerie();
        String query = "SELECT * FROM jourferie where datejourferie='" + date + "'";
        DBConnect db = new DBConnect();
        Connection c = db.getconn();
        BasicsFunctions bf = new BasicsFunctions();
        ResultSet rs = bf.execquery(query, c);
        try {
            boolean hasnext = rs.next();
            while (hasnext) {
                JourFerie tmp = new JourFerie();
                tmp.setIdjourferie(rs.getInt("idjourferie"));
                tmp.setNom(rs.getString("nom"));
                tmp.setDatejourferie(rs.getDate("datejourferie"));
                tmp.setHeuredebut(rs.getTime("heuredebut"));
                tmp.setHeurefin(rs.getTime("heurefin"));
                jourferie = tmp;
                hasnext = rs.next();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
        return jourferie;
    }

    /**
     * Mijery raha jour ferier lay date. Si oui : mijery raha ao anatiny ilay
     * heure nomena na tsia
     *
     * @param heure
     * @param date
     * @return
     * @throws InvalidExcept
     */
    public boolean isJourFerier(Time heure, String date) throws InvalidExcept {
        Date datesouple = Date.valueOf(dateSouple(date));
        Calendar cal = Calendar.getInstance();
        cal.setTime(datesouple);

        String annee = String.valueOf(cal.getWeekYear());
        for (JourFerie jourferier : getJoursFerieThisYear(annee)) {
            if (datesouple.equals(jourferier.getDatejourferie())) {
                return heure.after(jourferier.getHeuredebut()) && heure.before(jourferier.getHeurefin());
            }
        }
        return false;
    }

    /**
     * Mijery hoe week-end ve ilay andro. Rehefa tsy week-end dia mijery raha
     * jour férier.
     *
     * @param date
     * @param time
     * @return
     * @throws InvalidExcept
     */
    public boolean tsyFiasana(Date date, Time time) throws InvalidExcept {
        if (!isWeekEnd(date)) {
            return isJourFerier(time, date.toString());
        } else {
            return true;
        }
    }

    /*/ AFFICHAGE /*/
    /**
     * Manisy an'ireo jour ferier de l'année ao amin'ilay tableau
     */
    public void setTableJourFerier() {
        DefaultTableModel model = new DefaultTableModel();
        int dateannee = getAnneeAuj();
        List<JourFerie> joursferies = getJoursFerieThisYear(String.valueOf(dateannee));

        Object[] nom = new Object[joursferies.size()];
        Object[] dates = new Object[joursferies.size()];
        Object[] debut = new Object[joursferies.size()];
        Object[] fin = new Object[joursferies.size()];
        int i = 0;
        for (JourFerie jour : joursferies) {
            nom[i] = (Object) jour.getNom();
            dates[i] = (Object) jour.getDatejourferie();
            debut[i] = (Object) jour.getHeuredebut();
            fin[i] = (Object) jour.getHeurefin();
            i++;
        }

        jourferie.getListejourferie().setModel(model);

        model.addColumn("Nom", nom);
        model.addColumn("Date", dates);
        model.addColumn("de", debut);
        model.addColumn("à", fin);
    }
}
