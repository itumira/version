package listener;

import dialog.AugmenteDialog;
import dialog.AvanceDialog;
import dialog.EditionDialog;
import fenetre.AugmenteBox;
import fenetre.AvanceBox;
import fenetre.EditionBox;
import fenetre.Fenetre;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import panel.DiagrammePaie;
import panel.Main;

public class PaieListenerM implements ActionListener{
    Fenetre fenetre;
    public PaieListenerM(){
        
    }
    public PaieListenerM(Fenetre fenetre){
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Avance")) {
            AvanceBox main = new AvanceBox(fenetre, true);
            AvanceDialog ad = new AvanceDialog(main);
            main.setContentPane(ad);
            main.setVisible(true);
        }
        if (e.getActionCommand().equals("Augmentation")) {
            AugmenteBox main = new AugmenteBox(fenetre, true);
            AugmenteDialog ad = new AugmenteDialog(main);
            main.setContentPane(ad);
            main.setVisible(true);
        }
        if (e.getActionCommand().equals("Edition")) {
            EditionBox main = new EditionBox(fenetre, true);
            EditionDialog ed = new EditionDialog(main);
            main.setContentPane(ed);
            main.setVisible(true);
        }
        if (e.getActionCommand().equals("Diagramme des paies")) {
            fenetre.getContentPane().setVisible(false);
            DiagrammePaie main = new DiagrammePaie(fenetre);
            fenetre.setContentPane(main);
        }
        if (e.getActionCommand().equals("< Accueil")) {
            fenetre.getContentPane().setVisible(false);
            Main main = new Main(fenetre);
            fenetre.setContentPane(main);
        }
    }
    
    
}
