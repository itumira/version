package metier;

import database.BasicsFunctions;
import database.DBConnect;
import dialog.ChargerDialog;
import dialog.EnregistrerDialog;
import entite.DetModele;
import entite.JourFerie;
import entite.Modele;
import exceptions.InvalidExcept;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
import panel.EntrerMulti;

public class MetierModele extends MetierEntrerMulti {

    EntrerMulti entrermulti;
    EnregistrerDialog enregistrerdialog;

    ChargerDialog chargerdialog;

    public MetierModele() {
    }

    public MetierModele(EnregistrerDialog enregistrerdialog) {
        this.enregistrerdialog = enregistrerdialog;
    }

    public MetierModele(ChargerDialog chargerdialog) {
        this.chargerdialog = chargerdialog;
    }

    public MetierModele(EntrerMulti entrermulti) {
        this.entrermulti = entrermulti;
    }

    public MetierModele(EntrerMulti entrermulti, EnregistrerDialog enregistrerdialog) {
        this.entrermulti = entrermulti;
        this.enregistrerdialog = enregistrerdialog;
    }

    public List<Modele> getListeModele() throws InvalidExcept {
        List<Modele> listegrand = new ArrayList();
        DefaultTableModel dtm = (DefaultTableModel) entrermulti.getTablesemaine().getModel();
        int nbr = dtm.getRowCount();
        DBConnect db = new DBConnect();

        DetModele detmodele = getDetModele();

        BasicsFunctions bf = new BasicsFunctions();
        int iddetmodele = bf.insertionObjI("detmodele", detmodele);
        System.out.println(iddetmodele);
        for (int i = 0; i < nbr; i++) {
            Modele modele = new Modele();
            int idemp = Integer.parseInt(((String) dtm.getValueAt(i, 0)).split("/")[0]);
            modele.setIdemploye(idemp);
            modele.setIddetmodele(iddetmodele);
            if ((String.valueOf(dtm.getValueAt(i, 2))).compareTo("null") == 0) {
                modele.setLundi("");
            } else if ((String.valueOf(dtm.getValueAt(i, 2))).compareTo("null") != 0) {
                modele.setLundi((String) dtm.getValueAt(i, 2));
            }
            if ((String.valueOf(dtm.getValueAt(i, 4))).compareTo("null") == 0) {
                modele.setMardi("");
            } else if ((String.valueOf(dtm.getValueAt(i, 4))).compareTo("null") != 0) {
                modele.setMardi((String) dtm.getValueAt(i, 4));
            }
            if ((String.valueOf(dtm.getValueAt(i, 6))).compareTo("null") == 0) {
                modele.setMercredi("");
            } else if ((String.valueOf(dtm.getValueAt(i, 6))).compareTo("null") != 0) {
                modele.setMercredi((String) dtm.getValueAt(i, 6));
            }
            if ((String.valueOf(dtm.getValueAt(i, 8))).compareTo("null") == 0) {
                modele.setJeudi("");
            } else if ((String.valueOf(dtm.getValueAt(i, 8))).compareTo("null") != 0) {
                modele.setJeudi((String) dtm.getValueAt(i, 8));
            }
            if ((String.valueOf(dtm.getValueAt(i, 10))).compareTo("null") == 0) {
                modele.setVendredi("");
            } else if ((String.valueOf(dtm.getValueAt(i, 10))).compareTo("null") != 0) {
                modele.setVendredi((String) dtm.getValueAt(i, 10));
            }
            if ((String.valueOf(dtm.getValueAt(i, 12))).compareTo("null") == 0) {
                modele.setSamedi("");
            } else if ((String.valueOf(dtm.getValueAt(i, 12))).compareTo("null") != 0) {
                modele.setSamedi((String) dtm.getValueAt(i, 12));
            }
            if ((String.valueOf(dtm.getValueAt(i, 14))).compareTo("null") == 0) {
                modele.setDimanche("");
            } else if ((String.valueOf(dtm.getValueAt(i, 14))).compareTo("null") != 0) {
                modele.setDimanche((String) dtm.getValueAt(i, 14));
            }
            listegrand.add(modele);
        }
        return listegrand;
    }

    public DetModele getDetModele() throws InvalidExcept {
        return new DetModele(enregistrerdialog.getNommodele().getText(), Date.valueOf(dateSouple(entrermulti.getDatesemaine().getText())));
    }

    public void traiterEnregistrerModele() throws InvalidExcept {
        List<Modele> listemodele = getListeModele();
        BasicsFunctions bf = new BasicsFunctions();
        for (Modele modele : listemodele) {
            bf.insertionObj("modele", modele);
        }
    }

    /*/ CHARGEMENT /*/
    public List<DetModele> getAllDetModele() {
        List<DetModele> liste = new ArrayList();

        String query = "SELECT * FROM detmodele";

        DBConnect db = new DBConnect();
        Connection c = db.getconn();
        BasicsFunctions bf = new BasicsFunctions();
        ResultSet rs = bf.execquery(query, c);
        try {
            boolean hasnext = rs.next();
            while (hasnext) {
                DetModele tmp = new DetModele();
                tmp.setIddetmodele(rs.getInt("iddetmodele"));
                tmp.setNommodele(rs.getString("nommodele"));
                tmp.setSemainemodele(rs.getDate("semainemodele"));
                liste.add(tmp);
                hasnext = rs.next();
            }
        } catch (SQLException e) {
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
            }
        }

        return liste;
    }
// Methodes

    public List<Modele> getModeleByIdDetModele() {
        int iddetmodele = getIdModeleSelected();
        List<Modele> liste = new ArrayList();
        String query = "SELECT * FROM modele INNER JOIN detmodele"
                + " ON modele.iddetmodele=detmodele.iddetmodele "
                + " WHERE detmodele.iddetmodele=" + iddetmodele;

        DBConnect db = new DBConnect();
        Connection c = db.getconn();
        BasicsFunctions bf = new BasicsFunctions();
        ResultSet rs = bf.execquery(query, c);
        try {
            boolean hasnext = rs.next();
            while (hasnext) {
                Modele modele = new Modele(rs.getInt("idmodele"), rs.getInt("idemploye"), rs.getString("lundi"), rs.getString("mardi"), rs.getString("mercredi"), rs.getString("jeudi"), rs.getString("vendredi"), rs.getString("samedi"), rs.getString("dimanche"), rs.getInt("iddetmodele"));
                liste.add(modele);
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
//  Affichage

    public void setModeleTableau() {
        DefaultTableModel dtm = (DefaultTableModel) chargerdialog.getEntrerMulti().getTablesemaine().getModel();

        List<Modele> listemodele = getModeleByIdDetModele();

        int nbrligne = dtm.getRowCount();
        int nbrcol = dtm.getColumnCount();

        int k = 0;
        for (int i = 0; i < nbrligne; i++) {
            Modele modele = listemodele.get(k);
            System.out.println(k);
            if ((((String) dtm.getValueAt(i, 0)).split("/")[0]).compareTo(String.valueOf(modele.getIdemploye())) == 0) {
                dtm.setValueAt(modele.getLundi(), i, 2);
                dtm.setValueAt(modele.getMardi(), i, 4);
                dtm.setValueAt(modele.getMercredi(), i, 6);
                dtm.setValueAt(modele.getJeudi(), i, 8);
                dtm.setValueAt(modele.getVendredi(), i, 10);
                dtm.setValueAt(modele.getSamedi(), i, 12);
                dtm.setValueAt(modele.getDimanche(), i, 14);
                k++;
            }

        }
    }

    public void setListe() {
        DefaultListModel model = new DefaultListModel();
        for (DetModele detmodele : getAllDetModele()) {
            model.addElement(detmodele.getIddetmodele() + " : " + detmodele.getNommodele());
        }
        chargerdialog.getListemodele().setModel(model);
    }

    public int getIdModeleSelected() {
        int a = chargerdialog.getListemodele().getSelectedIndex();
        ListModel lm = chargerdialog.getListemodele().getModel();
        Object o = ((DefaultListModel) lm).get(a);
        return Integer.parseInt(((String) o).split(" : ")[0]);
    }
}
