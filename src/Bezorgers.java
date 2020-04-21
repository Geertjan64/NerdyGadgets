public class Bezorgers {

    private String[] bezorgers;
    private boolean isActief;

    public Bezorgers() {
        bezorgers = new String[] {"Piet", "Geert-Jan", "Pamir", "Lars", "Dielan", "Pascal", "Peter", "Fred", "Jan"};
        isActief = false;
    }

    public String[] getBezorgers() {
        return bezorgers;
    }

    public void setActief(boolean actief) {
        isActief = actief;
    }

}
