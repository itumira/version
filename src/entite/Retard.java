package entite;

import exceptions.InvalidExcept;
import java.sql.Date;
import java.sql.Time;
import metier.Metier;

public class Retard extends Metier {

    int idretard;
    Time heure_retard;
    Date date_retard;
    int idemploye;
    int identrer;

    public int getIdretard() {
        return idretard;
    }

    public void setIdretard(int idretard) {
        this.idretard = idretard;
    }

    public Time getHeure_retard() {
        return heure_retard;
    }

    public void setHeure_retard(Time heure_retard) {
        this.heure_retard = heure_retard;
    }

    public void setHeure_retard(String heure_retard) {
        this.heure_retard = Time.valueOf(heure_retard);
    }

    public void setHeure_retard(long heure_retard) {
        setHeure_retard(new Time(heure_retard));
    }

    public Date getDate_retard() {
        return date_retard;
    }

    public void setDate_retard(Date date_retard) {
        this.date_retard = date_retard;
    }

    public void setDate_retard(String date_retard) {
        try {
            this.date_retard = Date.valueOf(dateSouple(date_retard));
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

    public int getIdentrer() {
        return identrer;
    }

    public void setIdentrer(int identrer) {
        this.identrer = identrer;
    }

    @Override
    public String toString() {
        return "default,'" + getHeure_retard() + "','" + getDate_retard() + "'," + getIdemploye() + "," + getIdentrer();
    }

}
