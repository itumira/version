package listener;

import exceptions.InvalidExcept;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.MetierJourFerie;
import panel.PanelJourFerie;

public class JourFerierListener implements ActionListener {

    PanelJourFerie pjf;
    MetierJourFerie mjf;

    public JourFerierListener(PanelJourFerie pjf, MetierJourFerie mjf) {
        this.pjf = pjf;
        this.mjf = mjf;
    }

    public JourFerierListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pjf.getAjouterjourf()) {
            try {
                mjf.insertJourFerie();
            } catch (InvalidExcept ex) {
                
            }
        }
    }
}
