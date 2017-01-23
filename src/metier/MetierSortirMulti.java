package metier;

import database.DBConnect;
import entite.Emp_cat;
import entite.Exit;
import exceptions.InvalidExcept;
import java.awt.Dimension;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import panel.SortirMulti;
import personne.Employe;

public class MetierSortirMulti extends MetierEntrerMulti {

    SortirMulti sortirmulti;

    public MetierSortirMulti() {
    }

    public MetierSortirMulti(SortirMulti sortirmulti) {
        this.sortirmulti = sortirmulti;
    }

    public List<List<Exit>> getListeSortir() throws InvalidExcept {
        List<List<Exit>> listegrand = new ArrayList();

        DefaultTableModel dtm = (DefaultTableModel) sortirmulti.getTablesemaine().getModel();

        int nbr = dtm.getRowCount();
        for (int i = 0; i < nbr; i++) {
            List<Exit> liste = new ArrayList();
            Date dateinit = Date.valueOf(dateSouple(sortirmulti.getDatesemaine().getText()));
            long datelong = dateinit.getTime();
            for (int j = 2; j < 9; j++) {
                if (dtm.getValueAt(i, j) != null) {
                    Exit exit = new Exit();
                    exit.setIdemploye(Integer.parseInt(((String) dtm.getValueAt(i, 0)).split("/")[0]));
                    exit.setDate((new Date(datelong)).toString());
                    exit.setHeure((String) dtm.getValueAt(i, j));
                    liste.add(exit);
                }
                datelong = datelong + 86400000;
            }
            listegrand.add(liste);
        }
        System.out.println(listegrand);
        return listegrand;
    }

    public List<Exit> getListeTsotraSortir() throws InvalidExcept {
        List<List<Exit>> listetaloha = getListeSortir();
        List<Exit> liste = new ArrayList();
        for (List<Exit> ll : listetaloha) {
            for (Exit e : ll) {
                liste.add(e);
            }
        }
        return liste;
    }

    public void fairesortirtous() {
        List<Exit> liste = new ArrayList();
        try {
            liste = getListeTsotraSortir();
        } catch (InvalidExcept ex) {

        }
        DBConnect db = new DBConnect();
        Connection c = db.getconn();
        for (Exit e : liste) {
            try {
                sortir(getEmployee(e.getIdemploye()), (e.getDate()), heureSouple(e.getHeure()), c);
            } catch (InvalidExcept ex) {
            }
        }
    }

    /*/ AFFICHAGE /*/
    @Override
    public void setComboCateg() {
        sortirmulti.getCombocat().addItem("Tous");
        for (Emp_cat cat : getListCat()) {
            sortirmulti.getCombocat().addItem(cat.getCategorie());
        }
    }
    @Override
    public void setTableMulti() {
        DefaultTableModel model = new DefaultTableModel();
        int i = 0;
        String datee = "";
        try {
            datee = dateSouple(sortirmulti.getDatesemaine().getText());

            Date dater = Date.valueOf(datee);
            List<Employe> lister;
            if (((String) sortirmulti.getCombocat().getSelectedItem()).equals("Tous")) {
                lister = getAllEmployee();
            } else {
                lister = getAllEmployeeByCat((String) sortirmulti.getCombocat().getSelectedItem());
            }
            int nbre = lister.size();
            Object[] matr = new Object[nbre];
            Object[] nomprenoms = new Object[nbre];
            Object[] lundi = new Object[nbre];
            Object[] mardi = new Object[nbre];
            Object[] mercredi = new Object[nbre];
            Object[] jeudi = new Object[nbre];
            Object[] vendredi = new Object[nbre];
            Object[] samedi = new Object[nbre];
            Object[] dimanche = new Object[nbre];
            for (Employe e : lister) {
                matr[i] = (Object) e.getIdemploye() + "/" + e.getEmp_matr();
                nomprenoms[i] = (Object) e.getEmp_nom() + " " + e.getEmp_prenom();
                i++;
            }

            model.addColumn("Matr", matr);
            model.addColumn("Nom - Prenoms", nomprenoms);
            model.addColumn("Lundi", lundi);
            model.addColumn("Mardi", mardi);
            model.addColumn("Mercredi", mercredi);
            model.addColumn("Jeudi", jeudi);
            model.addColumn("Vendredi", vendredi);
            model.addColumn("Samedi", samedi);
            model.addColumn("Dimanche", dimanche);

            sortirmulti.getTablesemaine().setModel(model);

            sortirmulti.getFenetre().setSize(700, 600);
            sortirmulti.getFenetre().setMaximumSize(new Dimension(700, 600));
            sortirmulti.getFenetre().setMinimumSize(new Dimension(700, 600));
        } catch (InvalidExcept ex) {
            JOptionPane.showMessageDialog(new Frame(), "Erreur dans la date", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void soumettre() throws InvalidExcept {
        String date = sortirmulti.getDatesemaine().getText();
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
}
