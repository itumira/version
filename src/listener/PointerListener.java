package listener;

import database.DBConnect;
import exceptions.InvalidExcept;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.MetierPointer;
import panel.Pointer;
import personne.Employe;

public class PointerListener implements ActionListener {

    Pointer pointer;
    MetierPointer metierpointer;

    public PointerListener() {
    }

    public PointerListener(Pointer pointer) {
        this.pointer = pointer;
        metierpointer = new MetierPointer(pointer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pointer.getCheck_entrer()) {
            try {
                metierpointer.faireentrer();
            } catch (InvalidExcept ex) {
            }
        }
        if (e.getSource() == pointer.getCheck_sortir()) {
            try {
                metierpointer.fairesortir();
            } catch (InvalidExcept ex) {
            }
        }
    }
}
