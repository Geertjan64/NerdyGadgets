public class Bezorger extends Werknemer {

    //private int werknemerID; Overerven abstracte class
    //private String voornaam; Overerven abstracte class
    //private String achternaam; Overerven abstracte class

    private boolean isActief;

    public Bezorger(int werknemerID, String voornaam, String achternaam, String tussenvoegsel, String email) {
        super(werknemerID, voornaam, achternaam, tussenvoegsel, email);
        isActief = false;
    }

    public boolean getActief() {
        return isActief;
    }

    public void setActief(boolean actief) {
        isActief = actief;
    }

    public String toString() {
        return "(" + this.getWerknemerID() + ") " + this.getVoornaam();
    }

    public boolean equals(Object obj) {
        if (this.toString().substring(1, 5).equals(obj)) {
            return true;
        }
        return false;
    }
}
