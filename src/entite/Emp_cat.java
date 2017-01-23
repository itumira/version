package entite;

public class Emp_cat {

    int idempcat;
    String categorie;

    public Emp_cat() {
    }

    public Emp_cat(int idempcat, String categorie) {
        this.idempcat = idempcat;
        this.categorie = categorie;
    }

    public int getIdempcat() {
        return idempcat;
    }

    public void setIdempcat(int idempcat) {
        this.idempcat = idempcat;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

}
