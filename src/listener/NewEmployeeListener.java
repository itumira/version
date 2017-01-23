package listener;

import exceptions.InvalidExcept;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import metier.MetierNewEmployee;
import panel.NewEmployee;

public class NewEmployeeListener implements ActionListener {

    MetierNewEmployee mne;
    NewEmployee ne;

    public NewEmployeeListener() {
    }

    public NewEmployeeListener(NewEmployee ne) {
        this.ne = ne;
        mne = new MetierNewEmployee(ne);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ne.getEnregistrer()) {
            try {
                mne.enregistrerNewEmp();
            } catch (InvalidExcept ex) {
            }
        }
    }

}
