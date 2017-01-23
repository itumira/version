package exceptions;

import java.awt.Frame;
import javax.swing.JOptionPane;

public class InvalidExcept extends Exception {

    public InvalidExcept() {
    }

    public InvalidExcept(String message) {
        super(message);
    }

    public InvalidExcept(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidExcept(Throwable cause) {
        super(cause);
    }

    public InvalidExcept(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InvalidExcept(String message, String type) {
        JOptionPane.showMessageDialog(new Frame(), message, type, JOptionPane.ERROR_MESSAGE);
    }

}
