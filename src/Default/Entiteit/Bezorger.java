package Default.Entiteit;

public class Bezorger extends Werknemer {

    private boolean isActief;

    public Bezorger(int werknemerID, String voornaam, String achternaam, String tussenvoegsel, String email, int activiteit) {
        super(werknemerID, voornaam, achternaam, tussenvoegsel, email);
        if (activiteit == 0) {
            isActief = false;
        } else {
            isActief = true;
        }
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
