package listener;

import fenetre.Fenetre;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import metier.MetierPointer;
import panel.DetailsEmploye;
import panel.EntrerMulti;
import panel.PanelJourFerie;
import panel.Main;
import panel.Pointer;
import panel.SortirMulti;

public class PointageListener implements ActionListener {

    Pointer pointer;
    Fenetre fenetre;

    MetierPointer mp;

    public PointageListener() {

    }

    public PointageListener(Fenetre fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Gerer Jour Ferier")) {
            fenetre.getContentPane().setVisible(false);
            PanelJourFerie main = new PanelJourFerie(fenetre);
            fenetre.setContentPane(main);
        }
        if (e.getActionCommand().equals("Details")) {
            fenetre.getContentPane().setVisible(false);
            DetailsEmploye main = new DetailsEmploye(fenetre);
            fenetre.setContentPane(main);
        }
        if (e.getActionCommand().equals("< Accueil")) {
            fenetre.getContentPane().setVisible(false);
            Main main = new Main(fenetre);
            fenetre.setContentPane(main);
            fenetre.setSize(500, 600);
            fenetre.setMaximumSize(new Dimension(500, 600));
            fenetre.setMinimumSize(new Dimension(500, 600));

        }
        if (e.getActionCommand().equals("Entrer")) {
            fenetre.getContentPane().setVisible(false);
            EntrerMulti main = new EntrerMulti(fenetre);
            fenetre.setContentPane(main);
        }
        if (e.getActionCommand().equals("Sortir")) {
            fenetre.getContentPane().setVisible(false);
            SortirMulti main = new SortirMulti(fenetre);
            fenetre.setContentPane(main);
        }
        if (e.getActionCommand().equals("Pointer")) {
            fenetre.getContentPane().setVisible(false);
            Pointer main = new Pointer(fenetre);
            fenetre.setContentPane(main);
        }

    }

}
