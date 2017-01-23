package entite;

import metier.Metier;

public class Modele extends Metier {

    int idmodele;
    int idemploye;
    String lundi;
    String mardi;
    String mercredi;
    String jeudi;
    String vendredi;
    String samedi;
    String dimanche;
    int iddetmodele;

    @Override
    public String toString() {
        return "default," + idemploye + "," + lundi
                + "," + mardi + "," + mercredi + "," + jeudi + "," + vendredi
                + "," + samedi + "," + dimanche + "," + iddetmodele;
    }

    public Modele() {
    }

    public Modele(int idmodele, int idemploye, String lundi, String mardi, String mercredi, String jeudi, String vendredi, String samedi, String dimanche, int iddetmodele) {
        this.idmodele = idmodele;
        this.idemploye = idemploye;
        this.lundi = lundi;
        this.mardi = mardi;
        this.mercredi = mercredi;
        this.jeudi = jeudi;
        this.vendredi = vendredi;
        this.samedi = samedi;
        this.dimanche = dimanche;
        this.iddetmodele = iddetmodele;
    }

    public int getIdmodele() {
        return idmodele;
    }

    public void setIdmodele(int idmodele) {
        this.idmodele = idmodele;
    }

    public int getIdemploye() {
        return idemploye;
    }

    public void setIdemploye(int idemploye) {
        this.idemploye = idemploye;
    }

    public int getIddetmodele() {
        return iddetmodele;
    }

    public void setIddetmodele(int iddetmodele) {
        this.iddetmodele = iddetmodele;
    }

    public void setLundi(String lundi) {
        if (lundi.equals("")) {
            this.lundi = "null";
        } else {
            this.lundi = "'" + lundi + "'";
        }
    }

    public void setMardi(String mardi) {
        if (mardi.equals("")) {
            this.mardi = "null";
        } else {
            this.mardi = "'" + mardi + "'";
        }
    }

    public void setMercredi(String mercredi) {
        if (mercredi.equals("")) {
            this.mercredi = "null";
        } else {
            this.mercredi = "'" + mercredi + "'";
        }
    }

    public void setJeudi(String jeudi) {
        if (jeudi.equals("")) {
            this.jeudi = "null";
        } else {
            this.jeudi = "'" + jeudi + "'";
        }
    }

    public void setVendredi(String vendredi) {
        if (vendredi.equals("")) {
            this.vendredi = "null";
        } else {
            this.vendredi = "'" + vendredi + "'";
        }
    }

    public void setSamedi(String samedi) {
        if (samedi.equals("")) {
            this.samedi = "null";
        } else {
            this.samedi = "'" + samedi + "'";
        }
    }

    public void setDimanche(String dimanche) {
        if (dimanche.equals("")) {
            this.dimanche = "null";
        } else {
            this.dimanche = "'" + dimanche + "'";
        }
    }

    public String getLundi() {
        return lundi;
    }

    public String getMardi() {
        return mardi;
    }

    public String getMercredi() {
        return mercredi;
    }

    public String getJeudi() {
        return jeudi;
    }

    public String getVendredi() {
        return vendredi;
    }

    public String getSamedi() {
        return samedi;
    }

    public String getDimanche() {
        return dimanche;
    }

}
