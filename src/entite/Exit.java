package entite;

public class Exit {

    int idemploye;
    String date;
    String heure;

    public Exit() {
    }

    public Exit(int idemploye, String date, String heure) {
        this.idemploye = idemploye;
        this.date = date;
        this.heure = heure;
    }

    public int getIdemploye() {
        return idemploye;
    }

    public void setIdemploye(int idemploye) {
        this.idemploye = idemploye;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }
    
}
