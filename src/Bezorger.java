public class Bezorger extends Persoon {

    //private int werknemerID; Overerven abstracte class
    //private String voornaam; Overerven abstracte class
    //private String achternaam; Overerven abstracte class

    private boolean isActief;

    public Bezorger(int werknemerID, String voornaam, String achternaam) {
        super(werknemerID, voornaam, achternaam);
        isActief = false;
    }

    public boolean getActief() {
        return isActief;
    }

    public void setActief(boolean actief) {
        isActief = actief;
    }

}
