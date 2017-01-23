package listener;

import dialog.ChargerDialog;
import fenetre.ChargerBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import metier.MetierModele;
import panel.EntrerMulti;

public class ModeleListener2 implements ActionListener {

    EntrerMulti entrermulti;
    MetierModele metiermodele;

    public ModeleListener2(MetierModele metiermodele) {
        this.metiermodele = metiermodele;
    }

    public ModeleListener2(EntrerMulti entrermulti) {
        this.entrermulti = entrermulti;
        this.metiermodele = new MetierModele(entrermulti);
    }

    public ModeleListener2(EntrerMulti entrermulti, MetierModele metiermodele) {
        this.entrermulti = entrermulti;
        this.metiermodele = metiermodele;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == entrermulti.getChargement()) {
            ChargerBox main = new ChargerBox(entrermulti.getFenetre(), true);
            ChargerDialog ed = new ChargerDialog(main);
            MetierModele nme = new MetierModele(ed);
            nme.setListe();
            main.setContentPane(ed);
            main.setVisible(true);
        }
    }
}
