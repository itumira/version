package dialog;

import fenetre.AvanceBox;
import fenetre.Fenetre;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import listener.PointageListener;

public class PointerDialog extends javax.swing.JPanel {

    Fenetre fenetre;
    AvanceBox ab;

    public AvanceBox getAb() {
        return ab;
    }

    public PointerDialog(Fenetre fenetre, AvanceBox ab) {
        initComponents();
        this.ab = ab;
        this.fenetre = fenetre;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        matriculefield = new javax.swing.JTextField();
        pointage = new javax.swing.JButton();
        alerte = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(368, 152));
        setMinimumSize(new java.awt.Dimension(368, 152));
        setPreferredSize(new java.awt.Dimension(368, 152));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Matricule Employe :");

        matriculefield.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        pointage.setBackground(new java.awt.Color(204, 204, 204));
        pointage.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pointage.setText("Pointage");

        alerte.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        alerte.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pointage, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addComponent(matriculefield))
                .addGap(45, 45, 45))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(alerte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(matriculefield, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(alerte, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pointage, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
    }// </editor-fold>//GEN-END:initComponents

    public JTextField getMatriculefield() {
        return matriculefield;
    }

    public JButton getPointage() {
        return pointage;
    }

    public JLabel getAlerte() {
        return alerte;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alerte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField matriculefield;
    private javax.swing.JButton pointage;
    // End of variables declaration//GEN-END:variables
}
