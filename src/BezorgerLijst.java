public class BezorgerLijst {

    private String[] bezorgers;
    private boolean isActief;

    public BezorgerLijst() {
        bezorgers = new String[] {"Piet", "Geert-Jan", "Pamir", "Lars", "Dielan", "Pascal", "Peter", "Fred", "Jan"};
    }

    public String[] getBezorgers() {
        return bezorgers;
    }

    public void setActief(boolean actief) {
        isActief = actief;
    }

    public boolean isActief() {
        return isActief;
    }
}
