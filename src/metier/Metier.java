package metier;

public class Metier extends GestionDate {

    public boolean isBetween(long a, long b, long c) {
        return a <= b && b <= c;
    }
}
