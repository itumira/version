package listener;

import fenetre.Fenetre;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import panel.EnregistrerConge;
import panel.Main;
import panel.ValiderConge;

public class CongeListener implements ActionListener {

    Fenetre fenetre;

    public CongeListener() {

    }

    public CongeListener(Fenetre fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Demande de conge")) {
            fenetre.getContentPane().setVisible(false);
            EnregistrerConge main = new EnregistrerConge(fenetre);
            fenetre.setContentPane(main);
        }
        if (e.getActionCommand().equals("Valider Conge")) {
            fenetre.getContentPane().setVisible(false);
            ValiderConge main = new ValiderConge(fenetre);
            fenetre.setContentPane(main);
        }
        if (e.getActionCommand().equals("< Accueil")) {
            fenetre.getContentPane().setVisible(false);
            Main main = new Main(fenetre);
            fenetre.setContentPane(main);
        }
    }

}
