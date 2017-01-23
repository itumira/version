package listener;

import exceptions.InvalidExcept;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import metier.MetierSortirMulti;
import panel.SortirMulti;

public class PointerMultiListener2 implements ActionListener {

    SortirMulti sm;
    MetierSortirMulti msm;

    public PointerMultiListener2() {
    }

    public PointerMultiListener2(SortirMulti sm) {
        this.sm = sm;
        msm = new MetierSortirMulti(sm);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sm.getVoirpointage()) {
            try {
                msm.soumettre();
            } catch (InvalidExcept ex) {

            }
        }
        if (e.getSource() == sm.getEnregistrerpointage()) {
            msm.fairesortirtous();
        }

    }
}
