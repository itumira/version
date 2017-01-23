package paie;

import database.BasicsFunctions;
import database.DBConnect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import personne.Employe;

public class Editer {

    public Employe getEmployeById(String matr) {
        String query = "SELECT * FROM EMPLOYE WHERE emp_matr='" + matr + "'";

        DBConnect db = new DBConnect();
        Connection c = db.getconn();
        BasicsFunctions bf = new BasicsFunctions();

        ResultSet rs = bf.execquery(query, c);

        Employe tmp = new Employe();
        int i = 0;
        try {
            boolean hasnext = rs.next();
            while (hasnext) {
                tmp.setIdemploye(rs.getInt("idemploye"));
                tmp.setEmp_nom(rs.getString("emp_nom"));
                tmp.setEmp_prenom(rs.getString("emp_prenom"));
                tmp.setEmp_birth(rs.getDate("emp_birth"));
                tmp.setEmp_contact(rs.getString("emp_contact"));
                tmp.setEmp_matr(rs.getString("emp_matr"));
                tmp.setDate_embauche(rs.getDate("date_embauche"));
                tmp.setEmp_cin(rs.getString("emp_cin"));
                i++;
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
        return tmp;
    }

    public int nbrEmp(Connection c) {
        String query = "SELECT count(*) as nbr FROM EMPLOYE";
        BasicsFunctions bf = new BasicsFunctions();
        ResultSet rs = bf.execquery(query, c);
        int nbre = 0;

        try {
            boolean hasnext = rs.next();
            while (hasnext) {
                nbre = rs.getInt("nbr");
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

        return nbre;
    }

    public Employe[] getAllEmploye() {
        String query = "SELECT * FROM EMPLOYE";

        DBConnect db = new DBConnect();
        Connection c = db.getconn();
        BasicsFunctions bf = new BasicsFunctions();

        ResultSet rs = bf.execquery(query, c);

        Employe[] rep = new Employe[nbrEmp(c)];
        int i = 0;
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
                rep[i] = tmp;
                i++;
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

        return rep;
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
    
    

}
