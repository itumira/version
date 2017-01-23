package entite;

import java.sql.Time;

public class HoraireByEmp {

    int idhoraire;
    int idemploye;
    Time fidirana;
    Time firavana;

    @Override
    public String toString() {
        return "default," + idemploye + ",'" + fidirana + "','" + firavana + "'";
    }

    public int getIdhoraire() {
        return idhoraire;
    }

    public void setIdhoraire(int idhoraire) {
        this.idhoraire = idhoraire;
    }

    public int getIdemploye() {
        return idemploye;
    }

    public void setIdemploye(int idemploye) {
        this.idemploye = idemploye;
    }

    public Time getFidirana() {
        return fidirana;
    }

    public void setFidirana(Time fidirana) {
        this.fidirana = fidirana;
    }

    public Time getFiravana() {
        return firavana;
    }

    public void setFiravana(Time firavana) {
        this.firavana = firavana;
    }
}
