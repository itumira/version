package listener;

import fenetre.Fenetre;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import panel.MainPointage;

public class PointageCommonListener implements ActionListener {

    Fenetre fenetre;

    public PointageCommonListener() {

    }

    public PointageCommonListener(Fenetre fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("< Accueil")) {
            fenetre.getContentPane().setVisible(false);
            MainPointage main = new MainPointage(fenetre);
            fenetre.setSize(500, 600);
            fenetre.setMaximumSize(new Dimension(500, 600));
            fenetre.setMinimumSize(new Dimension(500, 600));

            fenetre.setContentPane(main);
        }
    }

}
