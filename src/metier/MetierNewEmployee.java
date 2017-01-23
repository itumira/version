package metier;

import database.BasicsFunctions;
import database.DBConnect;
import entite.Emp_cat;
import entite.HoraireByEmp;
import exceptions.InvalidExcept;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import panel.NewEmployee;
import personne.Employe;

public class MetierNewEmployee extends Metier {

    NewEmployee ne;

    public MetierNewEmployee() {
    }

    public MetierNewEmployee(NewEmployee ne) {
        this.ne = ne;
    }

    public List<Emp_cat> getListCat() {
        List<Emp_cat> liste = new ArrayList();
        String query = "SELECT * FROM emp_cat";
        DBConnect db = new DBConnect();
        Connection c = db.getconn();
        BasicsFunctions bf = new BasicsFunctions();
        ResultSet rs = bf.execquery(query, c);
        try {
            boolean hasnext = rs.next();
            while (hasnext) {
                Emp_cat empcat = new Emp_cat();
                empcat.setIdempcat(rs.getInt("idempcat"));
                empcat.setCategorie(rs.getString("categorie"));
                liste.add(empcat);
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
        return liste;
    }

    public boolean empExiste(String nom, String prenom, String cin, Date birthday, Date date_embauche, String emp_contact) {
        String query = "SELECT * FROM public.employe "
                + "WHERE employe.emp_nom = '" + nom + "' "
                + "AND employe.emp_prenom = '" + prenom + "' "
                + "AND employe.emp_cin = '" + cin + "' "
                + "AND employe.date_embauche = '" + date_embauche + "' "
                + "AND employe.emp_birth = '" + birthday + "' "
                + "AND employe.emp_contact = '" + emp_contact + "' ";
        DBConnect db = new DBConnect();
        Connection c = db.getconn();
        BasicsFunctions bf = new BasicsFunctions();
        ResultSet rs = bf.execquery(query, c);
        int isa = 0;
        try {
            boolean hasnext = rs.next();
            while (hasnext) {
                isa++;
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
        return isa != 0;
    }

    public int getIdCategorie(String categorie) {
        String query = "";
        DBConnect db = new DBConnect();
        Connection c = db.getconn();
        BasicsFunctions bf = new BasicsFunctions();
        ResultSet rs = bf.execquery(query, c);
        int isa = 0;
        try {
            boolean hasnext = rs.next();
            while (hasnext) {
                isa++;
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
        return isa;
    }

    public void enregistrerNewEmp() throws InvalidExcept {
        String nom = ne.getNom().getText();
        String prenom = ne.getPrenom().getText();
        Date birthday = Date.valueOf(dateSouple(ne.getBirthday().getText()));
        Date date_embauche = Date.valueOf(dateSouple(ne.getDateembauche().getText()));
        String cin = (ne.getCin().getText()).trim();
        String contact = ne.getContact().getText();

        String categorie = (String) ((ne.getListecategorie()).getSelectedItem());

        Time heure_entree = heureSouple(ne.getEntree().getText());
        Time heure_sortie = heureSouple(ne.getSortie().getText());

        HoraireByEmp hbe = new HoraireByEmp();
        hbe.setFidirana(heure_entree);
        hbe.setFiravana(heure_sortie);

        if (!empExiste(nom, prenom, cin, birthday, date_embauche, contact)) {
            Employe emp = new Employe(nom, prenom, cin, birthday, date_embauche, contact);
            BasicsFunctions bf = new BasicsFunctions();
            int a = bf.insertionObjI("employe", emp);
            bf.updateEmployee(setEmpMatr(a), a);
            hbe.setIdemploye(a);
            int b = bf.insertionObjI("horairebyemp", hbe);
        }
    }

    public String setEmpMatr(int id) {
        return "EMP00" + id;
    }

    public void setComboCateg() {
        for (Emp_cat cat : getListCat()) {
            ne.getListecategorie().addItem(cat.getCategorie());
        }
    }
}
