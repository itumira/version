package listener;

import dialog.ChargerDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import metier.MetierModele;

public class ChargerModeleListener implements ActionListener {

    ChargerDialog chargerdialog;
    MetierModele metiermodele;

    public ChargerModeleListener() {

    }

    public ChargerModeleListener(ChargerDialog chargerdialog) {
        this.chargerdialog = chargerdialog;
        this.metiermodele = new MetierModele(chargerdialog);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chargerdialog.getCharger()) {
            metiermodele.setModeleTableau();
        }
    }

}
