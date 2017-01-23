package listener;

import fenetre.Fenetre;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import panel.MainPaie;

public class PaieCommonListener implements ActionListener{
    Fenetre fenetre;
    public PaieCommonListener(){
        
    }
    public PaieCommonListener(Fenetre fenetre){
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("< Accueil")) {
            fenetre.getContentPane().setVisible(false);
            MainPaie main = new MainPaie(fenetre);
            fenetre.setContentPane(main);
        }
    }
    
    
}
