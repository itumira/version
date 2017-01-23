package metier;

import exceptions.InvalidExcept;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GestionDate {

    public int getAnneeAuj() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        java.util.Date date = new java.util.Date();
        return Integer.parseInt(dateFormat.format(date));
    }

    public String getDateAuj() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        java.util.Date date = new java.util.Date();
        return dateFormat.format(date);
    }

    public String dateSouple(String date) throws InvalidExcept {
        try {
            int b = Integer.parseInt(date.substring(0, 4));
            date = date.replaceAll(String.valueOf(date.charAt(4)), "-");
            date = date.replaceAll(String.valueOf(date.charAt(7)), "-");
            return date;
        } catch (Exception e) {
            try {
                date = date.replaceAll(String.valueOf(date.charAt(2)), "-");
                date = date.replaceAll(String.valueOf(date.charAt(5)), "-");

                java.util.Date actualDate = new java.util.Date();

                SimpleDateFormat yy = new SimpleDateFormat("dd-MM-yy");
                SimpleDateFormat yyyy = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    actualDate = yy.parse(date);
                } catch (ParseException pe) {
                    throw new InvalidExcept("Date invalide", "Erreur");
                }
                String dateentrer = yyyy.format(actualDate);
                return dateentrer;
            } catch (Exception ee) {
                throw new InvalidExcept("Date invalide", "Erreur");
            }
        }
    }

    public Time heureSouple(String heure_entrer) {
        try {
            return Time.valueOf(heure_entrer);
        } catch (Exception excep) {
            int isa = 0;
            for (int i = 0; i < heure_entrer.length(); i++) {
                if (heure_entrer.charAt(i) != ' ') {
                    isa++;
                }
            }
            char[] oo = new char[isa];
            int j = 0;
            for (int i = 0; i < heure_entrer.length() && j < isa; i++) {
                if (heure_entrer.charAt(i) != ' ') {
                    oo[j] = heure_entrer.charAt(i);
                    j++;
                }
            }
            String val = new String(oo);
            if ((String.valueOf(val.charAt(0))).equals('-')) {
                val = val.split("-")[0];
            }
            if (val.length() > 5) {
                val = val.replaceAll(String.valueOf(val.charAt(2)), ":");
                String[] apmh = contientInn(val);
                val = apmh[1] + " " + apmh[0];
                DateFormat df = new SimpleDateFormat("hh:mm aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                java.util.Date date = null;
                String output = null;
                try {
                    //Converting the input String to Date
                    date = df.parse(val);
                    //Changing the format of date and storing it in String
                    output = outputformat.format(date);
                    //Displaying the date
                    return Time.valueOf(output);
                } catch (Exception pe) {
                    pe.printStackTrace();
                }
            } else {
                heure_entrer = heure_entrer.replaceAll(String.valueOf(heure_entrer.charAt(2)), ":");
                heure_entrer = heure_entrer + ":00";
                return Time.valueOf(heure_entrer);
            }
            return null;
        }
    }

    public String[] contientInn(String heure) {
        for (int i = 0; i < heure.length(); i++) {
            if (heure.charAt(i) == 'A') {
                String am = "AM";
                String h = heure.split("A")[0];
                String[] x = new String[2];
                x[0] = am;
                x[1] = h;
                return x;
            } else if (heure.charAt(i) == 'P') {
                String am = "PM";
                String h = heure.split("P")[0];
                String[] x = new String[2];
                x[0] = am;
                x[1] = h;
                return x;
            }
        }
        return null;
    }

    public int setMois(String mois) {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        for (int i = 0; i < months.length; i++) {
            if (mois.equals(months[i])) {
                return i + 1;
            }
        }
        return 0;
    }

    public boolean isWeekEnd(Date date) {
        java.util.Date dateutil = new java.util.Date(date.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("u");
        int day = new Integer(sdf.format(dateutil));
        return day == 7 || day == 6;
    }

    public boolean isLundi(Date date) {
        java.util.Date dateutil = new java.util.Date(date.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("u");
        int day = new Integer(sdf.format(dateutil));
        return day == 1;
    }

    public boolean isSamedi(Date date) {
        java.util.Date dateutil = new java.util.Date(date.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("u");
        int day = new Integer(sdf.format(dateutil));
        return day == 6;
    }

}
