package entite;

import exceptions.InvalidExcept;
import java.sql.Date;
import java.sql.Time;
import metier.Metier;

public class Heuresup extends Metier {

    int idheuresup;
    Time duree_heuresup;
    Date date_heuresup;
    int idemploye;
    int idsortir;

    public int getIdheuresup() {
        return idheuresup;
    }

    public void setIdheuresup(int idheuresup) {
        this.idheuresup = idheuresup;
    }

    public Time getDuree_heuresup() {
        return duree_heuresup;
    }

    public void setDuree_heuresup(Time duree_heuresup) {
        this.duree_heuresup = duree_heuresup;
    }

    public void setDuree_heuresup(String duree_heuresup) {
        this.duree_heuresup = Time.valueOf(duree_heuresup);
    }

    public void setDuree_heuresup(long duree_heuresup) {
        setDuree_heuresup(new Time(duree_heuresup));
    }

    public Date getDate_heuresup() {
        return date_heuresup;
    }

    public void setDate_heuresup(Date date_heuresup) {
        this.date_heuresup = date_heuresup;
    }

    public void setDate_heuresup(String date_heuresup) {
        try {
            this.date_heuresup = Date.valueOf(dateSouple(date_heuresup));
        } catch (InvalidExcept ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int getIdemploye() {
        return idemploye;
    }

    public void setIdemploye(int idemploye) {
        this.idemploye = idemploye;
    }

    public int getIdsortir() {
        return idsortir;
    }

    public void setIdsortir(int idsortir) {
        this.idsortir = idsortir;
    }

    @Override
    public String toString() {
        return "default,'" + getDuree_heuresup() + "','" + getDate_heuresup() + "'," + getIdemploye() + "," + getIdsortir();
    }

}
