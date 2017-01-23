package personne;

import database.BasicsFunctions;
import entite.HoraireByEmp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employe {

    int idemploye;
    String emp_nom;
    String emp_prenom;
    String emp_cin;
    String emp_matr;
    Date emp_birth;
    Date date_embauche;
    String emp_contact;

    @Override
    public String toString() {
        return "default,'" + emp_nom + "','" + emp_prenom + "','" + emp_cin + "',null,'" + emp_birth + "','" + date_embauche + "','" + emp_contact + "'";
    }

    public Employe() {
    }

    public Employe(String emp_nom, String emp_prenom, String emp_cin, Date emp_birth, Date date_embauche, String emp_contact) {
        this.emp_nom = emp_nom;
        this.emp_prenom = emp_prenom;
        this.emp_cin = emp_cin;
        this.emp_birth = emp_birth;
        this.date_embauche = date_embauche;
        this.emp_contact = emp_contact;
    }

    public Employe(String emp_nom, String emp_prenom, String emp_cin, String emp_matr, Date emp_birth, Date date_embauche, String emp_contact) {
        this.emp_nom = emp_nom;
        this.emp_prenom = emp_prenom;
        this.emp_cin = emp_cin;
        this.emp_matr = emp_matr;
        this.emp_birth = emp_birth;
        this.date_embauche = date_embauche;
        this.emp_contact = emp_contact;
    }

    public int getIdemploye() {
        return idemploye;
    }

    public void setIdemploye(int idemploye) {
        this.idemploye = idemploye;
    }

    public String getEmp_nom() {
        return emp_nom;
    }

    public void setEmp_nom(String emp_nom) {
        this.emp_nom = emp_nom;
    }

    public String getEmp_prenom() {
        return emp_prenom;
    }

    public void setEmp_prenom(String emp_prenom) {
        this.emp_prenom = emp_prenom;
    }

    public String getEmp_cin() {
        return emp_cin;
    }

    public void setEmp_cin(String emp_cin) {
        this.emp_cin = emp_cin;
    }

    public String getEmp_matr() {
        return emp_matr;
    }

    public void setEmp_matr(String emp_matr) {
        this.emp_matr = emp_matr;
    }

    public Date getEmp_birth() {
        return emp_birth;
    }

    public void setEmp_birth(Date emp_birth) {
        this.emp_birth = emp_birth;
    }

    public Date getDate_embauche() {
        return date_embauche;
    }

    public void setDate_embauche(Date date_embauche) {
        this.date_embauche = date_embauche;
    }

    public String getEmp_contact() {
        return emp_contact;
    }

    public void setEmp_contact(String emp_contact) {
        this.emp_contact = emp_contact;
    }

    /*/ METHODES /*/
    public HoraireByEmp getHoraire(Connection c) {
        String query = "SELECT * FROM horairebyemp WHERE idemploye=" + this.getIdemploye() + "";
        System.out.println(query);
        BasicsFunctions bf = new BasicsFunctions();

        ResultSet rs = bf.execquery(query, c);
        HoraireByEmp hbe = new HoraireByEmp();
        try {
            boolean hasnext = rs.next();
            while (hasnext) {
                hbe.setIdhoraire(rs.getInt(1));
                hbe.setIdemploye(rs.getInt(2));
                hbe.setFidirana(rs.getTime(3));
                hbe.setFiravana(rs.getTime(4));
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
        return hbe;
    }

    public HoraireByEmp getHoraire(int id, Connection c) {
        String query = "SELECT * FROM horairebyemp WHERE idemploye=" + id + "";
        System.out.println(query);
        BasicsFunctions bf = new BasicsFunctions();

        ResultSet rs = bf.execquery(query, c);
        HoraireByEmp hbe = new HoraireByEmp();
        try {
            boolean hasnext = rs.next();
            while (hasnext) {
                hbe.setIdhoraire(rs.getInt(1));
                hbe.setIdemploye(rs.getInt(2));
                hbe.setFidirana(rs.getTime(3));
                hbe.setFiravana(rs.getTime(4));
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
        return hbe;
    }
}
