package listener;

import dialog.ChargerDialog;
import dialog.EnregistrerDialog;
import exceptions.InvalidExcept;
import fenetre.ChargerBox;
import fenetre.EnregistrerBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import metier.MetierModele;
import panel.EntrerMulti;

public class ModeleListener implements ActionListener {

    EnregistrerBox eb;
    EnregistrerDialog enregistrerdialog;

    EntrerMulti entrermulti;

    MetierModele metiermodele;

    public ModeleListener(EnregistrerDialog enregistrerdialog, EntrerMulti entrermulti) {
        this.enregistrerdialog = enregistrerdialog;
        this.entrermulti = entrermulti;
        this.metiermodele = new MetierModele(entrermulti, enregistrerdialog);
    }

    public ModeleListener(EntrerMulti entrermulti) {
        this.entrermulti = entrermulti;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enregistrerdialog.getEnregistrermodele()) {
            try {
                metiermodele.traiterEnregistrerModele();
//                System.out.println(metiermodele.getDetModele());
            } catch (InvalidExcept ex) {

            }
        }
    }

}
