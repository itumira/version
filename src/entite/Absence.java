package entite;

import exceptions.InvalidExcept;
import java.sql.Date;
import metier.MetierPointer;

public class Absence extends MetierPointer {

    int idabsence;
    int idemploye;
    Date dateabsence;

    @Override
    public String toString() {
        return "default," + idemploye + ",'" + dateabsence + "'";
    }

    public Absence() {
    }

    public Absence(int idabsence, int idemploye, Date dateabsence) {
        this.idabsence = idabsence;
        this.idemploye = idemploye;
        this.dateabsence = dateabsence;
    }

    public int getIdabsence() {
        return idabsence;
    }

    public void setIdabsence(int idabsence) {
        this.idabsence = idabsence;
    }

    public int getIdemploye() {
        return idemploye;
    }

    public void setIdemploye(int idemploye) {
        this.idemploye = idemploye;
    }

    public Date getDateabsence() {
        return dateabsence;
    }

    public void setDateabsence(Date dateabsence) {
        this.dateabsence = dateabsence;
    }

    public void setDateabsence(String dateabsence) {
        try {
            this.dateabsence = Date.valueOf(dateSouple(dateabsence));
        } catch (InvalidExcept ex) {
        }
    }
}
