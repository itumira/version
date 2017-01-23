package metier;

import database.BasicsFunctions;
import database.DBConnect;
import entite.Emp_cat;
import entite.Entrer;
import exceptions.InvalidExcept;
import java.awt.Dimension;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import panel.EntrerMulti;
import personne.Employe;

public class MetierEntrerMulti extends MetierPointer {

    EntrerMulti entrermulti;

    public MetierEntrerMulti() {
    }

    public MetierEntrerMulti(EntrerMulti entrermulti) {
        this.entrermulti = entrermulti;
    }

    public Employe getEmployee(int idemploye) {
        Employe employe = new Employe();
        String query = "SELECT * FROM employe WHERE idemploye=" + idemploye;
        DBConnect db = new DBConnect();
        Connection c = db.getconn();
        BasicsFunctions bf = new BasicsFunctions();
        ResultSet rs = bf.execquery(query, c);
        try {
            boolean hasnext = rs.next();
            while (hasnext) {

                employe.setIdemploye(rs.getInt("idemploye"));
                employe.setEmp_nom(rs.getString("emp_nom"));
                employe.setEmp_prenom(rs.getString("emp_prenom"));
                employe.setEmp_birth(rs.getDate("emp_birth"));
                employe.setEmp_cin(rs.getString("emp_cin"));
                employe.setEmp_contact(rs.getString("emp_contact"));
                employe.setEmp_matr(rs.getString("emp_matr"));
                employe.setDate_embauche(rs.getDate("date_embauche"));
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
        return employe;
    }

    /**
     * Avoir la liste de toutes les catégories d'employés.
     *
     * @return
     */
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

    public List<Employe> getAllEmployee() {
        List<Employe> liste = new ArrayList();
        String query = "SELECT * FROM employe";
        DBConnect db = new DBConnect();
        Connection c = db.getconn();
        BasicsFunctions bf = new BasicsFunctions();
        ResultSet rs = bf.execquery(query, c);
        try {
            boolean hasnext = rs.next();
            while (hasnext) {
                Employe employe = new Employe();
                employe.setIdemploye(rs.getInt("idemploye"));
                employe.setEmp_nom(rs.getString("emp_nom"));
                employe.setEmp_prenom(rs.getString("emp_prenom"));
                employe.setEmp_birth(rs.getDate("emp_birth"));
                employe.setEmp_cin(rs.getString("emp_cin"));
                employe.setEmp_contact(rs.getString("emp_contact"));
                employe.setEmp_matr(rs.getString("emp_matr"));
                employe.setDate_embauche(rs.getDate("date_embauche"));
                liste.add(employe);
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

    /**
     * Avoir la liste de tous les employés qui appartiennent à une catégorie.
     *
     * @param categorie
     * @return
     */
    public List<Employe> getAllEmployeeByCat(String categorie) {
        List<Employe> liste = new ArrayList();
        String query = "SELECT * FROM empcateg WHERE categorie='" + categorie + "'";
        DBConnect db = new DBConnect();
        Connection c = db.getconn();
        BasicsFunctions bf = new BasicsFunctions();
        ResultSet rs = bf.execquery(query, c);
        try {
            boolean hasnext = rs.next();
            while (hasnext) {
                Employe employe = new Employe();
                employe.setIdemploye(rs.getInt("idemploye"));
                employe.setEmp_nom(rs.getString("emp_nom"));
                employe.setEmp_prenom(rs.getString("emp_prenom"));
                employe.setEmp_birth(rs.getDate("emp_birth"));
                employe.setEmp_cin(rs.getString("emp_cin"));
                employe.setEmp_contact(rs.getString("emp_contact"));
                employe.setEmp_matr(rs.getString("emp_matr"));
                employe.setDate_embauche(rs.getDate("date_embauche"));
                liste.add(employe);
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

    /**
     * Avoir la liste des entrées saisies par ligne, par employé.
     *
     * @return
     * @throws InvalidExcept
     */
    public List<List<Entrer>> getListeEntrer() throws InvalidExcept {
        List<List<Entrer>> listegrand = new ArrayList();

        DefaultTableModel dtm = (DefaultTableModel) entrermulti.getTablesemaine().getModel();

        int nbr = dtm.getRowCount();
        int nbre = dtm.getColumnCount();
        DBConnect db = new DBConnect();
        Connection c = db.getconn();

        for (int i = 0; i < nbr; i++) {
            System.out.println("ligne n° : " + (i + 1));
            List<Entrer> liste = new ArrayList();
            Date dateinit = Date.valueOf(dateSouple(entrermulti.getDatesemaine().getText()));
            long datelong = dateinit.getTime();

            int idemp = Integer.parseInt(((String) dtm.getValueAt(i, 0)).split("/")[0]);
            for (int j = 2; j < nbre - 1; j = j + 2) {
                System.out.println("(" + i + "," + j + ")");
                Entrer entrer = new Entrer();
                entrer.setIdemploye(idemp);
                entrer.setDateentrer(new Date(datelong));
                if (dtm.getValueAt(i, j + 1) == null) {
                    System.out.println("if (dtm.getValueAt(i, j + 1) == null)");
                    if (dtm.getValueAt(i, j) != null) {
                        entrer.setHeureentree((String) dtm.getValueAt(i, j));
                        liste.add(entrer);
                    }
                }
                if (dtm.getValueAt(i, j + 1) != null) {
                    System.out.println("if (dtm.getValueAt(i, j + 1) != null)");
                    Employe empl = new Employe();
                    empl.setIdemploye(idemp);
                    Time time = (empl.getHoraire(c)).getFidirana();
                    long timelong = time.getTime();
                    Time timeretard = heureSouple((String) dtm.getValueAt(i, j + 1));
                    long timeretardlong = timeretard.getTime();
                    entrer.setHeureentree(new Time(timelong + timeretardlong + 10800000));
                    liste.add(entrer);
                }
                datelong = datelong + 86400000;
            }
            if (!liste.isEmpty()) {
                listegrand.add(liste);
            }
        }

        return listegrand;
    }

    public List<Entrer> getListeTsotraEntrer() throws InvalidExcept {
        List<List<Entrer>> listetaloha = getListeEntrer();
        List<Entrer> liste = new ArrayList();
        for (List<Entrer> ll : listetaloha) {
            int a = 0;
            for (Entrer e : ll) {
                liste.add(e);
                a = e.getIdemploye();
            }
        }
        return liste;
    }

    public void faireentrertous() throws InvalidExcept {
        List<Entrer> liste = new ArrayList();
        liste = getListeTsotraEntrer();
        DBConnect db = new DBConnect();
        Connection c = db.getconn();
        for (Entrer e : liste) {
            entrer(getEmployee(e.getIdemploye()), String.valueOf(e.getDateentrer()), e.getHeureentree(), c);
        }
    }

    public Time getLastEntree(Date datejour, int idemploye) {
        String query = "SELECT * FROM entrer "
                + "WHERE dateentrer='" + datejour + "' "
                + "and idemploye=" + idemploye + " "
                + "ORDER BY heureentree DESC LIMIT 1";
        System.out.println(query);
        DBConnect db = new DBConnect();
        Connection c = db.getconn();
        BasicsFunctions bf = new BasicsFunctions();
        ResultSet rs = bf.execquery(query, c);
        Time reponse = null;
        try {
            boolean hasnext = rs.next();
            while (hasnext) {
                reponse = rs.getTime("heureentree");
                System.out.println(reponse);
                hasnext = rs.next();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return reponse;
    }

    /*/ AFFICHAGE /*/
    /**
     * Manisy an'ireo catégories ao amin'ilay combobox.
     */
    public void setComboCateg() {
        entrermulti.getCombocat().addItem("Tous");
        for (Emp_cat cat : getListCat()) {
            entrermulti.getCombocat().addItem(cat.getCategorie());
        }
    }

    public void setTableMulti() {
        DefaultTableModel model = new DefaultTableModel();
        int i = 0;
        String datee = "";
        try {
            datee = dateSouple(entrermulti.getDatesemaine().getText());

            Date dater = Date.valueOf(datee);
            List<Employe> lister;
            if (((String) entrermulti.getCombocat().getSelectedItem()).equals("Tous")) {
                lister = getAllEmployee();
            } else {
                lister = getAllEmployeeByCat((String) entrermulti.getCombocat().getSelectedItem());
            }
            int nbre = lister.size();
            Object[] matr = new Object[nbre];
            Object[] nomprenoms = new Object[nbre];
            Object[] lundi = new Object[nbre];
            Object[] lundi2 = new Object[nbre];
            Object[] mardi = new Object[nbre];
            Object[] mardi2 = new Object[nbre];
            Object[] mercredi = new Object[nbre];
            Object[] mercredi2 = new Object[nbre];
            Object[] jeudi = new Object[nbre];
            Object[] jeudi2 = new Object[nbre];
            Object[] vendredi = new Object[nbre];
            Object[] vendredi2 = new Object[nbre];
            Object[] samedi = new Object[nbre];
            Object[] samedi2 = new Object[nbre];
            Object[] dimanche = new Object[nbre];
            Object[] dimanche2 = new Object[nbre];
            Object[] retardsemaine = new Object[nbre];

            for (Employe e : lister) {
                matr[i] = (Object) e.getIdemploye() + "/" + e.getEmp_matr();
                nomprenoms[i] = (Object) e.getEmp_nom() + " " + e.getEmp_prenom();

                i++;
            }

            model.addColumn("Matr", matr);
            model.addColumn("Nom - Prenoms", nomprenoms);
            model.addColumn("Lundi", lundi);
            model.addColumn("retard", lundi2);
            model.addColumn("Mardi", mardi);
            model.addColumn("retard", mardi2);
            model.addColumn("Mercredi", mercredi);
            model.addColumn("retard", mercredi2);
            model.addColumn("Jeudi", jeudi);
            model.addColumn("retard", jeudi2);
            model.addColumn("Vendredi", vendredi);
            model.addColumn("retard", vendredi2);
            model.addColumn("Samedi", samedi);
            model.addColumn("retard", samedi2);
            model.addColumn("Dimanche", dimanche);
            model.addColumn("retard", dimanche2);
            model.addColumn("Retard", retardsemaine);

            entrermulti.getTablesemaine().setModel(model);

            entrermulti.getFenetre().setSize(1200, 600);
            entrermulti.getFenetre().setMaximumSize(new Dimension(1200, 600));
            entrermulti.getFenetre().setMinimumSize(new Dimension(1200, 600));
        } catch (InvalidExcept ex) {
            JOptionPane.showMessageDialog(new Frame(), "Erreur dans la date", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void soumettre() throws InvalidExcept {
        String date = entrermulti.getDatesemaine().getText();
        if (!date.equals("")) {
            Date daty = null;
            try {
                daty = Date.valueOf(dateSouple(date));
            } catch (InvalidExcept ie) {
                daty = null;
            }
            if (daty != null) {
                if (isLundi(daty)) {
                    setTableMulti();
                } else {
                    throw new InvalidExcept("Veuillez saisir le premier jour de la semaine.", "Erreur");
                }
            }
        } else {
            throw new InvalidExcept("Veuillez remplir le champ date! ", "Erreur");
        }
    }

    public Entrer getEntreeByEmpByDay(Employe emp, String day) {
        Entrer entrer = new Entrer();

        String query = "SELECT * FROM entrer WHERE idemploye=" + emp.getIdemploye() + " and dateentrer='" + day + "'";
        DBConnect db = new DBConnect();
        Connection c = db.getconn();
        BasicsFunctions bf = new BasicsFunctions();
        ResultSet rs = bf.execquery(query, c);
        try {
            boolean hasnext = rs.next();
            while (hasnext) {
                entrer.setIdentrer(rs.getInt("identrer"));
                entrer.setIdemploye(rs.getInt("idemploye"));
                entrer.setDateentrer(rs.getDate("dateentrer"));
                entrer.setHeureentree(rs.getTime("heureentree"));
                hasnext = rs.next();
            }
            return entrer;
        } catch (SQLException e) {
            return null;
        }
    }

    /*/ Vendredi 13 Janvier 2017 /*/
    public void setRetardSemaine() throws InvalidExcept {
        DefaultTableModel dtm = (DefaultTableModel) entrermulti.getTablesemaine().getModel();
        Date dateinit = Date.valueOf(dateSouple(entrermulti.getDatesemaine().getText()));
        long datelong = dateinit.getTime();
        long findesemaine = datelong + 518400000;
        int nbr = dtm.getRowCount();
        int nbre = dtm.getColumnCount();
        for (int i = 0; i < nbr; i++) {
            Time res = calculerRetardSemaine(dateinit, new Date(findesemaine), Integer.parseInt(((String) dtm.getValueAt(i, 0)).split("/")[0]));
            String restring = res.toString();
            dtm.setValueAt(restring, i, nbre - 1);
            System.out.println("Retard semaine = " + res);
        }
    }

    public void setRetardJour() throws InvalidExcept {
        DefaultTableModel dtm = (DefaultTableModel) entrermulti.getTablesemaine().getModel();
        Date dateinit = Date.valueOf(dateSouple(entrermulti.getDatesemaine().getText()));

        int nbr = dtm.getRowCount();
        int nbre = dtm.getColumnCount();
        for (int i = 0; i < nbr; i++) {
            long datelong = dateinit.getTime();
            long findesemaine = datelong + 518400000;
            for (int j = 3; j < nbre - 1; j = j + 2) {
                if (dtm.getValueAt(i, j - 1) != null) {
                    Time res = calculerRetardJour(new Date(datelong), Integer.parseInt(((String) dtm.getValueAt(i, 0)).split("/")[0]));
                    String restring = res.toString();
                    dtm.setValueAt(restring, i, j);
                    Time repon = getLastEntree(new Date(datelong), Integer.parseInt(((String) dtm.getValueAt(i, 0)).split("/")[0]));
                    String reponstring = repon.toString();
                    dtm.setValueAt(reponstring, i, j - 1);
                    System.out.println("Retard semaine = " + res);
                } else if (dtm.getValueAt(i, j - 1) == null) {
                    if (dtm.getValueAt(i, j) != null) {
                        Time repon = getLastEntree(new Date(datelong), Integer.parseInt(((String) dtm.getValueAt(i, 0)).split("/")[0]));
                        String reponstring = repon.toString();
                        dtm.setValueAt(reponstring, i, j - 1);
                        Time res = calculerRetardJour(new Date(datelong), Integer.parseInt(((String) dtm.getValueAt(i, 0)).split("/")[0]));
                        String restring = res.toString();
                        dtm.setValueAt(restring, i, j);
                        System.out.println("Retard semaine = " + repon);
                    }
                }
                datelong = datelong + 86400000;
            }

        }
    }

    public Time calculerRetardSemaine(Date datedebut, Date datefin, int idemploye) {
        String query = "SELECT sum(heure_retard) as somme "
                + "FROM retardentrer "
                + "WHERE dateentrer>='" + datedebut + "' "
                + "AND dateentrer<='" + datefin + "' and idemploye=" + idemploye + " GROUP BY idemploye";
        System.out.println(query);
        DBConnect db = new DBConnect();
        Connection c = db.getconn();
        BasicsFunctions bf = new BasicsFunctions();
        ResultSet rs = bf.execquery(query, c);
        Time reponse = new Time(0);
        try {
            boolean hasnext = rs.next();
            while (hasnext) {
                reponse = rs.getTime("somme");
                System.out.println(reponse);
                hasnext = rs.next();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return reponse;
    }

    public Time calculerRetardJour(Date datejour, int idemploye) {
        String query = "SELECT sum(heure_retard) as somme "
                + "FROM retardentrer "
                + "WHERE dateentrer='" + datejour + "' and idemploye=" + idemploye + " GROUP BY idemploye";
        System.out.println(query);
        DBConnect db = new DBConnect();
        Connection c = db.getconn();
        BasicsFunctions bf = new BasicsFunctions();
        ResultSet rs = bf.execquery(query, c);
        Time reponse = new Time(0);
        try {
            boolean hasnext = rs.next();
            while (hasnext) {
                reponse = rs.getTime("somme");
                System.out.println(reponse);
                hasnext = rs.next();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return reponse;
    }

    public void setRetardDivise() throws InvalidExcept {
        DefaultTableModel dtm = (DefaultTableModel) entrermulti.getTablesemaine().getModel();
        int nbr = dtm.getRowCount();
        int nbre = dtm.getColumnCount();
        for (int i = 0; i < nbr; i++) {
            if (dtm.getValueAt(i, nbre - 1) != null) {
                Time temps = heureSouple((String) dtm.getValueAt(i, nbre - 1));
                long fotoana = temps.getTime();
                int c = new Time(fotoana).getSeconds() + (new Time(fotoana).getMinutes() * 60) + (new Time(fotoana).getHours() * 3600);
                Time t = new Time(((c * 1000) / 5) - 10800000);
                String rep = (t.toString()).split(":")[0] + ":" + (t.toString()).split(":")[1];
                for (int j = 3; j < nbre - 5; j = j + 2) {
                    dtm.setValueAt(rep, i, j);
                }
            }
        }
    }
}
