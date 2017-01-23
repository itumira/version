package panel;

import database.DBConnect;
import fenetre.Fenetre;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import listener.PaieCommonListener;
import paie.Editer;
import personne.Employe;

public class Edition extends javax.swing.JPanel {
    DefaultTableModel model;
    Object[] emp_identif;
    Object[] gain;
    Object[] retenu;
    Object[] netapayer;
    Object[] nbre;
    
    Fenetre fenetre;
    
    public Edition(Fenetre fenetre) {
        this.fenetre = fenetre;
        initComponents();
        
        Connection c = new DBConnect().getconn();
        
        model = new DefaultTableModel();
        jTable1.setModel(model);
        
        Editer e = new Editer();
        int nbrEmp = e.nbrEmp(c);
        
        emp_identif = new Object[nbrEmp];
        gain = new Object[nbrEmp];
        retenu = new Object[nbrEmp];
        netapayer = new Object[nbrEmp];
        
        Employe[] listeEmp = e.getAllEmploye();
        
        nbre = new Object[nbrEmp];
        int i = 0;
        for (Employe emp : listeEmp) {
            nbre[i] = (Object) (i + 1);
            emp_identif[i] = (Object)emp.getEmp_matr();
            i++;
            //gain[i] = (Object)ef.getAllGains(emp.getIdemploye(),date);
            //retenu[i] = (Object)ef.getAllRetenus(emp.getIdemploye(),date);
            //netapayer[i] = (Object)ef.getNetAPayer(emp.getIdemploye(),date);
        }
        
        
        accueil.addActionListener(new PaieCommonListener(fenetre));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        welcome = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        accueil = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(490, 590));
        setMinimumSize(new java.awt.Dimension(490, 590));

        welcome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        welcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcome.setText("Edition");

        accueil.setBackground(new java.awt.Color(204, 204, 204));
        accueil.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        accueil.setText("< Accueil");

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(accueil, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(welcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(accueil, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accueil;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel welcome;
    // End of variables declaration//GEN-END:variables
}
