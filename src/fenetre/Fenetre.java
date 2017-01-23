package fenetre;

import panel.Main;

public class Fenetre extends javax.swing.JFrame {

    public Fenetre() {
        initComponents();
        setContentPane(new Main(this));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menubar = new javax.swing.JMenuBar();
        fichier = new javax.swing.JMenu();
        quitter = new javax.swing.JMenuItem();
        edition = new javax.swing.JMenu();
        aide = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(500, 600));
        setMinimumSize(new java.awt.Dimension(500, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(500, 600));

        menubar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menubar.setMaximumSize(new java.awt.Dimension(70, 32769));
        menubar.setMinimumSize(new java.awt.Dimension(80, 21));
        menubar.setName(""); // NOI18N
        menubar.setPreferredSize(new java.awt.Dimension(80, 21));

        fichier.setText("Fichier");

        quitter.setText("Quitter");
        fichier.add(quitter);

        menubar.add(fichier);

        edition.setText("Edition");
        menubar.add(edition);

        aide.setText("?");
        menubar.add(aide);

        setJMenuBar(menubar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 579, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Fenetre().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu aide;
    private javax.swing.JMenu edition;
    private javax.swing.JMenu fichier;
    private javax.swing.JMenuBar menubar;
    private javax.swing.JMenuItem quitter;
    // End of variables declaration//GEN-END:variables
}
