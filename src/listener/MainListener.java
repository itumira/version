package listener;

import fenetre.Fenetre;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import panel.MainConge;
import panel.MainPaie;
import panel.MainPointage;

public class MainListener implements ActionListener{
    Fenetre fenetre;
    public MainListener(){
        
    }
    public MainListener(Fenetre fenetre){
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Paie")) {
            fenetre.getContentPane().setVisible(false);
            MainPaie main = new MainPaie(fenetre);
            fenetre.setContentPane(main);
        }
        if (e.getActionCommand().equals("Pointage")) {
            fenetre.getContentPane().setVisible(false);
            MainPointage main = new MainPointage(fenetre);
            fenetre.setContentPane(main);
        }
        if (e.getActionCommand().equals("Conge")) {
            fenetre.getContentPane().setVisible(false);
            MainConge main = new MainConge(fenetre);
            fenetre.setContentPane(main);
        }
    }
    
    
}
