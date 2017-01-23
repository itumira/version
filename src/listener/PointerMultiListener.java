package listener;

import dialog.EnregistrerDialog;
import exceptions.InvalidExcept;
import fenetre.EnregistrerBox;
import fenetre.NewEmployeeDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import metier.MetierEntrerMulti;
import metier.MetierModele;
import metier.MetierNewEmployee;
import metier.MetierSortirMulti;
import panel.EntrerMulti;
import panel.NewEmployee;
import panel.SortirMulti;

public class PointerMultiListener implements ActionListener {

    EntrerMulti em;
    MetierEntrerMulti mem;
    SortirMulti sm;
    MetierSortirMulti msm;

    public PointerMultiListener() {
    }

    public PointerMultiListener(EntrerMulti em) {
        this.em = em;
        mem = new MetierEntrerMulti(em);
    }

    public PointerMultiListener(SortirMulti sm) {
        this.sm = sm;
        msm = new MetierSortirMulti(sm);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == em.getVoirpointage()) {
            try {
                mem.soumettre();
                em.getNouveau().addActionListener(this);
                em.getEnregistrermodele().addActionListener(this);
                em.getChargement().addActionListener(new ModeleListener2(em));
            } catch (InvalidExcept ex) {
            }
        }
        if (e.getSource() == em.getEnregistrerpointage()) {
            try {
                mem.setRetardDivise();
                mem.faireentrertous();
                System.out.println("   RETARD SEMAINE   ");
                mem.setRetardSemaine();
                System.out.println("   RETARD JOUR   ");
                mem.setRetardJour();
            } catch (InvalidExcept ex) {

            }
        }
        if (e.getSource() == em.getNouveau()) {
            NewEmployeeDialog main = new NewEmployeeDialog(em.getFenetre(), true);
            NewEmployee ed = new NewEmployee(main);
            MetierNewEmployee nme = new MetierNewEmployee(ed);
            nme.setComboCateg();
            main.setContentPane(ed);
            main.setVisible(true);
        }
        if (e.getSource() == em.getEnregistrermodele()) {
            EnregistrerBox main = new EnregistrerBox(em.getFenetre(), true);
            EnregistrerDialog ed = new EnregistrerDialog(main);
            MetierModele nme = new MetierModele(ed);
            main.setContentPane(ed);
            main.setVisible(true);
        }

    }
}
