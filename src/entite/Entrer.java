package entite;

import exceptions.InvalidExcept;
import java.sql.Date;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.Metier;

public class Entrer extends Metier{

    int identrer;
    int idemploye;
    Date dateentrer;
    Time heureentree;

    public int getIdentrer() {
        return identrer;
    }

    public void setIdentrer(int identrer) {
        this.identrer = identrer;
    }

    public int getIdemploye() {
        return idemploye;
    }

    public void setIdemploye(int idemploye) {
        this.idemploye = idemploye;
    }

    public Date getDateentrer() {
        return dateentrer;
    }

    public void setDateentrer(Date dateentrer) {
        this.dateentrer = dateentrer;
    }

    public void setDateentrer(String date_entrer) {
        try {
            this.dateentrer = Date.valueOf(dateSouple(date_entrer));
        } catch (InvalidExcept ex) {
            Logger.getLogger(Entrer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int setMois(String mois) {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        for (int i = 0; i < months.length; i++) {
            if (mois.equals(months[i])) {
                return i + 1;
            }
        }
        return 0;
    }

    public Time getHeureentree() {
        return heureentree;
    }

    public void setHeureentree(Time heureentree) {
        this.heureentree = heureentree;
    }

    public void setHeureentree(String heure_entrer) {
        this.heureentree = heureSouple(heure_entrer);
    }


    @Override
    public String toString() {
        return "default," + getIdemploye() + ",'" + getDateentrer() + "','" + getHeureentree() + "'";
    }
}
