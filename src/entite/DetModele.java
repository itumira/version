package entite;

import java.sql.Date;

public class DetModele {

    private int iddetmodele;
    private String nommodele;
    private Date semainemodele;

    @Override
    public String toString() {
        return "default,'" + nommodele + "','" + semainemodele + "'";
    }

    public DetModele(String nommodele, Date semainemodele) {
        this.nommodele = nommodele;
        this.semainemodele = semainemodele;
    }

    public DetModele() {
    }

    public int getIddetmodele() {
        return iddetmodele;
    }

    public void setIddetmodele(int iddetmodele) {
        this.iddetmodele = iddetmodele;
    }

    public String getNommodele() {
        return nommodele;
    }

    public void setNommodele(String nommodele) {
        this.nommodele = nommodele;
    }

    public Date getSemainemodele() {
        return semainemodele;
    }

    public void setSemainemodele(Date semainemodele) {
        this.semainemodele = semainemodele;
    }
    
    
}
