package entite;

import exceptions.InvalidExcept;
import java.sql.Date;
import java.sql.Time;
import metier.Metier;

public class JourFerie extends Metier {

    int idjourferie;
    String nom;
    Date datejourferie;
    Time heuredebut;
    Time heurefin;

    @Override
    public String toString() {
        return "default,'" + this.getNom() + "','" + this.getDatejourferie() + "','" + this.getHeuredebut() + "','" + this.getHeurefin() + "'";
    }

    public JourFerie() {
    }

    public JourFerie(int idjourferie, String nom, Date datejourferie, Time heuredebut, Time heurefin) {
        this.idjourferie = idjourferie;
        this.nom = nom;
        this.datejourferie = datejourferie;
        this.heuredebut = heuredebut;
        this.heurefin = heurefin;
    }

    public int getIdjourferie() {
        return idjourferie;
    }

    public void setIdjourferie(int idjourferie) {
        this.idjourferie = idjourferie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDatejourferie() {
        return datejourferie;
    }

    public void setDatejourferie(Date datejourferie) {
        this.datejourferie = datejourferie;
    }

    public void setDatejourferie(String datejourferie) throws InvalidExcept {
        this.datejourferie = Date.valueOf(dateSouple(datejourferie));
    }

    public Time getHeuredebut() {
        return heuredebut;
    }

    public void setHeuredebut(Time heuredebut) {
        this.heuredebut = heuredebut;
    }

    public void setHeuredebut(String heuredebut) {
        this.heuredebut = heureSouple(heuredebut);
    }

    public Time getHeurefin() {
        return heurefin;
    }

    public void setHeurefin(Time heurefin) {
        this.heurefin = heurefin;
    }

    public void setHeurefin(String heurefin) {
        this.heurefin = heureSouple(heurefin);
    }
}
