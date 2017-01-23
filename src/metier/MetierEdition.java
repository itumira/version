package metier;

import database.BasicsFunctions;
import database.DBConnect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import paie.Editer;
import panel.Edition;
import personne.Employe;

public class MetierEdition extends Metier {

    Edition edition;

    public Edition getEdition() {
        return edition;
    }

    public MetierEdition() {
    }

    public MetierEdition(Edition edition) {
        this.edition = edition;
    }

    public List<Employe> getAllEmploye() {
        List<Employe> list = new ArrayList();
        String query = "SELECT * FROM EMPLOYE";

        DBConnect db = new DBConnect();
        Connection c = db.getconn();
        BasicsFunctions bf = new BasicsFunctions();

        ResultSet rs = bf.execquery(query, c);

        try {
            boolean hasnext = rs.next();
            while (hasnext) {
                Employe tmp = new Employe();
                tmp.setIdemploye(rs.getInt("idemploye"));
                tmp.setEmp_nom(rs.getString("emp_nom"));
                tmp.setEmp_prenom(rs.getString("emp_prenom"));
                tmp.setEmp_birth(rs.getDate("emp_birth"));
                tmp.setEmp_contact(rs.getString("emp_contact"));
                tmp.setEmp_matr(rs.getString("emp_matr"));
                tmp.setDate_embauche(rs.getDate("date_embauche"));
                tmp.setEmp_cin(rs.getString("emp_cin"));
                list.add(tmp);
                // = rs.getInt("nbr");
                hasnext = rs.next();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }
        return list;
    }

    public int[] moisannee(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int annee = cal.get(Calendar.YEAR);
        int[] rep = new int[2];
        rep[0] = month + 1;
        rep[1] = annee;
        return rep;
    }

    public Time getRetardDuMois(int emp, Date date) {
        int[] critmoisannee = moisannee(date);
        String query = "SELECT * FROM retard where idemploye = " + emp + " and (EXTRACT(MONTH FROM date_retard))=" + critmoisannee[0] + " AND (EXTRACT(YEAR FROM date_retard))=" + critmoisannee[1];
        //System.out.println(query);

        DBConnect db = new DBConnect();
        Connection c = db.getconn();
        BasicsFunctions bf = new BasicsFunctions();

        ResultSet rs = bf.execquery(query, c);

        long reponse = 0;
        try {
            boolean hasnext = rs.next();
            while (hasnext) {
                reponse = reponse + (rs.getTime("heure_retard")).getTime();
                hasnext = rs.next();
            }
        } catch (SQLException ex) {

        }
        //System.out.println(reponse+10800000);
        if (reponse != 0) {
            //System.out.println(reponse+10800000);
            return new Time(reponse + 10800000);
        } else {
            return new Time(-10800000);
        }
    }

    public double getLastSalaire(int emp) {
        String query = "SELECT * FROM SALAIRE WHERE IDEMPLOYE=1 ORDER BY IDSALAIRE DESC LIMIT 1";
        DBConnect db = new DBConnect();
        Connection c = db.getconn();
        BasicsFunctions bf = new BasicsFunctions();

        ResultSet rs = bf.execquery(query, c);

        double reponse = 0;
        try {
            boolean hasnext = rs.next();
            while (hasnext) {
                reponse = rs.getDouble("salaire");
                hasnext = rs.next();
            }
        } catch (SQLException ex) {

        }
        return reponse;
    }

    public double salaireHoraire(int emp) {
        double salairemonth = getLastSalaire(emp);
        return salairemonth / 173.33;
    }

    public double retenuRetard(int emp, Date date) {
        Time t = getRetardDuMois(emp, date);
        double s = salaireHoraire(emp);
        Calendar cal = Calendar.getInstance();
        cal.setTime(t);
        int heure = cal.get(Calendar.HOUR);
        return heure * s;
    }

    public double getRetenuCnaps(int emp, Date date) {
        return getLastSalaire(emp) / 100;
    }

    public int mois(String month) {
        String[] mo = {"Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre"};
        for (int i = 0; i < mo.length; i++) {
            if (mo[i].equals(month)) {
                return i + 1;
            }
        }
        return 0;
    }

    public void setTableau() {
        DefaultTableModel model = new DefaultTableModel();
        //jTable1.setModel(model);
        List<Employe> listeemp = getAllEmploye();

        Editer e = new Editer();
        int nbrEmp = listeemp.size();
        Object[] emp_identif;
        Object[] gain;
        Object[] retenu;
        Object[] netapayer;
        Object[] nbre;

        emp_identif = new Object[nbrEmp];
        gain = new Object[nbrEmp];
        retenu = new Object[nbrEmp];
        netapayer = new Object[nbrEmp];

        nbre = new Object[nbrEmp];
        int i = 0;
        for (Employe emp : listeemp) {
            nbre[i] = (Object) (i + 1);
            emp_identif[i] = (Object) emp.getEmp_matr();
            i++;
            //gain[i] = (Object)ef.getAllGains(emp.getIdemploye(),date);
            //retenu[i] = (Object)ef.getAllRetenus(emp.getIdemploye(),date);
            //netapayer[i] = (Object)ef.getNetAPayer(emp.getIdemploye(),date);
        }

    }
}
