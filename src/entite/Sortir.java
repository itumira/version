package entite;

import exceptions.InvalidExcept;
import java.sql.Date;
import java.sql.Time;
import metier.Metier;

public class Sortir extends Metier {

    int idsortir;
    int idemploye;
    Date datesortir;
    Time heuresortir;
    int identrer;

    public int getIdsortir() {
        return idsortir;
    }

    public void setIdsortir(int idsortir) {
        this.idsortir = idsortir;
    }

    public int getIdemploye() {
        return idemploye;
    }

    public void setIdemploye(int idemploye) {
        this.idemploye = idemploye;
    }

    public Date getDatesortir() {
        return datesortir;
    }

    public void setDatesortir(Date datesortir) {
        this.datesortir = datesortir;
    }

    public void setDatesortir(String datesortir) {
        try {
            this.datesortir = Date.valueOf(dateSouple(datesortir));
        } catch (InvalidExcept ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Time getHeuresortir() {
        return heuresortir;
    }

    public void setHeuresortir(Time heuresortir) {
        this.heuresortir = heuresortir;
    }

    public void setHeuresortir(String heuresortir) {
        this.heuresortir = heureSouple(heuresortir);
    }

    public int getIdentrer() {
        return identrer;
    }

    public void setIdentrer(int identrer) {
        this.identrer = identrer;
    }

    @Override
    public String toString() {
        return "default," + getIdemploye() + ",'" + this.getDatesortir() + "','" + getHeuresortir() + "'," + this.getIdentrer();
    }

}
